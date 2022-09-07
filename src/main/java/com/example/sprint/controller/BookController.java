package com.example.sprint.controller;

import com.example.sprint.enums.BooksOrderBy;
import com.example.sprint.exception.HttpResponse;
import com.example.sprint.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Book API")
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping()
    @ApiOperation(value = "Book 전체 조회", notes = "Paging 적용 (size 10, index 1 부터 시작), default order => 최신 발간년도")
    public ResponseEntity<HttpResponse> bookList(@PageableDefault(value = 10) Pageable pageable){
        return HttpResponse.toResponseEntity(bookService.getBooks(pageable));
    }

    @GetMapping(params = {"order_by"})
    @ApiOperation(value = "Book 전체 조회", notes = "Paging 적용 (size 10, index 1 부터 시작), default order => 최신 발간년도")
    public ResponseEntity<HttpResponse> bookList(@PageableDefault(value = 10) Pageable pageable, @RequestParam("order_by") BooksOrderBy booksOrderBy){
        return HttpResponse.toResponseEntity(bookService.getBooks(pageable, booksOrderBy));
    }

}
