package io.Mumuapi.controller.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminLoginDto {

    private String loginId;
    private String loginPw;
}
