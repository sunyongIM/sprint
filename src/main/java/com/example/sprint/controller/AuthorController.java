package com.example.sprint.controller;

import com.example.sprint.dto.AuthorReqDTO;
import com.example.sprint.exception.HttpResponse;
import com.example.sprint.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Api(tags = "Author API")
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("")
    @ApiOperation(value = "저자 등록", notes = "")
    public ResponseEntity<HttpResponse> authorAdd(@RequestBody @Valid AuthorReqDTO authorReqDTO) {
        return HttpResponse.toResponseEntity(authorService.addAuthor(authorReqDTO));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "id로 저자 찾기", notes = "Path Variable 이용")
    public ResponseEntity<HttpResponse> authorGet(@PathVariable Long id) {
        return HttpResponse.toResponseEntity(authorService.getAuthorById(id));
    }

}
