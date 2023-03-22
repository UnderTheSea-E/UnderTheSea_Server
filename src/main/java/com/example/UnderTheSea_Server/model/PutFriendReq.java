package com.example.UnderTheSea_Server.model;


import com.example.UnderTheSea_Server.domain.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PutFriendReq {
    public Long friend_id;
    public User user2;
}
