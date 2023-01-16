package com.example.UnderTheSea_Server.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000: 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000: Request 오류
     */
    // [POST] /plans
    POST_PLAN_EMPTY_USER(false, 2100, "계획을 실천할 사용자를 입력해주세요"),
    POST_PLAN_EMPTY_FRIEND(false, 2100, "함께할 친구를 입력해주세요"),
    POST_PLAN_EMPTY_CONTENT(false, 2101, "계획의 내용을 입력해주세요."),
    POST_PLAN_EMPTY_DATE(false, 2102, "날짜를 입력해주세요."),

    /**
     * 3000: Response 오류
     */
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    /**
     * 4000: Database 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),

    /**
     * 5000: Server 오류
     */
    SERVER_ERROR(false, 5000, "서버 연결에 실패하였습니다."),

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