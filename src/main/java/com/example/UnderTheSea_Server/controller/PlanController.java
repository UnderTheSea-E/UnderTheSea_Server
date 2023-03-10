package com.example.UnderTheSea_Server.controller;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.config.BaseResponse;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.model.*;
import com.example.UnderTheSea_Server.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.*;

@RestController
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;
    
    /**
     * Post Plan API
     * [POST] /plans
     * @return BaseResponse<PostPlanRes>
     */
    @PostMapping("/plans")
    public BaseResponse<PostPlanRes> createPlan(@RequestBody PostPlanReq postPlanReq) {
        if(postPlanReq.title.isEmpty()){
            return new BaseResponse<>(POST_PLAN_EMPTY_FRIEND);
        }
        if(postPlanReq.friend < 0){
            return new BaseResponse<>(POST_PLAN_EMPTY_FRIEND);
        }
        if(postPlanReq.content.isEmpty()){
            return new BaseResponse<>(POST_PLAN_EMPTY_CONTENT);
        }
        if(postPlanReq.date == null){
            return new BaseResponse<>(POST_PLAN_EMPTY_DATE);
        }

        try{
            //System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
            User userByJwt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userIdByJwt = userByJwt.getUserId();

            PostPlanRes postPlanRes = planService.createPlan(userIdByJwt, postPlanReq);
            return new BaseResponse<>(postPlanRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 날짜에 해당하는 모든 일정
     * Get Plans API
     * [GET] /plans
     * @return BaseResponse<GetPlansRes>
     */
    @GetMapping("/plans")
    public BaseResponse<GetPlansRes> selectPlans(@RequestParam("date\") @DateTimeFormat(pattern = \"yyyy-MM-dd") LocalDate date) {
        if(date == null){
            return new BaseResponse<>(GET_PLAN_EMPTY_DATE);
        }

        try{
            //System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
            User userByJwt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            GetPlansRes getPlansRes = planService.selectPlans(userByJwt, date);
            return new BaseResponse<>(getPlansRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 특정 식별자에 해당하는 상세 일정
     * Get Plan API
     * [GET] /plans
     * @return BaseResponse<GetPlanRes>
     */
    //@ResponseBody
    @GetMapping("/plan")
    public BaseResponse<GetPlanRes> selectPlan(@RequestParam("plan_id") Long plan_id) {
        if(plan_id == null){
            return new BaseResponse<>(GET_PLAN_EMPTY_ID);
        }

        if(plan_id < 0){
            return new BaseResponse<>(GET_PLAN_WRONG_ID);
        }

        try{
            //System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
            User userByJwt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            GetPlanRes getPlanRes = planService.selectPlan(userByJwt, plan_id);
            return new BaseResponse<>(getPlanRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * Put Plan API
     * [PUT] /plan
     * @return BaseResponse<PutPlanRes>
     */
    @PutMapping("/plans")
    public BaseResponse<PutPlanRes> updatePlans(@RequestBody PutPlanReq putPlanReq) {
        if(putPlanReq.plan_id == null){
            return new BaseResponse<>(PUT_PLAN_EMPTY_ID);
        }

        if(putPlanReq.plan_id < 0){
            return new BaseResponse<>(PUT_PLAN_WRONG_ID);
        }

        if(putPlanReq.friend_id == null){
            return new BaseResponse<>(POST_PLAN_EMPTY_FRIEND);
        }

        if(putPlanReq.friend_id < 0){
            return new BaseResponse<>(POST_PLAN_WRONG_FRIEND);
        }

        if(putPlanReq.content.isEmpty()){
            return new BaseResponse<>(POST_PLAN_EMPTY_CONTENT);
        }


        try{
            //System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
            User userByJwt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            PutPlanRes putPlanRes = planService.updatePlans(userByJwt, putPlanReq);

            return new BaseResponse<>(putPlanRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
