package com.example.sprint.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static com.example.sprint.exception.ResponseCode.WRONG_INTEGRITY;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ConstraintViolationException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<HttpResponse> handleDataException() {
        return HttpResponse.toResponseEntity(WRONG_INTEGRITY);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    protected ResponseEntity<HttpResponse> handleValidationException(MethodArgumentNotValidException e) {
        return HttpResponse.toResponseEntity(e);
    }

    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<HttpResponse> handleCustomException(CustomException e) {
        return HttpResponse.toResponseEntity(e.getResponseCode());
    }
}
