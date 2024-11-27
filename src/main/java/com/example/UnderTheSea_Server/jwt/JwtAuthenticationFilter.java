package com.example.UnderTheSea_Server.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //헤더에서 jwt 받아오기
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);//.split(" ")[1];
        //System.out.println(token.split(" ")[1]);
        //유효한 토큰인지 확인
        if(token != null && jwtTokenProvider.validateToken(token)){
            System.out.println("mp");
            //토큰 유효하면 토큰으로부터 유저 정보 받아오기 <인증>
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            System.out.println("nono");
            //SecurityContext에 Authentication 객체 저장 <인가>
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        else if(!jwtTokenProvider.validateToken(token)){
            String refresh = jwtTokenProvider.resolveRefreshToken((HttpServletRequest) request);
            if(refresh != null){
                RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(refresh).get();
                String accessToken = jwtTokenProvider.validateRefreshToken(refreshToken, (HttpServletResponse) response);
                if(accessToken != null) {
                    //토큰 유효하면 토큰으로부터 유저 정보 받아오기
                    Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                    //SecurityContext에 Authentication 객체 저장
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
