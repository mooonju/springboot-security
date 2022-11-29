package com.hospital.review.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
// 발생하는 에러코드를 미리 담아놓는 클래스
public enum ErrorCode {
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "UserName not found"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "");


    private HttpStatus status;
    private String message;
}
