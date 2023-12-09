package io.backofficeApi.controller.api.dto.common;

import lombok.*;

import java.time.Instant;

@Getter
@ToString
public class ResWrapper<T> {

    private final int code;
    private final String message;
    private final T data;
    private final long timestamp;

    public ResWrapper(ResCode resCode) {
        this.code = resCode.getCode();
        this.message = resCode.getMsg();
        this.data = null;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public ResWrapper(ResCode resCode, T data) {
        this.code = resCode.getCode();
        this.message = resCode.getMsg();
        this.data = data;
        this.timestamp = Instant.now().toEpochMilli();
    }
}
