package com.example.UnderTheSea_Server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

//jwt 토큰 방식을 이용해서 카카오 로그인 구현
//회원가입은 Email,Nickname,Password로 하고, 로그인은 Email,Password로 진행
@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {
    private String nickname;
    private String email;
    private String profileImgUrl;

    public KakaoUserInfoDto(String nickname, String email, String profileImgUrl) {
        this.nickname = nickname;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
    }
}
