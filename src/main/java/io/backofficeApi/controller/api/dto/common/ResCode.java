package io.backofficeApi.controller.api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResCode {

    SUCCESS(2000, "성공"),

    FAIL(4000, "시스템 에러 발생"),

    FAIL_MEMBER_NO_ID(4101, "존재하지 않는 아이디입니다."),
    FAIL_MEMBER_INVALID_PW(4102, "잘못된 비밀번호입니다."),
    ;

    private final Integer code;
    private final String msg;
}
