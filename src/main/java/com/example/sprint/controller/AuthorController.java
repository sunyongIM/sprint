package com.example.sprint.controller;

import com.example.sprint.exception.HttpResponse;
import com.example.sprint.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = "Author API")
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "id로 저자 찾기", notes = "Path Variable 이용")
    public ResponseEntity<HttpResponse> authorGet(@PathVariable Long id){
        return HttpResponse.toResponseEntity(authorService.getAuthorById(id));
    }

}
