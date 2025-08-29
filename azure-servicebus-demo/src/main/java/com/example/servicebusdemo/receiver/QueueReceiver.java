package com.example.servicebusdemo.receiver;

import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusException;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class QueueReceiver {
    private static final Logger log = LoggerFactory.getLogger(QueueReceiver.class);

    private final ServiceBusProcessorClient processorClient;

    public QueueReceiver(ServiceBusProcessorClient processorClient) {
        this.processorClient = processorClient;
    }

    @PostConstruct
    public void start() {
        processorClient.start();
        log.info("Service Bus processor started and listening for messages...");
    }

    @PreDestroy
    public void stop() {
        processorClient.close();
        log.info("Service Bus processor stopped.");
    }

    public void handleMessage(ServiceBusReceivedMessageContext context) {
        String body = context.getMessage().getBody().toString();
        log.info("Received message: {}", body);
    }

    public void handleError(ServiceBusErrorContext context) {
        Throwable throwable = context.getException();
        if (throwable instanceof ServiceBusException sbEx) {
            log.error("Error source: {}, reason: {}", sbEx.getEntityPath(), sbEx.getReason(), throwable);
        } else {
            log.error("Service Bus processing error", throwable);
        }
    }
}
