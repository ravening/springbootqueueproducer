package com.example.azuresbdemo.service;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.springframework.stereotype.Service;

@Service
public class ServiceBusSender {

    private final ServiceBusSenderClient senderClient;

    public ServiceBusSender(ServiceBusSenderClient senderClient) {
        this.senderClient = senderClient;
    }

    public void sendTextMessage(String text) {
        ServiceBusMessage message = new ServiceBusMessage(text);
        senderClient.sendMessage(message);
    }
}


