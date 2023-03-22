package com.example.UnderTheSea_Server.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PostUserRes {
    public Long id;
    public String nickname;
    public String email;
    public String profileImgUrl;
    public String firebaseJwt;

}
