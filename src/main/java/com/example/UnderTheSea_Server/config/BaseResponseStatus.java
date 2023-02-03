package com.example.UnderTheSea_Server.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */

@Getter
public enum BaseResponseStatus {
    /**
     * 200: 요청 성공
     */
    SUCCESS(true, 200, "요청에 성공하였습니다."),

    /**
     * 400: Request 오류
     */
    // [POST] /plans
    POST_RECORD_EMPTY_IMGURL(false, 200, "기록할 사진을 업로드해주세요"),
    POST_RECORD_EMPTY_CONTENT(false, 200, "기록의 내용을 입력해주세요."),
    POST_RECORD_EMPTY_SATISFACTION(false, 200, "만족/불만족을 입력해주세요."),

    /**
     * 400: Response 오류
     */
    RESPONSE_ERROR(false, 400, "값을 불러오는데 실패하였습니다."),

    /**
     * 400: Database 오류
     */
    DATABASE_ERROR(false, 400, "데이터베이스 연결에 실패하였습니다."),

    /**
     * 400: Server 오류
     */
    SERVER_ERROR(false, 400, "서버 연결에 실패하였습니다."),

    ;

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

}
