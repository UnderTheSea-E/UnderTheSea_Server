package com.example.UnderTheSea_Server.model;

import com.example.UnderTheSea_Server.domain.UserStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class GetUserRes {
    public Long userId;
    public String email;
    public String nickname;
    public String profileImgUrl;
    public Long characterId;
    public String characterName;
    public UserStatus status;
}
