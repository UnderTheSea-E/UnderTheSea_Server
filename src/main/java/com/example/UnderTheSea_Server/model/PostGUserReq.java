package com.example.UnderTheSea_Server.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PostGUserReq {
    public String nickname;
    public String email;
    public String profileImgUrl;
}
