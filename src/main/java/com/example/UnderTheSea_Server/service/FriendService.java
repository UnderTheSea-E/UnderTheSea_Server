package com.example.UnderTheSea_Server.service;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.domain.Friend;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.model.GetFriendRes;
import com.example.UnderTheSea_Server.model.GetUserRes;
import com.example.UnderTheSea_Server.model.PostFriendRes;
import com.example.UnderTheSea_Server.repository.FriendRepository;
import com.example.UnderTheSea_Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public final FriendRepository friendRepository;
    public final FriendDto friendDto;

    //계획 저장하기
    @Transactional
    public PostFriendRes createFriend(User userByJwt, Long your_id) throws BaseException {
        try{

            //친구 불러오기
            User friend = UserRepository.findByUserId(your_id);

            //계획 db에 insert하기
            Friend friends = friendRepository.save(
                    friendDto.insertFriend(
                            userByJwt,
                            your_id
                            ));

            return new PostFriendRes(friends.getFriend_id());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }



}
