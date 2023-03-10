package com.example.UnderTheSea_Server.controller;


import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.config.BaseResponse;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.model.PostFriendRes;
import com.example.UnderTheSea_Server.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.*;

@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    /**
     * Post Friend API
     * [POST] /friends
     * @return BaseResponse<PostFriendRes>
     */
    @PostMapping("/friends")
    public BaseResponse<PostFriendRes> createFriend(@RequestParam("your_id") long your_id) {
        if(your_id < 0){
            return new BaseResponse<>(POST_PLAN_EMPTY_FRIEND);
        }

        try{

            User userByJwt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            PostFriendRes postFriendRes = friendService.createFriend(userByJwt, your_id);
            return new BaseResponse<>(postFriendRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}