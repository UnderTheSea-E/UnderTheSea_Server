package com.example.UnderTheSea_Server.model;

import com.example.UnderTheSea_Server.domain.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class GetFriendRes {
    public List<User> friend;
}
