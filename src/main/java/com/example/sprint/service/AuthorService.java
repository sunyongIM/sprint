package com.example.sprint.service;

import com.example.sprint.dto.AuthorResDTO;
import com.example.sprint.entity.Author;
import com.example.sprint.exception.CustomException;
import com.example.sprint.exception.DataResponseCode;
import com.example.sprint.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.sprint.exception.ResponseCode.AUTHOR_NOT_FOUND;
import static com.example.sprint.exception.ResponseCode.SUCCESS;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public DataResponseCode getAuthorById(Long id) throws CustomException {
        HashMap<String, AuthorResDTO> authorHashMap = new HashMap<>();
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new CustomException(AUTHOR_NOT_FOUND)
        );
        AuthorResDTO authorResDTO = new AuthorResDTO(author);
        authorHashMap.put("author", authorResDTO);
        return new DataResponseCode(SUCCESS, authorHashMap);
    }

    // 저자가 동명이인인 경우엔 다시 찾는 과정을 거침
    public DataResponseCode getAuthorByName(String name) throws CustomException {
        HashMap<String, List<AuthorResDTO>> authorHashMap = new HashMap<>();
        List<Author> authorList = authorRepository.findAllByName(name);
        if (authorList.isEmpty()) {
            throw new CustomException(AUTHOR_NOT_FOUND);
        }

        List<AuthorResDTO> authorResDTOList = authorList.stream().map(AuthorResDTO::new).collect(Collectors.toList());
        authorHashMap.put("author", authorResDTOList);
        return new DataResponseCode(SUCCESS, authorHashMap);
    }

}
