package com.example.UnderTheSea_Server.jwt;

import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private static String jwtSecretKey = "secretkeyforunderthesea1234567890";
    //토큰 유효시간
    private static final long accessTokenValidTime = 300 * 60 * 1000L;
    private static final long refreshTokenValidTime = 3000 * 60 * 1000L;
    //jwt 토큰 생성
    private final UserService userDetailsService;

    //객체 초기화, secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init(){
        jwtSecretKey = Base64.getEncoder().encodeToString(jwtSecretKey.getBytes());
    }
    //JWT 토큰 생성
    public static Token createToken(User userPk, String roles){
        //JWT payload에 저장되는 정보 단위로 user 식별하는 값을 넣음
        Claims claims = Jwts.claims().setSubject(userPk.getUserId().toString());
        claims.put("roles", roles); //key-value 쌍으로 정보 저장
        Date now = new Date();

        //Access Token
        String accessToken = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + accessTokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();

        //Refresh Token
        String refreshToken =  Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();

        return Token.builder().accessToken(accessToken).refreshToken(refreshToken).key(userPk.getUserId().toString()).build();
    }

    //JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String username){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(username));
        return new UsernamePasswordAuthenticationToken(userDetails, " ", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String username) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(username).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String validateRefreshToken(RefreshToken refreshTokenObj, HttpServletResponse response){
        // refresh 객체에서 refreshToken 추출
        String refreshToken = refreshTokenObj.getRefreshToken();

        try {
            // 검증
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(refreshToken);

            //refresh 토큰의 만료시간이 지나지 않았을 경우, 새로운 access 토큰을 생성합니다.
            if (!claims.getBody().getExpiration().before(new Date())) {
                recreationAccessToken(claims.getBody().get("sub").toString(), claims.getBody().get("roles"));
            }
        }catch (Exception e) {
            //refresh 토큰이 만료되었을 경우, 로그인이 필요합니다.
            return null;
        }
        return null;
    }

    public String recreationAccessToken(String userId, Object roles){

        Claims claims = Jwts.claims().setSubject(userId); // JWT payload 에 저장되는 정보단위
        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();

        //Access Token
        String accessToken = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + accessTokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();

        return accessToken;
    }

}
