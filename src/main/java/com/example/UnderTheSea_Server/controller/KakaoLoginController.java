package com.example.UnderTheSea_Server.controller;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.config.BaseResponse;
import com.example.UnderTheSea_Server.model.PostUserReq;
import com.example.UnderTheSea_Server.model.PostUserRes;
import com.example.UnderTheSea_Server.service.KakaoUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class KakaoLoginController {
    private final KakaoUserService kakaoUserService;

    /**
     * Post User API (kakao)
     * [POST] /login/kakao
     * @return BaseResponse<PostUserRes>
     */
    @RequestMapping(value = "/login/kakao")
    public BaseResponse<PostUserRes> kakaoLogin(@RequestBody PostUserReq postUserReq, HttpServletResponse response) throws JsonProcessingException {
        try {
            PostUserRes postUserRes = kakaoUserService.kakaoLogin(postUserReq, response);
            return new BaseResponse<>(postUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
