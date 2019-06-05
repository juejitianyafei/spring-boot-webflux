package com.ty.web;

import com.ty.domain.City;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wangfei
 * @date 2019/6/4 18:18
 */
@Controller
public class TestController {

    @GetMapping("/hello")
    @ResponseBody
    public Mono<String> hello() {
        return Mono.just("Welcome to reactive world ~");
    }

}
