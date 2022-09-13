package com.example.sprint.service;

import com.example.sprint.dto.BookReqDTO;
import com.example.sprint.dto.BookResDTO;
import com.example.sprint.entity.Book;
import com.example.sprint.enums.BooksOrderBy;
import com.example.sprint.exception.CustomException;
import com.example.sprint.exception.DataResponseCode;
import com.example.sprint.exception.ResponseCode;
import com.example.sprint.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.sprint.enums.BooksOrderBy.ORDER_BY_PUBLISH_DATE;
import static com.example.sprint.exception.ResponseCode.SUCCESS;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Transactional
    public ResponseCode addBook(BookReqDTO bookReqDTO) throws CustomException {
        bookRepository.save(bookReqDTO.toEntity());
        return SUCCESS;
    }

    public DataResponseCode getBooks(Pageable pageable){
        PageImpl<Book> books = bookRepository.findAllOrderById(pageable);

        return new DataResponseCode(SUCCESS, pageToMap(books));
    }

    // TODO 정렬의 조건이 다양해지면 QueryDsl 사용
    public DataResponseCode getBooks(Pageable pageable, BooksOrderBy booksOrderBy){
        PageImpl<Book> books;

        if (booksOrderBy == ORDER_BY_PUBLISH_DATE){
            books = bookRepository.findAllOrderByPublishDate(pageable);
        } else {
            books = bookRepository.findAllOrderById(pageable);
        }

        return new DataResponseCode(SUCCESS, pageToMap(books));
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
