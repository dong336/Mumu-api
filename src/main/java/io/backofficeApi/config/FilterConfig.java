package io.backofficeApi.config;

import io.backofficeApi.auth.AuthRequestFilter;
import io.backofficeApi.auth.AuthService;
import io.backofficeApi.auth.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final AuthUtil jwtUtil;
    private final AuthService authService;

    @Bean
    public AuthRequestFilter authRequestFilter(AuthUtil jwtUtil, AuthService authService) {
        return new AuthRequestFilter(jwtUtil, authService);
    }

    @Bean
    public FilterRegistrationBean<AuthRequestFilter> authRequestFilterRegist() {
        FilterRegistrationBean<AuthRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(authRequestFilter(jwtUtil, authService));
        registrationBean.addUrlPatterns("/api/admin/*");
        return registrationBean;
    }
}