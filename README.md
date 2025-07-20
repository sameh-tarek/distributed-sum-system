# 🧮 Distributed Sum System

A distributed system built with Java & Spring Boot that performs a simple arithmetic operation (sum of two numbers) using gRPC, Kafka, and REST APIs — with support for monitoring and performance testing.

---

## 🚀 Project Structure

```
distributed-sum-system/
├── calculator-service-grpc/   # gRPC service to receive two numbers
├── sum-rest-service/          # REST service to return the current sum
```

---

## 🧱 Tech Stack

| Layer           | Technology                  |
|----------------|-----------------------------|
| Language        | Java 17                     |
| Framework       | Spring Boot 3.5.x           |
| Communication   | gRPC, REST                  |
| Messaging       | Apache Kafka                |
| Storage         | File-based sum              |
| Observability   | Prometheus, Grafana         |
| Performance     | k6                          |
| Build Tools     | Gradle (gRPC), Maven (REST) |

---

## 📦 Services Overview

### 1. `calculator-service-grpc`
- gRPC service to receive two numbers via `Add` RPC
- Will act as a **Kafka producer**
- Sends the request `{ num1, num2 }` to a Kafka topic

### 2. `sum-rest-service`
- REST API to **consume messages from Kafka**
- Adds received number to a **file-based sum**
- Supports:
  - `GET /total`: Return current sum
  - `POST /total/add`: (to be implemented)

---

## 🗺️ Roadmap

- [x] Implement gRPC CalculatorService with Add RPC
- [x] Expose REST `GET /total` to read from file
- [ ] Add Kafka producer to gRPC service
- [ ] Add Kafka consumer to REST service
- [ ] Use Outbox pattern for reliable message publishing
- [ ] Add Prometheus and Grafana for monitoring (latency, throughput)
- [ ] Load test with K6

---

## 🛠️ How to Run (dev mode)

### 1. gRPC Service

```bash
cd calculator-service-grpc
./gradlew bootRun
```

Test with `grpcurl`:

```bash
grpcurl -plaintext   -d '{"num1": 5, "num2": 10}'   localhost:9090 CalculatorService/Add
```

### 2. REST Service

```bash
cd sum-rest-service
./mvnw spring-boot:run
```

Test with curl:

```bash
curl http://localhost:8080/total
```

---

## 📤 Planned: Outbox Pattern

To ensure **reliable message delivery** from the gRPC service to Kafka, we will implement the **Transactional Outbox Pattern**, which includes:

- Persisting each outgoing event in a dedicated **outbox table** in the same DB transaction as the business operation.
- A background **Outbox Publisher** will read events from the table and publish them to Kafka.
- This approach avoids lost updates or duplication during service crashes or retries.
