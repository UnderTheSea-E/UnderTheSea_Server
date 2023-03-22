package com.example.UnderTheSea_Server.service;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.domain.Friend;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.model.GetFriendRes;
import com.example.UnderTheSea_Server.model.GetUserRes;
import com.example.UnderTheSea_Server.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class FriendService {
    public final FriendRepository friendRepository;
    //@Transactional
    public GetFriendRes selectFriends(User userByJwt) throws BaseException {
        try{
            List<Friend> friends = friendRepository.findByUser1(userByJwt);
            System.out.println(friends.size());
            List<User> users = new ArrayList<>();
            for(int i = 0; i < friends.size(); i++){
                users.add(friends.get(i).getUser2());
            }
            return new GetFriendRes(users);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
