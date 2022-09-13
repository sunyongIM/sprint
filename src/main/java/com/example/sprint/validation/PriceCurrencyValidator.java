package com.example.sprint.validation;

import com.example.sprint.dto.BookReqDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceCurrencyValidator implements ConstraintValidator<CustomValidation, BookReqDTO> {

    @Override
    public void initialize(CustomValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(BookReqDTO bookReqDTO, ConstraintValidatorContext context) {
        if (bookReqDTO.getPrice() == null && bookReqDTO.getCurrency() == null || bookReqDTO.getPrice() != null && bookReqDTO.getCurrency() != null) {
            return true;
        } else {
            return false;
        }
    }
}
