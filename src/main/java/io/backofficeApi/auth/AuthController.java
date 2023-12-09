package io.backofficeApi.auth;

import io.backofficeApi.controller.api.dto.common.ResCode;
import io.backofficeApi.controller.api.dto.common.ResWrapper;
import io.backofficeApi.entity.vo.RoleType;
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
    public ResponseEntity<ResWrapper<AuthResponse>> authAdminLogin(@RequestBody AuthRequest request) throws Exception {
        String username = request.username();
        String password = request.password();

        var result = authService.authAdminLogin(username, password);

        if(result.getCode() == ResCode.SUCCESS.getCode()) {
            String jws = jwtUtil.generateJws(username, RoleType.ADMIN.toString());
            HttpHeaders headers = new HttpHeaders();

            headers.add("Authorization", jws);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(result);
        } else {
            return ResponseEntity.ok()
                    .body(result);
        }
    }
}