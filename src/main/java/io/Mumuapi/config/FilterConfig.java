package io.Mumuapi.config;

import io.Mumuapi.auth.AuthRequestFilter;
import io.Mumuapi.auth.AuthService;
import io.Mumuapi.auth.AuthUtil;
import io.Mumuapi.repository.MemberRepository;
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