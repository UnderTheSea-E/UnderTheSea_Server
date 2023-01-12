package com.example.UnderTheSea_Server.jwt;

import com.example.UnderTheSea_Server.repository.UserRepository;
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
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private static String jwtSecretKey = "secretkey";
    //토큰 유효시간(30분)
    private static final long tokenValidTime = 30 * 60 * 1000L;
    //jwt 토큰 생성
    private final UserDetailsService userDetailsService;

    //객체 초기화, secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init(){
        jwtSecretKey = Base64.getEncoder().encodeToString(jwtSecretKey.getBytes());
    }
    //JWT 토큰 생성
    public static String createToken(String userPk, List<String> roles){
        //JWT payload에 저장되는 정보 단위로 user 식별하는 값을 넣음
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles); //key-value 쌍으로 정보 저장
        Date now = new Date();
        Date expiryDay = new Date(now.getTime() + tokenValidTime);

        return Jwts.builder()
                .setClaims(claims) //정보 저장
                .setIssuedAt(now)
                .setExpiration(expiryDay)
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey) //사용할 암호화 알고리즘
                .compact();
    }

    //JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, " ", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody().getSubject();
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



}
