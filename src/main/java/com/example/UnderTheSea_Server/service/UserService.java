package com.example.UnderTheSea_Server.service;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.model.*;
import com.example.UnderTheSea_Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.DATABASE_ERROR;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private Timestamp updated_at = new Timestamp(new Date().getTime());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userRepository.findByUserId(Long.parseLong(username));
                //.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    //@Transactional
    public GetUserRes selectUser(User userByJwt) throws BaseException{
        try{
            //user 정보 가져오기
            GetUserRes getUserRes = new GetUserRes(
                    userByJwt.getUserId(),
                    userByJwt.getEmail(),
                    userByJwt.getNickname(),
                    userByJwt.getProfileImgUrl(),
                    userByJwt.getCharacterId(),
                    userByJwt.getCharacterName(),
                    userByJwt.getStatus());

            return getUserRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //@Transactional
    public PutCharacterRes createCharacter(User userByJwt, PutCharacterReq putCharacterReq) throws BaseException{
        try{
            //user table에서 사용자의 캐릭터 정보 update하기
            userRepository.updateCharacter(putCharacterReq.character_id, putCharacterReq.character_name, updated_at, userByJwt.getUserId());

            return new PutCharacterRes(putCharacterReq.character_id);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PutMileageRes updateMileage(User userByJwt, PutMileageReq putMileageReq) throws BaseException{
        try {
            userRepository.updateMileage(putMileageReq.mileage, updated_at, userByJwt.getUserId());

            return new PutMileageRes(putMileageReq.mileage);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}