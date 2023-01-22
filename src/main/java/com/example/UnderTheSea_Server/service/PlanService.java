package com.example.UnderTheSea_Server.service;

import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.domain.Friend;
import com.example.UnderTheSea_Server.domain.Plan;
import com.example.UnderTheSea_Server.domain.User;
import com.example.UnderTheSea_Server.dto.PlanDto;
import com.example.UnderTheSea_Server.model.GetPlanReq;
import com.example.UnderTheSea_Server.model.GetPlanRes;
import com.example.UnderTheSea_Server.model.PostPlanReq;
import com.example.UnderTheSea_Server.model.PostPlanRes;
import com.example.UnderTheSea_Server.repository.FriendRepository;
import com.example.UnderTheSea_Server.repository.PlanRepository;
import com.example.UnderTheSea_Server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                            postPlanReq.recommend_id,
                            postPlanReq.content,
                            postPlanReq.date));

            return new PostPlanRes(plan.getPlan_id());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //날짜에 해당하는 계획들 불러오기
    @Transactional
    public GetPlanRes selectPlans(Long userIdByJwt, GetPlanReq getCourseReq) throws BaseException {
        try{
            //현재 로그인한 사용자 불러오기
            User user = userRepository.getById(userIdByJwt);

            //계획 db에서 select하기
            List<Plan> plans = planRepository.findByDateAndUser(getCourseReq.date, user);

            return new GetPlanRes(plans);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
