package com.hospital.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {
    private String resultCode;
    private T result;

    public static Response<Void> error(String resultCode) {
        return new Response<>(resultCode, null);
    } // 어떤 에러인지 알 수 알 수 있는 메소드

    public static <T> Response<T> success(T result) {
        return new Response<>("SUCCESS", result);
    } // 저장에 성공하면 메시지와 함께 저장 데이터 알려 주는 메소드

}
