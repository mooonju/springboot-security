package com.hospital.review.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
// 회원 가입이 성공했을때 저장된 정보를 띄움(보안상 패스워드 생략)
public class UserJoinResponse {
    private String userName;
    private String emailAddress;
}
