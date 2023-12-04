package io.Mumuapi.controller;

import io.Mumuapi.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
public class PingController {

    @Value("${jwt.secret}")
    private String secret;

    private final JwtUtil jwtUtil;

    @GetMapping("/ping")
    public String ping() {


        return "Success ping";
    }
}
