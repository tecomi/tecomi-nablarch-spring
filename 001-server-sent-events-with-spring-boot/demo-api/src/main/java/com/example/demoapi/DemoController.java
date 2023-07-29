package com.example.demoapi;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class DemoController {

    @Autowired
    DemoService demoService;

    @GetMapping("/api/hello")
    Object hello() {
        return Map.of("message", "HelloWorld");
    }

    @PostMapping("/api/hello-stream")
    SseEmitter helloStream(@RequestParam String name) throws IOException, InterruptedException {
        var sseEmitter = new SseEmitter();

        demoService.getMessage(sseEmitter, name);

        return sseEmitter;
    }

}
