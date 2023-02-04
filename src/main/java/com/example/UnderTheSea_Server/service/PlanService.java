package com.example.UnderTheSea_Server.service;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.domain.Plan;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.dto.PlanDto;
import com.example.UnderTheSea_Server.model.*;
import com.example.UnderTheSea_Server.repository.PlanRepository;
import com.example.UnderTheSea_Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class PlanService {
    public final PlanRepository planRepository;
    public final PlanDto planDto;
    public final UserRepository userRepository;

    //계획 저장하기
    @Transactional
    public PostPlanRes createPlan(Long userIdByJwt, PostPlanReq postPlanReq) throws BaseException {
        try{
            //현재 로그인한 사용자 불러오기
            User user = userRepository.getById(userIdByJwt);
            //친구 불러오기
            User friend = userRepository.getById(postPlanReq.friend_id);

            //계획 db에 insert하기
            Plan plan = planRepository.save(
                    planDto.insertPlan(
                            user,
                            friend,
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

            return new GetPlanRes(plan);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //계획 수정하기
    public PutPlanRes updatePlans(User userByJwt, PutPlanReq putPlanReq) throws BaseException {
        try{
            User friend = userRepository.findById(putPlanReq.friend_id).get();

            //계획 db에서 update하기
            planRepository.updateFriendAndContent(
                    friend,
                    putPlanReq.content,
                    putPlanReq.plan_id,
                    userByJwt
            );

            return new PutPlanRes(putPlanReq.plan_id);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
