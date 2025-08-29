package com.example.servicebusdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "azure.servicebus")
public class ServiceBusProperties {
    /**
     * Full connection string for the Service Bus namespace or the queue resource.
     * Example (namespace-level): Endpoint=sb://<namespace>.servicebus.windows.net/;SharedAccessKeyName=...;SharedAccessKey=...
     */
    private String connectionString;

    /** Queue name to send/receive. */
    private String queueName = "my-queue";

    public String getConnectionString() { return connectionString; }
    public void setConnectionString(String connectionString) { this.connectionString = connectionString; }

    public String getQueueName() { return queueName; }
    public void setQueueName(String queueName) { this.queueName = queueName; }
}
