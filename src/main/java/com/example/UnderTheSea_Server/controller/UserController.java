package com.example.UnderTheSea_Server.controller;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.config.BaseResponse;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.model.*;
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
     * Get Character API
     * [GET] /user
     * @return BaseResponse<GetMileageRes>
     * 사용자 정보 select
     */
    @GetMapping("/userInfo")
    public BaseResponse<GetUserRes> getUser() {
        try{
            User userByJwt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            GetUserRes getUserRes = userService.selectUser(userByJwt);
            return new BaseResponse<>(getUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * Put Character API
     * [PUT] /character
     * @return BaseResponse<PutCharacterRes>
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

            PutCharacterRes putCharacterRes = userService.createCharacter(userByJwt, putCharacterReq);
            return new BaseResponse<>(putCharacterRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * Put Mileage API
     * [PUT] /mileage
     * @return BaseResponse<PutMileageRes>
     * 마일리지 update
     */
    //@ResponseBody
    @PutMapping("/mileage")
    public BaseResponse<PutMileageRes> updateMileage(@RequestBody PutMileageReq putMileageReq) {
        if(putMileageReq.mileage == null){
            return new BaseResponse<>(PUT_MILEAGE_EMPTY_COUNT);
        }

        if(putMileageReq.mileage <= 0){
            return new BaseResponse<>(PUT_MILEAGE_WRONG_COUNT);
        }

        try{
            User userByJwt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            PutMileageRes putMileageRes = userService.updateMileage(userByJwt, putMileageReq);
            return new BaseResponse<>(putMileageRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
