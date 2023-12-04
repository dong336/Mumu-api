package io.Mumuapi.controller.api;

import io.Mumuapi.controller.api.dto.AdminMemberDto;
import io.Mumuapi.controller.api.dto.common.ResponseCode;
import io.Mumuapi.controller.api.dto.common.ResponseWrapper;
import io.Mumuapi.entity.Member;
import io.Mumuapi.repository.MemberRepository;
import io.Mumuapi.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/v1/admin/test1")
    public Map<String ,Object> test1() {
        return Map.of("code", 2000);
    }

    @PostMapping("/v1/admin/test2")
    public Map<String ,Object> test2() {
        return Map.of("code", 2000);
    }

    @GetMapping("/v1/isLoginAdmin")
    public ResponseWrapper<Map<String, Object>> isLoginAdmin() {
        log.info("start isLoginAdmin");
        return new ResponseWrapper<>(ResponseCode.SUCCESS);
    }

    @PostMapping("/v1/loginAdmin")
    public ResponseWrapper<AdminMemberDto> loginAdmin(@RequestBody AdminMemberDto adminMemberDto) {
        log.info("start loginAdmin: {}", adminMemberDto);
        Member admin = memberRepository.findByUserId(adminMemberDto.getId());

        if(admin == null) {
            return new ResponseWrapper<>(ResponseCode.FAIL_MEMBER_NO_ID);
        }

        Member adminByIdAndPw = memberRepository.findByUserIdAndUserPw(adminMemberDto.getId(), adminMemberDto.getPw());
        log.info("pass: {}", adminByIdAndPw);
        if(adminByIdAndPw == null) {
            return new ResponseWrapper<>(ResponseCode.FAIL_MEMBER_INVALID_PW);
        }
        else {
            AdminMemberDto result = AdminMemberDto.builder()
                    .id(adminMemberDto.getId())
                    .pw(adminMemberDto.getPw())
                    .name(adminByIdAndPw.getUserName())
                    .build();

            return new ResponseWrapper<>(ResponseCode.SUCCESS, result);
        }
    }
}