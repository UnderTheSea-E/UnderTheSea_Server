package com.example.UnderTheSea_Server.service;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.dto.KakaoUserInfoDto;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.dto.KakaoUserDto;
import com.example.UnderTheSea_Server.jwt.JwtService;
import com.example.UnderTheSea_Server.jwt.JwtTokenProvider;
import com.example.UnderTheSea_Server.jwt.Token;
import com.example.UnderTheSea_Server.model.PostUserReq;
import com.example.UnderTheSea_Server.model.PostUserRes;
import com.example.UnderTheSea_Server.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class KakaoUserService {
    private final UserRepository userRepository;
    public final KakaoUserDto kakaoUserDto;
    public final JwtService jwtService;
    private User kakaoUser;

    private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        // responseBody에 있는 정보를 꺼냄
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        Long id = jsonNode.get("id").asLong();
        String email = jsonNode.get("kakao_account").get("email").asText();
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();
        String profile = jsonNode.get("properties")
                .get("profile_image").asText();

        return new KakaoUserInfoDto(nickname, email, profile);
    }

    private User registerKakaoUserIfNeed(KakaoUserInfoDto kakaoUserInfo) {
        // DB 에 중복된 email이 있는지 확인
        String kakaoEmail = kakaoUserInfo.getEmail();
        String nickname = kakaoUserInfo.getNickname();
        String profile = kakaoUserInfo.getProfileImgUrl();
        kakaoUser = userRepository.findByEmail(kakaoEmail);
                //.orElse(null);

        if (kakaoUser == null) {
            kakaoUser = userRepository.save(kakaoUserDto.insertUser(kakaoEmail, nickname, profile));
        }
        return kakaoUser;
    }

    private void kakaoUsersAuthorizationInput(HttpServletResponse response) {
        // response header에 token 추가
        Token token = JwtTokenProvider.createToken(kakaoUser, "user_id");
        //jwtService.login(token);
        response.addHeader("Authorization", "BEARER" + " " + token.getAccessToken());
    }


    public PostUserRes kakaoLogin(PostUserReq postUserReq, HttpServletResponse response) throws BaseException {
        try {
            // 토큰으로 카카오 API 호출
            KakaoUserInfoDto kakaoUserInfo = getKakaoUserInfo(postUserReq.accessToken);

            // 카카오 사용자 정보로 회원가입 처리
            User kakaoUser = registerKakaoUserIfNeed(kakaoUserInfo);

            // response Header에 JWT 토큰 추가
            //Authentication authentication = null;
            kakaoUsersAuthorizationInput(response);

            PostUserRes postUserRes = new PostUserRes(
                    kakaoUser.getUserId(),
                    kakaoUserInfo.getNickname(),
                    kakaoUserInfo.getEmail(),
                    kakaoUser.getProfileImgUrl()
            );
            return postUserRes;
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
