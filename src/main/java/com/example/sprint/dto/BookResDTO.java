package com.example.sprint.dto;

import com.example.sprint.entity.Author;
import com.example.sprint.entity.Book;
import com.example.sprint.enums.Currency;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BookResDTO {

    private Long id;
    private String title;
    private Boolean discontinued = false;
    private String isbn;
    private Long pages;
    private LocalDate publishDate;
    private Double price;
    private Currency currency;
    private List<String> authors;

    @Builder
    public BookResDTO(Long id, String title, Boolean discontinued, String isbn, Long pages, LocalDate publishDate, Double price, Currency currency, List<String> authors) {
        this.id = id;
        this.title = title;
        this.discontinued = discontinued;
        this.isbn = isbn;
        this.pages = pages;
        this.publishDate = publishDate;
        this.price = price;
        this.currency = currency;
        this.authors = authors;
    }

    // 저자의 생년월일은 제외하고 이름만 반환
    public BookResDTO(Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.discontinued = book.getDiscontinued();
        this.isbn = book.getIsbn();
        this.pages = book.getPages();
        this.publishDate = book.getPublishDate();
        this.price = book.getPrice();
        this.currency = book.getCurrency();
        this.authors = book.getAuthors().stream().map(Author::getName).collect(Collectors.toList());
    }

}
