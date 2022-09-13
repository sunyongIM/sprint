package com.example.sprint.dto;

import com.example.sprint.entity.Author;
import com.example.sprint.entity.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class AuthorReqDTO {

    @NotBlank(message = "NAME_NECESSARY")
    @Size(max = 255, message = "NAME_TOO_LONG")
    private String name;

    @NotNull(message = "BIRTH_NECESSARY")
    @PastOrPresent(message = "BIRTH_NOT_VALID")
    private LocalDate birth;

    private Long bookId;

    @Builder
    public AuthorReqDTO(String name, LocalDate birth, Long bookId) {
        this.name = name;
        this.birth = birth;
        this.bookId = bookId;
    }

    public Author toEntity() {
        return Author.builder()
                .name(this.name)
                .birth(this.birth)
                .book(Book.builder().id(this.bookId).build())
                .build();
    }

}
