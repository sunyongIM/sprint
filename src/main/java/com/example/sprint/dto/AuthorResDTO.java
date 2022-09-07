package com.example.sprint.dto;

import com.example.sprint.entity.Author;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class AuthorResDTO {

    private String name;
    private LocalDate birth;

    @Builder
    public AuthorResDTO(String name, LocalDate birth){
        this.name = name;
        this.birth = birth;
    }

    public AuthorResDTO(Author author){
        this.name = author.getName();
        this.birth = author.getBirth();
    }
}
