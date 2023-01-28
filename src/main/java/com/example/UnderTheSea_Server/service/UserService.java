package com.example.UnderTheSea_Server.service;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.model.PutCharacterReq;
import com.example.UnderTheSea_Server.model.PutCharacterRes;
import com.example.UnderTheSea_Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.DATABASE_ERROR;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userRepository.findByUserId(Long.parseLong(username));
                //.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    //@Transactional
    public PutCharacterRes createCharacter(User userByJwt, PutCharacterReq putCharacterReq) throws BaseException{
        try{
            //user table에서 사용자의 캐릭터 정보 update하기
            userRepository.updateCharacterId(putCharacterReq.character_id, putCharacterReq.character_name, userByJwt.getUserId());

            return new PutCharacterRes(userByJwt.getUserId(), putCharacterReq.character_id);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}