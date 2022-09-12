package com.example.sprint.dto;

import com.example.sprint.entity.Author;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class AuthorReqDTO {

    @NotBlank(message = "NAME_NECESSARY")
    @Size(max = 255, message = "NAME_TOO_LONG")
    private String name;

    @NotBlank(message = "BIRTH_NECESSARY")
    @PastOrPresent(message = "BIRTH_NOT_VALID")
    private LocalDate birth;

    @Builder
    public AuthorReqDTO(String name, LocalDate birth) {
        this.name = name;
        this.birth = birth;
    }

    public Author toEntity() {
        return Author.builder()
                .name(this.name)
                .birth(this.birth)
                .build();
    }

}
