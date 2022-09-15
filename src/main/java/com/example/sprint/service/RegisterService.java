package com.example.sprint.service;

import com.example.sprint.dto.RegisterDTO;
import com.example.sprint.entity.Author;
import com.example.sprint.entity.Book;
import com.example.sprint.entity.Register;
import com.example.sprint.exception.CustomException;
import com.example.sprint.exception.ResponseCode;
import com.example.sprint.repository.AuthorRepository;
import com.example.sprint.repository.BookRepository;
import com.example.sprint.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.sprint.exception.ResponseCode.SUCCESS;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final RegisterRepository registerRepository;

//    @CacheEvict(value = "authorCache", key = "#registerDTO.authorReqDTO.name")
    public ResponseCode register(RegisterDTO registerDTO) throws CustomException {
        Author author = registerDTO.getAuthor().toEntity();
        Book book = registerDTO.getBook().toEntity();

        Long authorId = authorRepository.save(author).getId();
        Long bookId = bookRepository.save(book).getId();

        registerRepository.save(Register.builder()
                .author(Author.builder().id(authorId).build())
                .book(Book.builder().id(bookId).build())
                .build());

        return SUCCESS;
    }

}
