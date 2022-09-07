package com.example.sprint.service;

import com.example.sprint.dto.BookResDTO;
import com.example.sprint.entity.Book;
import com.example.sprint.enums.BooksOrderBy;
import com.example.sprint.exception.DataResponseCode;
import com.example.sprint.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.sprint.exception.ResponseCode.SUCCESS;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public DataResponseCode getBooks(Pageable pageable){
        PageImpl<Book> books = bookRepository.findAll(pageable);

        return new DataResponseCode(SUCCESS, pageToMap(books));
    }

    public DataResponseCode getBooks(Pageable pageable, BooksOrderBy booksOrderBy){
        return null;
    }

    private Map<String, Object> pageToMap(Page<Book> books) {
        Map<String, Object> resultMap = new HashMap<>();
        List<BookResDTO> bookResDTOList = books.map(BookResDTO::new).toList();

        resultMap.put("endPage", books.isLast());
        resultMap.put("bookList", bookResDTOList);
        resultMap.put("totalElements", books.getTotalElements());
        return resultMap;
    }
}
