package io.Mumuapi.auth;

import io.Mumuapi.dto.common.ResCode;
import io.Mumuapi.dto.common.ResWrapper;
import io.Mumuapi.entity.vo.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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

        ResCode code = authService.authAdminLogin(username, password);

        if(code.equals(ResCode.SUCCESS)) {
            String jws = jwtUtil.generateJws(username, RoleType.ADMIN.toString());
            HttpHeaders headers = new HttpHeaders();

            headers.add("Authorization", jws);

            return ResponseEntity.ok().headers(headers).build();
        } else {
            return ResponseEntity.ok().body(new ResWrapper<>(code));
        }
    }

    @PostMapping("/auth/user-login")
    public ResponseEntity<?> authUser(@RequestBody AuthRequest request) {
        return null;
    }
}