package com.example.sprint.entity;

import com.example.sprint.enums.Currency;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false)
    private Boolean discontinued = false;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private Long pages;

    @Column(nullable = false)
    private LocalDate publishDate;

    private Double price;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Author> authors;

    @Builder
    public Book(String title, Boolean discontinued, String isbn, Long pages, LocalDate publishDate, Double price, Currency currency) {
        this.title = title;
        this.discontinued = discontinued;
        this.isbn = isbn;
        this.pages = pages;
        this.publishDate = publishDate;
        this.price = price;
        this.currency = currency;
    }

}
