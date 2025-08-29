package com.example.azuresbdemo.service;

import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ServiceBusHandlers {

    private static final Logger logger = LoggerFactory.getLogger(ServiceBusHandlers.class);

    @Bean
    public Consumer<ServiceBusReceivedMessageContext> messageProcessor() {
        return context -> {
            ServiceBusReceivedMessage message = context.getMessage();
            try {
                String body = message.getBody().toString();
                logger.info("Received message with ID {} and body: {}", message.getMessageId(), body);
                context.complete();
            } catch (Exception e) {
                logger.error("Error processing message {}: {}", message.getMessageId(), e.getMessage(), e);
                context.abandon();
            }
        };
    }

    @Bean
    public Consumer<ServiceBusErrorContext> errorProcessor() {
        return errorContext -> {
            if (errorContext.getException() != null) {
                if (errorContext.getErrorSource() != null) {
                    logger.error("Error source {}", errorContext.getErrorSource());
                }
                Throwable ex = errorContext.getException();
                logger.error("Processor error: {}", ex.getMessage(), ex);
            }
        };
    }
}


