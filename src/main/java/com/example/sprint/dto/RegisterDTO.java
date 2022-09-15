package com.example.sprint.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@NoArgsConstructor
@Getter
public class RegisterDTO {

    private List<@Valid AuthorReqDTO> authors;
    private @Valid BookReqDTO book;

    @Builder
    public RegisterDTO(List<AuthorReqDTO> authorReqDTOList, BookReqDTO bookReqDTO){
        this.authors = authorReqDTOList;
        this.book = bookReqDTO;
    }

}
