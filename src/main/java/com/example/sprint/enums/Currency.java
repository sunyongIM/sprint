package com.example.sprint.enums;

import lombok.Getter;

@Getter
public enum Currency {

    KRW("원"),
    USD("달러"),
    EUR("유로");

    private final String currencyUnit;


    Currency(String currencyUnit){
        this.currencyUnit = currencyUnit;
    }


}
