package com.hospital.review.domain.dto;

import com.hospital.review.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
// 회원 가입시 입력받을 정보
public class UserJoinRequest {
    private String userName;
    private String password;
    private String emailAddress;

    public User toEntity() {
        return User.builder()
                .userName(this.userName)
                .password(this.password)
                .emailAddress(this.emailAddress)
                .build();
    }
}
