package com.example.sprint.validation;

import com.example.sprint.enums.Currency;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyValidator implements ConstraintValidator<CurrencyValidation, Currency> {
    private List<Currency> enums;

    @Override
    public void initialize(CurrencyValidation constraintAnnotation) {
        enums = Arrays.stream(constraintAnnotation.currency().getEnumConstants()).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(Currency value, ConstraintValidatorContext context) {
        return enums.contains(value);
    }
}
