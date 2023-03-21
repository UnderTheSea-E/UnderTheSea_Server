package com.example.UnderTheSea_Server.service;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.domain.Friend;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.dto.FriendDto;
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
    public final FriendDto friendDto;
    public final UserRepository userRepository;

    //친구 불러오기
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


    //친구 저장하기
    @Transactional
    public PostFriendRes createFriend(User userByJwt, String email) throws BaseException { // 메일여기는 이메일
        try{

            //이메일 받기
            User friend = userRepository.findByEmail(email); // 이메일로 받아서, 그 이메일에 해당하는 아이디를 찾을 것

            //Long your_id = friend.getUserId(); //받은 이메일에 맞는 user_ID 가져오기

            //계획 db에 insert 하기
            Friend friends = friendRepository.save(
                    friendDto.insertFriend(
                            userByJwt,
                            friend // 여기 저장하는건 이메일에 해당하는 그 아이디 맞음
                            ));

            return new PostFriendRes(friends.getFriend_id());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
