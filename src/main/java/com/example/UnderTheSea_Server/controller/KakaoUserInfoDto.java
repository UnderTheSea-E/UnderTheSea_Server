package com.example.UnderTheSea_Server.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

//jwt 토큰 방식을 이용해서 카카오 로그인 구현
//회원가입은 Email,Nickname,Password로 하고, 로그인은 Email,Password로 진행

public class KakaoUserInfoDto {
    private Long id;
    private String nickname;
    private String email;

    public KakaoUserInfoDto(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }
}
