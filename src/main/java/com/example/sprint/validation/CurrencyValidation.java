package com.example.sprint.validation;

import com.example.sprint.enums.Currency;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CurrencyValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrencyValidation {
    String message() default "등록 가능한 통화는 KRW, USD, EUR 입니다.";

    Class<?>[] groups() default {};

    Class<? extends Currency> currency();

    Class<? extends Payload>[] payload() default {};

}
