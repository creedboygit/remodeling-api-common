package com.hanssem.remodeling.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    OK(0, "OK"),
    NOT_FOUND(404, "찾을 수 없는 요청입니다."),
    BAD_REQUEST(400, "잘못된 요청입니다."),
    ERROR_FEIGN_CLIENT(9998, "Feign Client 에러"),
    ERROR(9999, "오류가 발생했습니다. 잠시 후 다시 시도해 주세요."),
    NECESSARY_SCRAP_TYPE(100, "스크랩 타입은 필수입니다."),
    NECESSARY_AUTHORIZATION(101, "인증값은 필수입니다."),
    //     * 12000 ~ 12999 공통
    NOT_FOUND_EXCEL_SHEET(12000, "Excel sheet not found"),
    ONLY_LOGIN(12001, "로그인 후 사용 가능합니다."),
    INVALID_END_DATETIME(12001, "사용 종료된 코드입니다."),
    DATA_NOT_FOUND(12100, "해당하는 데이터가 존재하지 않습니다."),


    /*
     * 10000 ~ 10999 게이트웨이
     * 11000 ~ 11999 인증
     * 12000 ~ 12999 공통
     * 13000 ~ 13999 회원
     * 14000 ~ 14499 상담
     * 14500 ~ 14999 리모델링매니저
     * 15000 ~ 15999 대리점
     * 16000 ~ 16099 컨텐츠-상품
     * 16100 ~ 16199 컨텐츠-공간별견적담기
     * 16300 ~ 16399 컨텐츠-인테리어취향
     * 16400 ~ 16999 컨텐츠-전시 외
     * 17000 ~ 17999 알림
     */

    INVALID_PARAMS(12000, "입력값이 필요합니다.")
    ;

    private final int code;
    private final String message;
}
