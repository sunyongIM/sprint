package com.example.sprint.dto;

import com.example.sprint.entity.Book;
import com.example.sprint.enums.Currency;
import com.example.sprint.validation.CurrencyValidation;
import com.example.sprint.validation.CustomValidation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@CustomValidation
public class BookReqDTO {

    @NotBlank(message = "TITLE_NECESSARY")
    @Size(max = 255, message = "TITLE_TOO_LONG")
    private String title;

//    @NotBlank(message = "DISCONTINUED_NECESSARY")
    private Boolean discontinued;

    @NotBlank(message = "ISBN_NECESSARY")
    @ISBN(message = "ISBN_NOT_VALID")
    private String isbn;

    @NotNull(message = "PAGES_NECESSARY")
    private Long pages;

    @NotNull(message = "PUBLISH_DATE_NECESSARY")
    @PastOrPresent(message = "PUBLISH_DATE_NOT_VALID")
    private LocalDate publishDate;

    @Digits(integer = 300, fraction = 2)
    private Double price;

    @CurrencyValidation(currency = Currency.class)
    private String currency;

    @Builder
    public BookReqDTO(String title, Boolean discontinued, String isbn, Long pages, LocalDate publishDate, Double price, String currency) {
        this.title = title;
        this.discontinued = discontinued;
        this.isbn = isbn;
        this.pages = pages;
        this.publishDate = publishDate;
        this.price = price;
        this.currency = currency;
    }

    // 해당 메서드 대신 @ISBN Validation 사용
    public boolean checkIsbn() {
        String isbnString = this.isbn.replace("-", "");
        int sum = 0;

        for (int i = 0; i < isbnString.length(); i++) {
            if (i % 2 == 1) {
                sum += Character.getNumericValue(isbnString.charAt(i)) * 3;
                continue;
            }
            sum += Character.getNumericValue(isbnString.charAt(i));
        }

        return (sum % 10) == 0;
    }

    public Book toEntity() {
        Currency stringToCurrency = Currency.valueOf(this.currency);
        Boolean discontinuedDefault = this.discontinued;
        if (discontinuedDefault == null){
            discontinuedDefault = false;
        }
        return Book.builder()
                .title(this.title)
                .discontinued(discontinuedDefault)
                .isbn(this.isbn)
                .pages(this.pages)
                .publishDate(this.publishDate)
                .price(this.price)
                .currency(stringToCurrency)
                .build();
    }

}
