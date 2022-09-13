package com.example.sprint.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import static com.example.sprint.exception.ResponseCode.WRONG_INTEGRITY;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<HttpResponse> handleDataException() {
        log.error("handleDataException throw Exception : {}", WRONG_INTEGRITY);
        return HttpResponse.toResponseEntity(WRONG_INTEGRITY);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<HttpResponse> handleConstraintException(ConstraintViolationException e) {
        log.error("handleDataException throw Exception : {}", e.getConstraintViolations());
        return HttpResponse.toResponseEntity(e);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<HttpResponse> handleValidationException(MethodArgumentNotValidException e) {
        log.error("handleValidationException throw Exception : {}", e.getBindingResult().getFieldErrors());
        return HttpResponse.toResponseEntity(e);
    }

    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<HttpResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw Exception : {}", e.getResponseCode());
        return HttpResponse.toResponseEntity(e.getResponseCode());
    }
}
