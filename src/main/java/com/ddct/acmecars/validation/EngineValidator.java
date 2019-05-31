package com.ddct.acmecars.validation;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ddct.acmecars.models.Engine;

@Component
public class EngineValidator implements Validator {

    private final MessageSource messageSource;

    @Autowired
    public EngineValidator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Engine.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "type", null,
            messageSource.getMessage("engine.type.missing", null, Locale.ENGLISH));

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capacity", null,
            messageSource.getMessage("engine.capacity.missing", null, Locale.ENGLISH))  ;

    }
}
