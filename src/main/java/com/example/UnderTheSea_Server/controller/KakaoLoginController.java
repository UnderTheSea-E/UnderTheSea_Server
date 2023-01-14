package com.example.UnderTheSea_Server.controller;

import com.example.UnderTheSea_Server.model.PostUserReq;
import com.example.UnderTheSea_Server.service.KakaoUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor

public class KakaoLoginController {
    private final KakaoUserService kakaoUserService;

    @GetMapping("/user/kakao/callback")
    public KakaoUserInfoDto kakaoLogin(@RequestParam PostUserReq postUserReq, HttpServletResponse response) throws JsonProcessingException {
        return kakaoUserService.kakaoLogin(postUserReq, response);
    }

}
