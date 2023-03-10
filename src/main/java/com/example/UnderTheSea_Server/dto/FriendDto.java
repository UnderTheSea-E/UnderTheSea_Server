package com.example.UnderTheSea_Server.dto;

import com.example.UnderTheSea_Server.domain.Friend;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.domain.UserStatus;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;

@Repository
public class FriendDto {

    private Timestamp created_at = new Timestamp(new Date().getTime());
    private Timestamp updated_at = new Timestamp(new Date().getTime());

    public Friend insertFriend(User user1, User user2) {
        Friend friendEntity = Friend.builder()
                .user1(user1)
                .user2(user2)
                .status(UserStatus.ACTIVE)
                .created_at(created_at)
                .updated_at(updated_at)
                .build();
        return friendEntity;
    }

}
