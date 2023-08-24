package com.example.UnderTheSea_Server.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PostUserRes {
    public Long userId;
    public String nickname;
    public String email;
    public String profileImgUrl;
    public String firebaseJwt;

}
