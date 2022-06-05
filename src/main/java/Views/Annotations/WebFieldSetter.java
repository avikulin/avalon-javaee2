package Views.Annotations;

import Views.Enums.Validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebFieldSetter {
    String fieldName();
    Validator[] rules();
    int minLength() default -1;
    int maxLength() default -1;
    boolean mayBeEmpty() default true;
}
