package com.example.UnderTheSea_Server.service;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.domain.Friend;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.dto.FriendDto;
import com.example.UnderTheSea_Server.model.PostFriendRes;
import com.example.UnderTheSea_Server.repository.FriendRepository;
import com.example.UnderTheSea_Server.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.DATABASE_ERROR;

public class FriendService {

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
