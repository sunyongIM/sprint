package com.example.sprint.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Builder
    public Author(String name, LocalDate birth, Book book){
        this.name = name;
        this.birth = birth;
        this.book = book;
    }
}
