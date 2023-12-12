package io.backofficeApi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class CustomErrorController implements ErrorController {
//    @RequestMapping("/error")
//    public String handleError() {
//        // 404 에러 발생 시 /static/index.html 로 포워딩
//        log.info("error load");
//
//        return "forward:index.html";
//    }
}
