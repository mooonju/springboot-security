package com.hospital.review.service;

import com.hospital.review.domain.User;
import com.hospital.review.domain.dto.UserDto;
import com.hospital.review.domain.dto.UserJoinRequest;
import com.hospital.review.exception.ErrorCode;
import com.hospital.review.exception.HospitalReviewException;
import com.hospital.review.repository.UserRepository;
import com.hospital.review.untils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String secretKey;
    private long expireTimeMs = 1000 * 60 * 60;

    public UserDto join(UserJoinRequest request) {
        // 비즈니스 로직 - 회원 가입

        // 회원 userName(id) 중복 check
        // 중복이면 회원가입 X -> Exception 발생
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user -> {
                    throw new HospitalReviewException(ErrorCode.DUPLICATED_USER_NAME,
                            String.format("Username:%s", request.getUserName()));
                });

        // 중복 체크 통과하면 회원 가입 -> .save()
        User savedUser = userRepository.save(request.toEntity());

        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .emailAddress(savedUser.getEmailAddress())
                .build();
    }

    public String login(String userName, String password) {

        //userName 있는지 여부 확인
        //없으면 NOT_FOUND 에러 발생
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new HospitalReviewException(ErrorCode.NOT_FOUND, String.format("%s는 가입된 적이 없습니다.", userName)));

        //password 일치하는지 여부 확인
        if(!encoder.matches(password, user.getPassword())){
            throw new HospitalReviewException(ErrorCode.INVALID_PASSWORD, "userName 또는 password 가 잘못 되었습니다.");
        }

        //두가지 확인이 통과하면 토큰 발행
        return JwtTokenUtil.createToken(userName, secretKey, expireTimeMs);
    }
}
