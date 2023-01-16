package com.example.UnderTheSea_Server.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PostUserReq {
    public String accessToken;
    public String accessExpiration;
    public String refreshToken;
    public String refreshExpiration;
}
