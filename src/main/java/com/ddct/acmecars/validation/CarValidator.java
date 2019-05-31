package com.ddct.acmecars.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ddct.acmecars.models.Car;

@Component
public class CarValidator implements Validator {

    private final EngineValidator engineValidator;

    @Autowired
    public CarValidator(EngineValidator engineValidator) {
        this.engineValidator = engineValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Car.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Car car = (Car) target;

        if (car == null) {
            return;
        }

        if (car.getEngine() != null) {
            try {
                errors.pushNestedPath("engine");
                ValidationUtils.invokeValidator(this.engineValidator, car.getEngine(), errors);
            } finally {
                errors.popNestedPath();
            }
        }
    }
}
