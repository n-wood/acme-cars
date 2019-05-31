package com.ddct.acmecars.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {
    private Enum[] enumValues;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        enumValues = constraintAnnotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid = false;

        if (value != null) {
            for (Enum enumValue : enumValues) {
                if (enumValue.name().equals(value)) {
                    isValid = true;
                    break;
                }
            }
        } else {
            isValid = true;
        }

        return isValid;
    }
}
