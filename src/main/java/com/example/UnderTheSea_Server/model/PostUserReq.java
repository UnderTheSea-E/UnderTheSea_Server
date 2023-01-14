package com.example.UnderTheSea_Server.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostUserReq {
    public String accessToken;
    public String accessExpiration;
    public String refreshToken;
    public String refreshExpiration;
}
