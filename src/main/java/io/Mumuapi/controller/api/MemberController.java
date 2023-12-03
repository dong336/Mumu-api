package io.Mumuapi.controller.api;

import io.Mumuapi.controller.api.dto.AdminLoginDto;
import io.Mumuapi.controller.api.dto.common.ResponseCode;
import io.Mumuapi.controller.api.dto.common.ResponseWrapper;
import io.Mumuapi.entity.Member;
import io.Mumuapi.service.MemberService;
import io.Mumuapi.service.dto.AdminMemberDto;
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

    @GetMapping("/v1/admin/login")
    public ResponseWrapper<Map<String, Object>> isLoginAdmin() {
        log.info("start isLoginAdmin");
        return new ResponseWrapper<>(
                ResponseCode.SUCCESS,
                Map.of("id", "admin",
                        "name", "관리자"));
    }

    @PostMapping("/v1/admin/login")
    public ResponseWrapper<Map<String, Object>> loginAdmin(@RequestBody Map<String, Object> param) {
        log.info("start loginAdmin: {}", param);
        return new ResponseWrapper<>(
                ResponseCode.SUCCESS,
                new HashMap<>()
        );
    }
}
