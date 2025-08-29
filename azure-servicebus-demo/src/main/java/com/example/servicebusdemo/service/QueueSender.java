package com.example.servicebusdemo.service;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QueueSender {
    private static final Logger log = LoggerFactory.getLogger(QueueSender.class);

    private final ServiceBusSenderClient senderClient;

    public QueueSender(ServiceBusSenderClient senderClient) {
        this.senderClient = senderClient;
    }

    public void send(String body) {
        senderClient.sendMessage(new ServiceBusMessage(body));
        log.info("Sent message to queue: {}", body);
    }
}
