package io.backofficeApi.controller.api.admin;

import io.backofficeApi.controller.api.admin.dto.AdminLoginRequestDto;
import io.backofficeApi.controller.api.admin.dto.AdminLoginResponseDto;
import io.backofficeApi.controller.api.admin.dto.common.ResCode;
import io.backofficeApi.controller.api.admin.dto.common.ResWrapper;
import io.backofficeApi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/v1/test1")
    public Map<String ,Object> test1() {
        return Map.of("code", 2000);
    }

    @GetMapping("/v1/login")
    public ResWrapper<AdminLoginResponseDto> login(@RequestBody AdminLoginRequestDto adminLoginRequestDto) throws Exception {
        log.info("start isLogin");


        return new ResWrapper<>(ResCode.SUCCESS);
    }
}