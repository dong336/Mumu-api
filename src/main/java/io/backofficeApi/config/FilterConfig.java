package io.backofficeApi.config;

import io.backofficeApi.auth.AuthRequestFilter;
import io.backofficeApi.auth.AuthService;
import io.backofficeApi.auth.AuthUtil;
import io.backofficeApi.filter.SpaRedirectFiler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.regex.Pattern;

@Slf4j
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
    public FilterRegistrationBean<AuthRequestFilter> authRequestFilterRegister() {
        FilterRegistrationBean<AuthRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(authRequestFilter(jwtUtil, authService));
        registrationBean.addUrlPatterns("/api/admin/*");
        registrationBean.setOrder(0);
        return registrationBean;
    }

    @Bean
    public SpaRedirectFiler spaRedirectFiler() {
        return new SpaRedirectFiler();
    }

    @Bean
    public FilterRegistrationBean<SpaRedirectFiler> spaRedirectFilerRegister() {
        FilterRegistrationBean<SpaRedirectFiler> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(spaRedirectFiler());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}