package com.example.UnderTheSea_Server.service;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.domain.Friend;
import com.example.UnderTheSea_Server.domain.Plan;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.dto.PlanDto;
import com.example.UnderTheSea_Server.model.*;
import com.example.UnderTheSea_Server.repository.FriendRepository;
import com.example.UnderTheSea_Server.repository.PlanRepository;
import com.example.UnderTheSea_Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;
    private final PlanDto planDto;
    private final UserRepository userRepository;

    private Timestamp updated_at = new Timestamp(new Date().getTime());

    //계획 저장하기
    //@Transactional
    public PostPlanRes createPlan(Long userIdByJwt, PostPlanReq postPlanReq) throws BaseException {
        try{
            //현재 로그인한 사용자 불러오기
            User user = userRepository.getById(userIdByJwt);
            //친구 불러오기
            //User friend = userRepository.getById(postPlanReq.friend);

            //계획 db에 insert하기
            Plan plan = planRepository.save(
                    planDto.insertPlan(
                            user,
                            postPlanReq.title,
                            postPlanReq.friend,
                            postPlanReq.content,
                            postPlanReq.date));

            return new PostPlanRes(plan.getPlanId());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //날짜에 해당하는 계획들 불러오기
    @Transactional
    public GetPlansRes selectPlans(User userByJwt, LocalDate date) throws BaseException {
        try{
            //계획 db에서 select하기
            List<Plan> plans = planRepository.findByUserAndDate(userByJwt, date);

            return new GetPlansRes(plans);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public GetPlanRes selectPlan(User userByJwt, Long plan_id) throws BaseException {
        try{
            //계획 db에서 select하기
            Plan plan = planRepository.findByUserAndPlanId(userByJwt, plan_id);

            String friend_email = userRepository.findById(plan.getFriend()).get().getEmail();

            return new GetPlanRes(plan.getPlanId(), plan.getTitle(), friend_email, plan.getContent(), plan.getDate(), plan.getStatus());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //계획 수정하기
    public PutPlanRes updatePlans(User userByJwt, PutPlanReq putPlanReq) throws BaseException {
        try{
            //User friend = userRepository.findById(putPlanReq.friend).get();

            //계획 db에서 update하기
            planRepository.updatePlan(putPlanReq.title,
                    putPlanReq.friend,
                    putPlanReq.content,
                    updated_at,
                    putPlanReq.planId
            );

            return new PutPlanRes(putPlanReq.planId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
