package com.example.servicebusdemo.config;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ServiceBusProperties.class)
public class ServiceBusConfig {

    @Bean
    public ServiceBusClientBuilder serviceBusClientBuilder(ServiceBusProperties props) {
        return new ServiceBusClientBuilder().connectionString(props.getConnectionString());
    }

    @Bean
    public ServiceBusSenderClient serviceBusSenderClient(ServiceBusClientBuilder builder, ServiceBusProperties props) {
        return builder
                .sender()
                .queueName(props.getQueueName())
                .buildClient();
    }

    @Bean(destroyMethod = "close")
    public ServiceBusProcessorClient serviceBusProcessorClient(ServiceBusClientBuilder builder, ServiceBusProperties props,
                                                               com.example.servicebusdemo.receiver.QueueReceiver receiver) {
        // The receiver supplies message/err handlers; we start it there.
        return builder
                .processor()
                .queueName(props.getQueueName())
                .processMessage(receiver::handleMessage)
                .processError(receiver::handleError)
                .buildProcessorClient();
    }
}
