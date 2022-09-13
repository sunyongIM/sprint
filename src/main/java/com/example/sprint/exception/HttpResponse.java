package com.example.sprint.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class HttpResponse implements Serializable {

    private final LocalDateTime timestamp;
    private final Integer status;
    private final String response;
    private final String code;
    private final String message;
    private Object data;

    @Builder
    public HttpResponse(Integer status, String response, String code, String message, Object data) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.response = response;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity<HttpResponse> toResponseEntity(ResponseCode responseCode) {
        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(HttpResponse.builder()
                        .status(responseCode.getHttpStatus().value())
                        .response(responseCode.getHttpStatus().name())
                        .code(responseCode.name())
                        .message(responseCode.getMessage())
                        .build()
                );
    }

    // 첫번째 validation 에러만 보여줌
    public static ResponseEntity<HttpResponse> toResponseEntity(MethodArgumentNotValidException validError) {

//        List<String> errorsField = new ArrayList<>();
        List<String> errorsMessage = new ArrayList<>();
        for (ObjectError error : validError.getBindingResult().getAllErrors()) {
//            errorsField.add(((FieldError) error).getField());
            errorsMessage.add(error.getDefaultMessage());
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(HttpResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .response(HttpStatus.BAD_REQUEST.name())
                        .code("CUSTOM_EXCEPTION")
                        .message(errorsMessage.get(0))
                        .build()
                );
    }

    public static ResponseEntity<HttpResponse> toResponseEntity(ConstraintViolationException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(HttpResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .response(HttpStatus.BAD_REQUEST.name())
                        .code(exception.getLocalizedMessage())
                        .message(exception.getMessage())
                        .build()
                );
    }


        public static ResponseEntity<HttpResponse> toResponseEntity(DataResponseCode dataResponseCode) {
        ResponseCode responseCode = dataResponseCode.getResponseCode();
        Object data = dataResponseCode.getData();
        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(HttpResponse.builder()
                        .status(responseCode.getHttpStatus().value())
                        .response(responseCode.getHttpStatus().name())
                        .code(responseCode.name())
                        .message(responseCode.getMessage())
                        .data(data)
                        .build()
                );
    }
}