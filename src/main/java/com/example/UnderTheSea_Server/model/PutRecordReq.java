package com.example.UnderTheSea_Server.model;

/* PUT method : 기록 정보를 Update 클라이언트>서버  */

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public class PutRecordReq {
    public Long record_id;
    public String img_url;
    public int satisfaction;
    public String content;
}
