package com.example.UnderTheSea_Server.controller;

import com.example.UnderTheSea_Server.model.PostUserReq;
import com.example.UnderTheSea_Server.service.KakaoUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class KakaoLoginController {
    private final KakaoUserService kakaoUserService;

    @RequestMapping(value = "/login/kakao")
    public KakaoUserInfoDto kakaoLogin(@RequestBody PostUserReq postUserReq, HttpServletResponse response) throws JsonProcessingException {
        return kakaoUserService.kakaoLogin(postUserReq, response);
    }

}
