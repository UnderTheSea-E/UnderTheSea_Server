package com.example.UnderTheSea_Server.controller;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.config.BaseResponse;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.model.*;
import com.example.UnderTheSea_Server.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.*;

@RestController
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    /**
     * Post Plan API
     * [POST] /plans
     * @return BaseResponse<PostPlanRes>
     */
    @PostMapping("/records")
    public BaseResponse<PostRecordRes> createPlan(@RequestBody PostRecordReq postRecordReq) {
        if(postRecordReq.content.isEmpty()){
            return new BaseResponse<>(POST_PLAN_EMPTY_CONTENT);
        }

        if(postRecordReq.date == null){
            return new BaseResponse<>(POST_PLAN_EMPTY_DATE);
        }

        try{
            //System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
            User userByJwt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userIdByJwt = userByJwt.getUserId();

            PostRecordRes postRecordRes = recordService.createRecord(userIdByJwt, postRecordReq);
            return new BaseResponse<>(postRecordRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * Post Plan API
     * [POST] /plans
     * @return BaseResponse<PostPlanRes>
     */
    @GetMapping("/records")
    public BaseResponse<GetRecordsRes> createPlan(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        if(date == null){
            return new BaseResponse<>(POST_PLAN_EMPTY_DATE);
        }

        try{
            //System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
            User userByJwt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //Long userIdByJwt = userByJwt.getUserId();

            GetRecordsRes postRecordRes = recordService.selectRecords(userByJwt , date);
            return new BaseResponse<>(postRecordRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * Post Plan API
     * [POST] /plans
     * @return BaseResponse<PostPlanRes>
     */
    @GetMapping("/record")
    public BaseResponse<GetRecordRes> createPlan(@RequestParam("record_id") Long record_id) {
        if(record_id == null){
            return new BaseResponse<>(GET_PLAN_EMPTY_ID);
        }

        try{
            //System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
            User userByJwt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //Long userIdByJwt = userByJwt.getUserId();

            GetRecordRes getRecordRes = recordService.selectRecord(userByJwt , record_id);
            return new BaseResponse<>(getRecordRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
