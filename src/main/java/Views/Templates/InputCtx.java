package Views.Templates;

import java.util.function.Consumer;

public class InputCtx {
    private String uiCaption;
    private Consumer<String> func;
    private ValidationCtx validationCtx;

    public InputCtx(String uiCaption, Consumer<String> func, ValidationCtx validationCtx) {
        this.uiCaption = uiCaption;
        this.func = func;
        this.validationCtx = validationCtx;
    }

    public String getUiCaption() {
        return uiCaption;
    }

    public Consumer<String> getFunc() {
        return func;
    }

    public ValidationCtx getValidationCtx() {
        return validationCtx;
    }
}
