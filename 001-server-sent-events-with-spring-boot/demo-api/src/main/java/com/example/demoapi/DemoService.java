package com.example.demoapi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public class DemoService {

    @Async
    public void getMessage(SseEmitter sseEmitter, String name) throws IOException, InterruptedException {
        var message = "Hello" + name;
        for (int i = 0; i < message.length(); i++) {
            sseEmitter.send(message.substring(i, i + 1), MediaType.TEXT_PLAIN);
            TimeUnit.MILLISECONDS.sleep(200);
        }
        sseEmitter.complete();
    }
}
