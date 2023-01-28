package com.example.UnderTheSea_Server.controller;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.config.BaseResponse;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.model.PutCharacterReq;
import com.example.UnderTheSea_Server.model.PutCharacterRes;
import com.example.UnderTheSea_Server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Post Character API
     * [POST] /character
     * @return BaseResponse<PostCharacterRes>
     */
    //@ResponseBody
    @PutMapping("/character")
    public BaseResponse<PutCharacterRes> createCharacter(@RequestBody PutCharacterReq putCharacterReq) {
        if(putCharacterReq.character_id == null){
            return new BaseResponse<>(PUT_CHARACTER_EMPTY_ID);
        }

        if(putCharacterReq.character_id< 0){
            return new BaseResponse<>(PUT_CHARACTER_WRONG_ID);
        }

        if(putCharacterReq.character_name.isEmpty()){
            return new BaseResponse<>(PUT_CHARACTER_EMPTY_NAME);
        }

        if(putCharacterReq.character_name.length() > 10){
            return new BaseResponse<>(PUT_CHARACTER_LENGTH_NAME);
        }

        try{
            User userByJwt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //System.out.println(userByJwt);
            PutCharacterRes putCharacterRes = userService.createCharacter(userByJwt, putCharacterReq);
            return new BaseResponse<>(putCharacterRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
