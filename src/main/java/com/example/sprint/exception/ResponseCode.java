package com.example.sprint.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ResponseCode implements Serializable {

    /** ( 200 OK / 201 CREATED ) 요청성공 */
    SUCCESS(OK, "성공!"),

    /** 400 BAD_REQUEST 잘못된 요청 */

    VALIDATION_FAIL(BAD_REQUEST,"VALIDATION_FAIL"),

    // validation, integrity 위반
    WRONG_INTEGRITY(BAD_REQUEST, "DB 무결성 위반."),

    /** 401 UNAUTHORIZED 인증되지 않은 사용자 */
    LOGIN_REQUIRED(UNAUTHORIZED, "로그인이 필요합니다."),
    NOT_AUTHORED(UNAUTHORIZED, "해당 게시글의 작성자가 아닙니다."),

    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다."),

    /** 404 NOT_FOUND  리소스를 찾을 수 없음 */
    AUTHOR_NOT_FOUND(NOT_FOUND, "해당 저자를 찾을 수 없습니다.");



    private final HttpStatus httpStatus;
    private final String message;

    ResponseCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
