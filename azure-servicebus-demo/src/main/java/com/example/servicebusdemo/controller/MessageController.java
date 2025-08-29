package com.example.servicebusdemo.controller;

import com.example.servicebusdemo.service.QueueSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final QueueSender queueSender;

    public MessageController(QueueSender queueSender) {
        this.queueSender = queueSender;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String msg) {
        queueSender.send(msg);
        return "Message sent: " + msg;
    }
}
