package io.Mumuapi.auth;

import io.Mumuapi.controller.api.dto.common.ResponseCode;
import io.Mumuapi.controller.api.dto.common.ResponseWrapper;
import io.Mumuapi.entity.Member;
import io.Mumuapi.entity.vo.RoleType;
import io.Mumuapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUtil jwtUtil;
    private final AuthService authService;

    @PostMapping("/auth/admin-login")
    public ResponseEntity<?> authAdminLogin(@RequestBody AuthRequest request) throws Exception {
        String username = request.username();
        String password = request.password();

        ResponseCode code = authService.authAdminLogin(username, password);

        if(code.equals(ResponseCode.SUCCESS)) {
            String jws = jwtUtil.generateJws(username, RoleType.ADMIN.toString());
            HttpHeaders headers = new HttpHeaders();

            headers.add("Authorization", jws);

            return ResponseEntity.ok().headers(headers).build();
        } else {
            return ResponseEntity.ok().body(new ResponseWrapper<>(code));
        }
    }

    @PostMapping("/auth/user-login")
    public ResponseEntity<?> authUser(@RequestBody AuthRequest request) {
        return null;
    }
}