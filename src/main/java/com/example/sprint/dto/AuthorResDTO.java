package com.example.sprint.dto;

import com.example.sprint.entity.Author;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class AuthorResDTO {

    private String name;
    private LocalDate birth;
    private List<String> books;

    @Builder
    public AuthorResDTO(String name, LocalDate birth, List<String> books){
        this.name = name;
        this.birth = birth;
        this.books = books;
    }

    public AuthorResDTO(Author author){
        this.name = author.getName();
        this.birth = author.getBirth();
        this.books = author.getRegisterList().stream().map(authorBook -> authorBook.getBook().getTitle()).collect(Collectors.toList());
    }
}
