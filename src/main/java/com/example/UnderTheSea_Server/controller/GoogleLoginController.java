package com.example.UnderTheSea_Server.controller;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.config.BaseResponse;
import com.example.UnderTheSea_Server.model.PostGUserReq;
import com.example.UnderTheSea_Server.model.PostUserReq;
import com.example.UnderTheSea_Server.model.PostUserRes;
import com.example.UnderTheSea_Server.service.GoogleUserService;
import com.example.UnderTheSea_Server.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class GoogleLoginController {
    public final GoogleUserService googleUserService;

    /**
     * Post User API (google)
     * [POST] /login/google
     * @return BaseResponse<PostGUserRes>
     */
    @RequestMapping(value = "/login/google")
    public BaseResponse<PostUserRes> googleLogin(@RequestBody PostGUserReq postGUserReq, HttpServletResponse response) throws JsonProcessingException {
        try {
            PostUserRes postUserRes = googleUserService.googleLogin(postGUserReq, response);
            return new BaseResponse<>(postUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}

