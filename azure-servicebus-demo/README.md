# Azure Service Bus Demo (Spring Boot)

This sample sends messages to an Azure Service Bus **queue** via a REST endpoint and receives/logs messages using the Azure Java SDK.

## Endpoints

- `GET /send?msg=Hello` — sends the provided `msg` to the configured queue.

## Prerequisites

- JDK 17+
- Maven 3.9+
- An Azure Service Bus namespace with a **queue** created.
- A connection string with **Send** and **Listen** rights.

## Configure

Set environment variables (recommended):

```bash
export AZURE_SERVICEBUS_CONNECTION_STRING="Endpoint=sb://...;SharedAccessKeyName=...;SharedAccessKey=...;EntityPath=<omit for namespace-level>"
export AZURE_SERVICEBUS_QUEUE_NAME="my-queue"
```

Or edit `src/main/resources/application.yml`.

## Run

```bash
mvn spring-boot:run
# In another shell
curl "http://localhost:8080/send?msg=HelloWorld"
```

You should see logs like:

```
Sent message to queue: HelloWorld
Received message: HelloWorld
```

## Notes

- The receiver uses `ServiceBusProcessorClient` which runs in the background and logs messages.
- Graceful shutdown is handled to stop the processor on app stop.
