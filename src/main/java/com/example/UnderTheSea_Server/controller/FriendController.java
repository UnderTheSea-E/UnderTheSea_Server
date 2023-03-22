package com.example.UnderTheSea_Server.controller;


import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.config.BaseResponse;
import com.example.UnderTheSea_Server.domain.Friend;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.model.GetFriendRes;
import com.example.UnderTheSea_Server.model.GetPlanRes;
import com.example.UnderTheSea_Server.model.PostFriendRes;
import com.example.UnderTheSea_Server.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.*;

@RestController
@RequiredArgsConstructor
public class FriendController {
    public final FriendService friendService;
    /**
     * Get Friend API
     * [GET] /friend
     * @return BaseResponse<GetFriendRes>
     */
    @GetMapping("/friends")
    public BaseResponse<GetFriendRes> createFriend() {
        try{
            User userByJwt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            GetFriendRes getFriendRes = friendService.selectFriends(userByJwt);
            return new BaseResponse<>(getFriendRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    

}