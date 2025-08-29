package com.example.azuresbdemo.config;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.models.ServiceBusReceiveMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ServiceBusConfig {

    @Value("${azure.servicebus.connection-string}")
    private String serviceBusConnectionString;

    @Value("${azure.servicebus.queue-name}")
    private String queueName;

    @Bean
    public ServiceBusSenderClient serviceBusSenderClient() {
        return new ServiceBusClientBuilder()
                .connectionString(serviceBusConnectionString)
                .sender()
                .queueName(queueName)
                .buildClient();
    }

    @Bean(initMethod = "start", destroyMethod = "close")
    public ServiceBusProcessorClient serviceBusProcessorClient(
            Consumer<com.azure.messaging.servicebus.ServiceBusReceivedMessageContext> messageProcessor,
            Consumer<com.azure.messaging.servicebus.ServiceBusErrorContext> errorProcessor
    ) {
        return new ServiceBusClientBuilder()
                .connectionString(serviceBusConnectionString)
                .processor()
                .queueName(queueName)
                .receiveMode(ServiceBusReceiveMode.PEEK_LOCK)
                .processMessage(messageProcessor)
                .processError(errorProcessor)
                .maxConcurrentCalls(1)
                .disableAutoComplete()
                .buildProcessorClient();
    }
}


