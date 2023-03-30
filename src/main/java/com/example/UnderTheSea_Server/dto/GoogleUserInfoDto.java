package com.example.UnderTheSea_Server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleUserInfoDto {
    private String nickname;
    private String email;
    private String profileImgUrl;

    public GoogleUserInfoDto(String nickname, String email, String profileImgUrl) {
        this.nickname = nickname;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
    }
}
