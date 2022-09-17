package com.example.sprint.validation;

import com.example.sprint.enums.Currency;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CurrencyValidator implements ConstraintValidator<CurrencyValidation, String> {
    private List<Currency> enums;

    @Override
    public void initialize(CurrencyValidation constraintAnnotation) {
        enums = Arrays.stream(constraintAnnotation.currency().getEnumConstants()).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Currency currency = null;
        try {
            currency = Currency.valueOf(value);
        } catch (IllegalArgumentException e){
            log.warn("등록되지 않은 통화 : {}", value);
            return false;
        }
        return enums.contains(currency);
    }
}
