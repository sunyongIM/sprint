package com.example.sprint.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PriceCurrencyValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomValidation {
    String message() default "통화는 가격이 존재하는 경우 필수값이며, 아닌 경우는 존재하지 않아야함.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
