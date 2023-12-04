package io.Mumuapi.controller.api.dto.common;

import lombok.*;

import java.time.Instant;
import java.util.HashMap;

@Getter
@ToString
public class ResponseWrapper<T> {

    private final int code;
    private final String message;
    private final T data;
    private final long timestamp;

    public ResponseWrapper(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMsg();
        this.data = null;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public ResponseWrapper(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMsg();
        this.data = data;
        this.timestamp = Instant.now().toEpochMilli();
    }
}
