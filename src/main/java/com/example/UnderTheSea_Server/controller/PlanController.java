//package com.example.UnderTheSea_Server.controller;
//
//import com.example.UnderTheSea_Server.config.BaseException;
//import com.example.UnderTheSea_Server.config.BaseResponse;
//import com.example.UnderTheSea_Server.config.BaseResponseStatus;
//import com.example.UnderTheSea_Server.model.PostPlanReq;
//import com.example.UnderTheSea_Server.model.PostPlanRes;
//import com.example.UnderTheSea_Server.service.PlanService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import static com.example.UnderTheSea_Server.config.BaseResponseStatus.*;
//
//@RestController
//@RequiredArgsConstructor
//public class PlanController {
//    private final PlanService planService;
//    private final JwtService jwtService;
//
//    /**
//     * Post Plan API
//     * [POST] /plans
//     * @return BaseResponse<PostPlanRes>
//     */
//    //@ResponseBody
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@PostMapping("/plans")
//    public BaseResponse<PostPlanRes> createPlan(@RequestBody PostPlanReq postPlanReq) {
//        if(postPlanReq.friend_id < 0){
//            return new BaseResponse<>(POST_PLAN_EMPTY_FRIEND);
//        }
//        if(postPlanReq.content.isEmpty()){
//            return new BaseResponse<>(POST_PLAN_EMPTY_CONTENT);
//        }
//        if(postPlanReq.date == null){
//            return new BaseResponse<>(POST_PLAN_EMPTY_DATE);
//        }
//
//        try{
//            Long userIdByJwt = jwtService.getUserId();
//            PostPlanRes postPlanRes = planService.createPlan(userIdByJwt, postPlanReq);
//            return new BaseResponse<>(postPlanRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//
//}
//
//
