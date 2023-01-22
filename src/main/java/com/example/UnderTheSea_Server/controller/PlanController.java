package com.example.UnderTheSea_Server.controller;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.config.BaseResponse;
import com.example.UnderTheSea_Server.config.BaseResponseStatus;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.jwt.JwtAuthenticationFilter;
import com.example.UnderTheSea_Server.model.GetPlanReq;
import com.example.UnderTheSea_Server.model.GetPlanRes;
import com.example.UnderTheSea_Server.model.PostPlanReq;
import com.example.UnderTheSea_Server.model.PostPlanRes;
import com.example.UnderTheSea_Server.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
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
    //@ResponseBody
    @PostMapping("/plans")
    public BaseResponse<PostPlanRes> createPlan(@RequestBody PostPlanReq postPlanReq) {

        if(postPlanReq.friend_id < 0){
            return new BaseResponse<>(POST_PLAN_EMPTY_FRIEND);
        }
        if(postPlanReq.content.isEmpty()){
            return new BaseResponse<>(POST_PLAN_EMPTY_CONTENT);
        }
        if(postPlanReq.date == null){
            return new BaseResponse<>(POST_PLAN_EMPTY_DATE);
        }

        try{
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
            User userByJwt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userIdByJwt = userByJwt.getUserId();

            PostPlanRes postPlanRes = planService.createPlan(userIdByJwt, postPlanReq);
            return new BaseResponse<>(postPlanRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * Get Plan API
     * [GET] /plans
     * @return BaseResponse<GetPlanRes>
     */
    //@ResponseBody
    @GetMapping("/plans")
    public BaseResponse<GetPlanRes> selectPlans(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        if(date == null){
            return new BaseResponse<>(GET_PLAN_EMPTY_DATE);
        }

        try{
            //System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());
            User userByJwt = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            GetPlanRes getPlanRes = planService.selectPlans(userByJwt, date);
            return new BaseResponse<>(getPlanRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
