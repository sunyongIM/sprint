package com.example.sprint.enums;

import lombok.Getter;

@Getter
public enum BooksOrderBy {
    ORDER_BY_ID("등록순"),
    ORDER_BY_PUBLISH_DATE("최신순");

    private final String order;

    BooksOrderBy(String order){
        this.order = order;
    }

}
