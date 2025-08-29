package com.example.azuresbdemo.web;

import com.example.azuresbdemo.service.ServiceBusSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final ServiceBusSender serviceBusSender;

    public MessageController(ServiceBusSender serviceBusSender) {
        this.serviceBusSender = serviceBusSender;
    }

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody Map<String, Object> payload) {
        Object text = payload.get("text");
        if (text == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing 'text' field"));
        }
        serviceBusSender.sendTextMessage(text.toString());
        return ResponseEntity.accepted().body(Map.of("status", "queued"));
    }
}


