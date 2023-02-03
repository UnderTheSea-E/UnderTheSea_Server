package com.example.UnderTheSea_Server.controller;


import com.example.UnderTheSea_Server.config.BaseException;
import com.example.UnderTheSea_Server.config.BaseResponse;
import com.example.UnderTheSea_Server.model.PostRecordReq;
import com.example.UnderTheSea_Server.model.PostRecordRes;
import com.example.UnderTheSea_Server.service.RecordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.example.UnderTheSea_Server.config.BaseResponseStatus.*;

public class RecordController {

    private final RecordService recordService;
    private final JwtService jwtService;


    //@ResponseBody
    @PostMapping("/records")
    public BaseResponse<PostRecordRes> createRecord(@RequestBody PostRecordReq postRecordReq) {
        if(postRecordReq.img_url == null){
            return new BaseResponse<>(POST_RECORD_EMPTY_IMGURL);
        }
        if(postRecordReq.content.isEmpty()){
            return new BaseResponse<>(POST_RECORD_EMPTY_CONTENT);
        }
        if(postRecordReq.satisfaction == null){
            return new BaseResponse<>(POST_RECORD_EMPTY_SATISFACTION);
        }

        try{

            PostRecordRes postRecordRes = recordService.createRecord(postRecordReq);
            return new BaseResponse<>(postRecordRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
