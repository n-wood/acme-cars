package com.ddct.acmecars.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ddct.acmecars.controllers.CarsDto;

@Component
public class CarsDtoValidator implements Validator {

    private final CarValidator carValidator;

    @Autowired
    public CarsDtoValidator(CarValidator carValidator) {
        this.carValidator = carValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CarsDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CarsDto carsDto = (CarsDto) target;

        if (carsDto == null) {
            return;
        }

        if (!CollectionUtils.isEmpty(carsDto.getCars())) {
            for (int i = 0; i < carsDto.getCars().size(); i++) {
                try {
                    errors.pushNestedPath(String.format("cars[%d]", i));
                    ValidationUtils.invokeValidator(this.carValidator, carsDto.getCars().get(i), errors);
                } finally {
                    errors.popNestedPath();
                }
            }
        }
    }
}
