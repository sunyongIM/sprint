package com.example.sprint.controller;

import com.example.sprint.dto.AuthorReqDTO;
import com.example.sprint.dto.BookReqDTO;
import com.example.sprint.dto.RegisterDTO;
import com.example.sprint.exception.HttpResponse;
import com.example.sprint.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "저자-도서 등록 API")
@RequestMapping("/api/register")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService){
        this.registerService = registerService;
    }

    @PostMapping("")
    @ApiOperation(value = "저자-도서 등록", notes = "")
    public ResponseEntity<HttpResponse> authorAdd(@RequestBody @Valid RegisterDTO registerDTO) {
        return HttpResponse.toResponseEntity(registerService.register(registerDTO));
    }

}
