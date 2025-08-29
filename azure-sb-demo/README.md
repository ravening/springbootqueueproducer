# Azure Service Bus Spring Boot Demo

This application sends messages to an Azure Service Bus queue and logs received messages via a background processor.

## Prerequisites

- Java 17
- Maven 3.9+
- An Azure Service Bus namespace and a Queue

## Configuration

Set the following environment variables or update `src/main/resources/application.yml`:

- `AZURE_SERVICEBUS_CONNECTION_STRING` – connection string with Send/Listen rights
- `AZURE_SERVICEBUS_QUEUE_NAME` – target queue name

## Run

```bash
export AZURE_SERVICEBUS_CONNECTION_STRING="Endpoint=sb://..." \
       AZURE_SERVICEBUS_QUEUE_NAME="my-queue"

mvn spring-boot:run
```

## Send a Message

```bash
curl -X POST http://localhost:8080/api/messages \
  -H 'Content-Type: application/json' \
  -d '{"text":"hello from spring"}'
```

The receiver will log messages as they arrive.


