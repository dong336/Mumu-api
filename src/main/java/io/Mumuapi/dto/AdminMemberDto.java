package io.Mumuapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminMemberDto {
    private String id;
    private String pw;
    private String name;
}