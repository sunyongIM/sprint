package com.example.sprint.service;

import com.example.sprint.dto.AuthorReqDTO;
import com.example.sprint.dto.BookReqDTO;
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

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.sprint.exception.ResponseCode.SUCCESS;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final RegisterRepository registerRepository;

    //    @CacheEvict(value = "authorCache", key = "#registerDTO.authorReqDTO.name")
    @Transactional
    public ResponseCode register(RegisterDTO registerDTO) throws CustomException {

        List<Author> authors = registerDTO.getAuthors().stream().map(AuthorReqDTO::toEntity).collect(Collectors.toList());
        Book book = registerDTO.getBook().toEntity();

        Long bookId = bookRepository.save(book).getId();

        for (Author author : authors) {
            Long authorId;

            Author existAuthor = authorRepository.findByNameAndBirth(author.getName(), author.getBirth()).orElseGet(
                    ()-> authorRepository.save(author)
            );

            authorId = existAuthor.getId();

            registerRepository.save(Register.builder()
                    .author(Author.builder().id(authorId).build())
                    .book(Book.builder().id(bookId).build())
                    .build());
        }

        return SUCCESS;
    }

}
