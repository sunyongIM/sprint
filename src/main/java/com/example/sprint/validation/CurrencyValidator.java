package com.example.sprint.validation;

import com.example.sprint.enums.Currency;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class CurrencyValidator implements ConstraintValidator<CurrencyValidation, String> {

    @Override
    public void initialize(CurrencyValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Currency.valueOf(value);
        } catch (IllegalArgumentException e){
            log.warn("등록되지 않은 통화 : {}", value);
            return false;
        }
        return true;
    }
}
