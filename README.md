# 🧮 Distributed Sum System

A distributed system built with **Java**, **Spring Boot**, and **Apache Kafka** to perform a simple arithmetic operation — the sum of two numbers — using **gRPC**, **REST APIs**, and **asynchronous messaging**.  
Includes observability and performance testing integrations for production-grade deployments.

---

## 📁 Project Structure

```
distributed-sum-system/
├── calculator-service-grpc/   # gRPC service to send data to Kafka
├── sum-rest-service/          # REST service to consume and persist the sum
├── docker-compose.yml         # Kafka, Zookeeper, Kafdrop setup
└── README.md
```

---

## ⚙️ Tech Stack

| Layer         | Technology                  |
|---------------|-----------------------------|
| Language      | Java 17                     |
| Framework     | Spring Boot 3.2+            |
| RPC Protocol  | gRPC                        |
| REST API      | Spring Web MVC              |
| Messaging     | Apache Kafka                |
| Dashboard     | Kafdrop                     |
| Monitoring    | Prometheus, Grafana (TBD)   |
| Performance   | k6                          |
| Build Tools   | Gradle (gRPC), Maven (REST) |

---

## 📦 Services Overview

### 1. `calculator-service-grpc`

- gRPC service that exposes an `Add` RPC method.
- Accepts two integers and publishes them as a Kafka message to `calculator-sum-topic`.

✅ Features:
- gRPC server on `localhost:9090`
- Kafka **producer** integration

---

### 2. `sum-rest-service`

- Spring Boot REST API to return the current total sum.
- Acts as Kafka **consumer**, listens to `calculator-sum-topic`.
- Persists the sum in a simple file-based store (`sum.txt`).

✅ Endpoints:
- `GET /total` – Returns the current total

---

## 🧪 Run the Project Locally

### 1. Start Kafka & Kafdrop (Docker)

```bash
docker-compose up -d
```

This will spin up:
- **Zookeeper** on port `2181`
- **Kafka Broker** on port `9092`
- **Kafdrop UI** on port `9000`

---

### 2. Start gRPC Producer

```bash
cd calculator-service-grpc
./gradlew bootRun
```

Test using `grpcurl`:

```bash
grpcurl -plaintext -d '{"num1": 5, "num2": 10}' localhost:9090 CalculatorService/Add
```

---

### 3. Start REST Consumer

```bash
cd sum-rest-service
./mvnw spring-boot:run
```

Access the API:

```bash
curl http://localhost:8080/total
```

---

## 🔍 Kafka Dashboard – Kafdrop

Kafka UI is available at:

**➡️ http://localhost:9000**

Browse Kafka topics, inspect partitions, and view message payloads.

![Kafdrop UI](screenshots/kafdrop-ui.png)

---

## 🗺️ Roadmap

- [x] Implement gRPC CalculatorService with Add RPC
- [x] Expose REST `GET /total` to read sum from file
- [x] Add Kafka producer to gRPC service
- [x] Add Kafka consumer to REST service
- [x] Add Kafdrop for Kafka message inspection
- [ ] Implement Outbox Pattern for safe Kafka publishing
- [ ] Add Prometheus + Grafana for monitoring
- [ ] Run k6 load tests and report metrics
- [ ] use Kubernetes cluster for deployment

---

## 📤 Outbox Pattern (Planned)

To ensure **exactly-once** delivery from the gRPC producer to Kafka:

- Use a DB-backed **Outbox table** to log events
- A background process will read and push events to Kafka
- Avoids lost messages and enables better recovery after failure

---

## 👨‍💻 Author

**Sameh Tarek** – Java Backend Developer  
[GitHub](https://github.com/sameh-tarek) • [LinkedIn](https://www.linkedin.com/in/sameh-tarek-mohamed-766a0a234/)

---
