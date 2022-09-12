package com.example.sprint.dto;

import com.example.sprint.entity.Book;
import com.example.sprint.enums.Currency;
import lombok.Builder;
import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class BookReqDTO {

    @NotBlank(message = "TITLE_NECESSARY")
    @Size(max = 255, message = "TITLE_TOO_LONG")
    private String title;

    @NotBlank(message = "DISCONTINUED_NECESSARY")
    private Boolean discontinued;

    @NotBlank(message = "ISBN_NECESSARY")
    @ISBN(message = "ISBN_NOT_VALID")
    private String isbn;

    @NotBlank(message = "PAGES_NECESSARY")
    private Long pages;

    @NotBlank(message = "PUBLISH_DATE_NECESSARY")
    @PastOrPresent(message = "PUBLISH_DATE_NOT_VALID")
    private LocalDate publishDate;

    @Digits(integer = 0, fraction = 2)
    private Float price;


    private Currency currency;

    @Builder
    public BookReqDTO(String title, Boolean discontinued, String isbn, Long pages, LocalDate publishDate, Float price, Currency currency) {
        this.title = title;
        this.discontinued = discontinued;
        this.isbn = isbn;
        this.pages = pages;
        this.publishDate = publishDate;
        this.price = price;
        this.currency = currency;
    }

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
        return Book.builder()
                .title(this.title)
                .discontinued(this.discontinued)
                .isbn(this.isbn)
                .pages(this.pages)
                .publishDate(this.publishDate)
                .price(this.price)
                .currency(this.currency)
                .build();
    }

}
