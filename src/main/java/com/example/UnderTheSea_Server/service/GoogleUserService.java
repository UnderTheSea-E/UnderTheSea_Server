package com.example.UnderTheSea_Server.service;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.dto.GoogleUserInfoDto;
import com.example.UnderTheSea_Server.dto.UserDto;
import com.example.UnderTheSea_Server.jwt.JwtTokenProvider;
import com.example.UnderTheSea_Server.jwt.Token;
import com.example.UnderTheSea_Server.model.PostGUserReq;
import com.example.UnderTheSea_Server.model.PostUserRes;
import com.example.UnderTheSea_Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class GoogleUserService {
    private final UserRepository userRepository;
    private User googleUser;
    public UserDto userDto;

    private User registerGoogleUserIfNeed(GoogleUserInfoDto googleUserInfo) {
        // DB 에 중복된 email이 있는지 확인
        String googleEmail = googleUserInfo.getEmail();
        String nickname = googleUserInfo.getNickname();
        String profile = googleUserInfo.getProfileImgUrl();
        googleUser = userRepository.findByEmail(googleEmail);

        if (googleUser == null) {
            googleUser = userRepository.save(userDto.insertUser(googleEmail, nickname, profile));
        }

        return googleUser;
    }

    private void googleUsersAuthorizationInput(HttpServletResponse response) {
        // response header에 token 추가
        Token token = JwtTokenProvider.createToken(googleUser, "user");
        //jwtService.login(token);
        response.addHeader("Authorization", "BEARER" + " " + token.getAccessToken());
        response.addHeader("Refresh", token.getRefreshToken());
    }

    public PostUserRes googleLogin(PostGUserReq postGUserReq, HttpServletResponse response) throws BaseException {
        try {
            // 넘겨받은 구글 사용자 정보
            GoogleUserInfoDto googleUserInfo = new GoogleUserInfoDto(postGUserReq.nickname, postGUserReq.email, postGUserReq.profileImgUrl);

            // 구글 사용자 회원가입(로그인) 처리
            User googleUser = registerGoogleUserIfNeed(googleUserInfo);

            // response Header에 JWT 토큰 추가
            //Authentication authentication = null;
            googleUsersAuthorizationInput(response);

            PostUserRes postUserRes = new PostUserRes(
                    googleUser.getUserId(),
                    googleUserInfo.getNickname(),
                    googleUserInfo.getEmail(),
                    googleUser.getProfileImgUrl(),
                    "any"
            );
            return postUserRes;
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
