package com.example.sprint.service;

import com.example.sprint.dto.AuthorReqDTO;
import com.example.sprint.dto.AuthorResDTO;
import com.example.sprint.entity.Author;
import com.example.sprint.exception.CustomException;
import com.example.sprint.exception.DataResponseCode;
import com.example.sprint.exception.ResponseCode;
import com.example.sprint.repository.AuthorRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

//    @CacheEvict(value = "authorCache", key = "#authorReqDTO.name")
    public ResponseCode addAuthor(AuthorReqDTO authorReqDTO) throws CustomException {


        return SUCCESS;
    }

//    @Cacheable(value = "authorCache", key = "#id")  // 저자 삭제 또는 수정 로직이 없어 CacheEvict 를 따로 정의하진 않음
    public DataResponseCode getAuthorById(Long id) throws CustomException {
        HashMap<String, AuthorResDTO> authorHashMap = new HashMap<>();
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new CustomException(AUTHOR_NOT_FOUND)
        );
        AuthorResDTO authorResDTO = new AuthorResDTO(author);
        authorHashMap.put("author", authorResDTO);
        return new DataResponseCode(SUCCESS, authorHashMap);
    }

    // TODO 저자가 동명이인인 경우엔 - 생일이나 프로필로 다시 선택하는 과정을 거쳐야 함
//    @Cacheable(value = "authorCache", key = "#name")
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
