package io.Mumuapi.controller.api.admin;

import io.Mumuapi.dto.AdminMemberDto;
import io.Mumuapi.dto.common.ResCode;
import io.Mumuapi.dto.common.ResWrapper;
import io.Mumuapi.entity.Member;
import io.Mumuapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/v1/test2")
    public Map<String ,Object> test2() {
        return Map.of("code", 2000);
    }

    @GetMapping("/v1/isLoginAdmin")
    public ResWrapper<Map<String, Object>> isLoginAdmin() {
        log.info("start isLoginAdmin");
        return new ResWrapper<>(ResCode.SUCCESS);
    }

    @PostMapping("/v1/login")
    public ResWrapper<AdminMemberDto> adminLogin(@RequestBody AdminMemberDto adminMemberDto) {
        log.info("start loginAdmin: {}", adminMemberDto);
        Member admin = memberRepository.findByUserId(adminMemberDto.getId());

        if(admin == null) {
            return new ResWrapper<>(ResCode.FAIL_MEMBER_NO_ID);
        }

        Member adminByIdAndPw = memberRepository.findByUserIdAndUserPw(adminMemberDto.getId(), adminMemberDto.getPw());
        log.info("pass: {}", adminByIdAndPw);
        if(adminByIdAndPw == null) {
            return new ResWrapper<>(ResCode.FAIL_MEMBER_INVALID_PW);
        }
        else {
            AdminMemberDto result = AdminMemberDto.builder()
                    .id(adminMemberDto.getId())
                    .pw(adminMemberDto.getPw())
                    .name(adminByIdAndPw.getUserName())
                    .build();

            return new ResWrapper<>(ResCode.SUCCESS, result);
        }
    }
}