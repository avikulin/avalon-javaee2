package Views.Templates;

import ApiUtils.Helpers.DataValidator;
import Common.Annotations.UserCaption;
import Common.Exceptions.DataValidationException;
import Views.Annotations.WebFieldGetter;
import Views.Annotations.WebFieldSetter;
import Views.Annotations.WebFormModel;
import Views.WebForm;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class BaseWebForm implements WebForm {
    private static final int EMPTY_PARAM = -1;

    private final Map<String, Supplier<String>> outputData;
    private final Map<String, InputCtx> inputData;
    private final List<String> errorCtx;
    private boolean validationState;

    public BaseWebForm() {
        this.inputData = new HashMap<>();
        this.outputData = new HashMap<>();
        this.errorCtx = new ArrayList<>();
        this.validationState = true;
        Class<?> clazz = this.getClass();
        WebFormModel[] classAnnotation = clazz.getAnnotationsByType(WebFormModel.class);
        if (classAnnotation.length == 0) {
            throw new IllegalStateException("This class is not WebFormModel. Check the required annotations.");
        }

        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            WebFieldGetter getter = m.getDeclaredAnnotation(WebFieldGetter.class);
            WebFieldSetter setter = m.getDeclaredAnnotation(WebFieldSetter.class);

            if (getter != null) {
                if ((m.getParameterCount() != 0) || (m.getReturnType() != String.class)) {
                    throw new IllegalStateException("This class is not a proper WebFormModel. The getter method \"" +
                            m.getName() + "\" has a wrong signature. ");
                }
                String getterFieldName = getter.fieldName();
                this.outputData.put(getterFieldName, () -> {
                    try {
                        return (String) m.invoke(this);
                    } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                        throw new IllegalStateException("Error in analyzing getters at method \"" + m.getName() + "\"");
                    }
                });
                continue; //метод либо getter, либо setter. Поэтому пропускаем часть анализа на setter.
            }
            if (setter != null) {
                if ((m.getParameterCount() != 1)
                        || (m.getReturnType() != Void.TYPE)
                        || m.getParameterTypes()[0] != String.class
                        || m.isAccessible()) {
                    throw new IllegalStateException("This class is not a proper WebFormModel. The setter method \"" +
                            m.getName() + "\" has a wrong signature and/or annotation and/or access level. ");
                }
                String setterFieldName = setter.fieldName();
                UserCaption[] fieldCaption = m.getAnnotationsByType(UserCaption.class);
                if (fieldCaption.length == 0 || fieldCaption[0].value().equals("")) {
                    throw new IllegalStateException("This class is not a proper WebFormModel. The method \"" +
                            m.getName() + "\" has a wrong \"UserCaption\" annotation. " +
                            "Check the signature and required annotations.");
                }
                String uiCaption = fieldCaption[0].value();

                ValidationCtx vctx = new ValidationCtx(
                        setter.rules(),
                        setter.minLength(),
                        setter.maxLength(),
                        setter.mayBeEmpty());
                Consumer<String> func = s -> {
                    try {
                        m.setAccessible(true);
                        m.invoke(this, s);
                        m.setAccessible(false);
                    } catch (IllegalAccessException |
                            InvocationTargetException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                        throw new IllegalStateException("Error in analyzing setters " +
                                "at method \"" + m.getName() + "\"");
                    }
                };
                InputCtx ictx = new InputCtx(uiCaption, func, vctx);
                this.inputData.put(setterFieldName, ictx);
            }
        }
    }

    private void invalidateState(String uiFieldCaption, String errMsg) {
        this.validationState = false;
        String msg = String.format("Ошибка в поле \"%s\". %s", uiFieldCaption, errMsg);
        this.errorCtx.add(msg);
    }

    @Override
    public void fillFrom(HttpServletRequest request) throws DataValidationException {
        String msgEmpty = "Требуется задать значение";
        String msgMinLength = "Допускаются значения с минимальной длиной не менее %s символов";
        String msgMaxLength = "Допускаются значения с максимальной длиной не более %s символов";

        for (Map.Entry<String, InputCtx> ctxEntry : this.inputData.entrySet()) {
            String fieldName = ctxEntry.getKey();
            InputCtx inputCtx = ctxEntry.getValue();
            ValidationCtx validationCtx = inputCtx.getValidationCtx();
            String value = request.getParameter(fieldName);
            String uiCaption = inputCtx.getUiCaption();

            boolean isEmpty = DataValidator.isEmpty(value);

            // проверка на полноту
            if (!validationCtx.isMayBeEmpty() && isEmpty) {
                invalidateState(uiCaption, msgEmpty);
            }

            // проверка на минимальную длину, если она задана
            if (validationCtx.getMinLength() != EMPTY_PARAM && value.length() < validationCtx.getMinLength()) {
                invalidateState(uiCaption, String.format(msgMinLength, validationCtx.getMinLength()));
            }

            // проверка на максимальную длину, если она задана
            if (validationCtx.getMinLength() != EMPTY_PARAM && value.length() < validationCtx.getMinLength()) {
                invalidateState(uiCaption, String.format(msgMaxLength, validationCtx.getMaxLength()));
            }

            // проверка на правила валидации, если они были заданы
            Arrays.stream(validationCtx.getRules()).forEach(r -> {
                if (!r.check(value)) {
                    invalidateState(uiCaption, r.getErrMsg());
                }
            });

            //if (this.isValid() && !isEmpty) {
                inputCtx.getFunc().accept(value);
            //}
        }

        if (!this.isValid()) {
            String msg = "Oшибки валидации полей формы: \n" + String.join("\n", this.errorCtx);
            throw new DataValidationException(msg);
        }
    }

    @Override
    public void transferTo(HttpServletRequest request) {
        this.outputData.forEach(
                (fieldName, value) -> request.setAttribute(fieldName, value.get())
        );
    }

    @Override
    public boolean isValid() {
        return this.validationState;
    }

    @Override
    public List<String> getErrorsList() {
        return this.isValid() ? new ArrayList<>() : new ArrayList<>(this.errorCtx);
    }
}
