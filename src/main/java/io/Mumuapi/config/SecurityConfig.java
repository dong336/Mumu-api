package io.Mumuapi.config;

import io.Mumuapi.entity.vo.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("start securityFilterChain");
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilter(corsFilter)
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.GET, "/api/*/admin/**").hasRole(RoleType.ADMIN.toString())
                        .requestMatchers(HttpMethod.POST, "/api/*/admin/**").hasRole(RoleType.ADMIN.toString())
                        .requestMatchers(HttpMethod.DELETE, "/api/*/admin/**").hasRole(RoleType.ADMIN.toString())
                        .requestMatchers(HttpMethod.PUT, "/api/*/admin/**").hasRole(RoleType.ADMIN.toString())
                        .requestMatchers(HttpMethod.PATCH, "/api/*/admin/**").hasRole(RoleType.ADMIN.toString())

                        .requestMatchers(HttpMethod.GET, "/api/*/user/**").hasRole(RoleType.USER.toString())
                        .requestMatchers(HttpMethod.POST, "/api/*/user/**").hasRole(RoleType.USER.toString())
                        .requestMatchers(HttpMethod.DELETE, "/api/*/user/**").hasRole(RoleType.USER.toString())
                        .requestMatchers(HttpMethod.PUT, "/api/*/user/**").hasRole(RoleType.USER.toString())
                        .requestMatchers(HttpMethod.PATCH, "/api/*/user/**").hasRole(RoleType.USER.toString())

                        .anyRequest().permitAll()
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return http.build();
    }
}
