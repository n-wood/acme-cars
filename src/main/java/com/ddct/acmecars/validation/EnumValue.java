package com.ddct.acmecars.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { EnumValueValidator.class })
@Retention(RetentionPolicy.RUNTIME)

@Target({
    ElementType.ANNOTATION_TYPE,
    ElementType.FIELD,
})
public @interface EnumValue {

    String message() default "{validation.enum.message}";

    Class<?>[] groups()  default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends java.lang.Enum<?>> enumClass();

}
