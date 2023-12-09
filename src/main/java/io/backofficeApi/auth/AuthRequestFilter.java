package io.backofficeApi.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthRequestFilter extends OncePerRequestFilter {

    private final AuthUtil jwtUtil;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final String token = request.getHeader("Authorization");

            if (token != null) {
                jwtUtil.validateToken(token); // 토큰 유효성 검사
                String username = jwtUtil.getUsernameFromToken(token);

                if (authService.authUserId(username)) {
                    filterChain.doFilter(request, response);
                } else {
                    handleAuthenticationFailure(response, "User is not authorized");
                }
            } else {
                handleAuthenticationFailure(response, "JWT token is missing");
            }
        } catch (Exception e) {
            handleAuthenticationFailure(response, "JWT Validation Failed");
        }
    }

    private void handleAuthenticationFailure(HttpServletResponse response, String errorMessage) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");
    }
}