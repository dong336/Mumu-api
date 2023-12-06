package io.backofficeApi.controller.api.admin.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminLoginRequestDto {
    private String id;
    private String pw;
}