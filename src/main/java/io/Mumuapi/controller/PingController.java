package io.Mumuapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PingController {

    @GetMapping("/hello")
    public String hello() {

        return "Hello, World!";
    }

    @GetMapping("/ping")
    public String ping() {

        return "Success";
    }
}
