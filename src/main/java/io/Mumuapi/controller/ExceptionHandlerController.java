package io.Mumuapi.controller;

import io.Mumuapi.controller.api.admin.dto.common.ResCode;
import io.Mumuapi.controller.api.admin.dto.common.ResWrapper;
import io.Mumuapi.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResWrapper<?>> handleRuntimeException(RuntimeException e) {
        ResCode resCode = ResCode.FAIL;
        return new ResponseEntity<>(new ResWrapper<>(resCode, e.getMessage()), HttpStatus.OK);
    }

}