package com.example.sprint.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RegisterDTO {

    private AuthorReqDTO author;
    private BookReqDTO book;

    @Builder
    public RegisterDTO(AuthorReqDTO authorReqDTO, BookReqDTO bookReqDTO){
        this.author = authorReqDTO;
        this.book = bookReqDTO;
    }

}
