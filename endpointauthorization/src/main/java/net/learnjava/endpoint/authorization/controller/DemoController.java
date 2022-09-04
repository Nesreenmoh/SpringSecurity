package net.learnjava.endpoint.authorization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        return "Demo";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}


