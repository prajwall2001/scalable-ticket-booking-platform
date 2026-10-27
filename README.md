# Scalable Ticket Booking Platform

A Java 17 and Spring Boot microservices portfolio project for event discovery, ticket inventory, and booking.

## Services

| Service | Port | Responsibility |
|---|---:|---|
| User Service | 8081 | User registration and lookup |
| Event Service | 8082 | Create and list events |
| Booking Service | 8083 | Ticket inventory, booking requests, and inventory reservation |
| Notification Service | 8084 | Demo notification receipt API |

## Technology

- Java 17, Spring Boot 3.3
- Spring Web, Spring Data JPA, Bean Validation
- MySQL for user, event, and booking data
- Springdoc OpenAPI / Swagger UI
- JUnit 5
- Maven multi-module build
- Optimistic locking with JPA `@Version` for inventory updates

## Architecture and limitations

This is a portfolio starter project, not a production-certified ticketing system. Booking reserves inventory in the Booking Service database. A production distributed deployment should use a durable event-driven workflow (for example, an outbox plus a message broker and compensating reservation) rather than assume a single ACID transaction spans multiple services.

JWT security and Firestore integration are not implemented yet. The notification endpoint currently stores receipts in memory for demonstration purposes. Do not describe these features as production-ready until implemented and tested.

## Prerequisites

- JDK 17+
- Maven 3.9+
- Docker Compose (optional, for MySQL)

## Run locally

Start MySQL:

```bash
docker compose up -d
```

Build and test:

```bash
mvn clean verify
```

Run each service in a separate terminal:

```bash
mvn -pl user-service spring-boot:run
mvn -pl event-service spring-boot:run
mvn -pl booking-service spring-boot:run
mvn -pl notification-service spring-boot:run
```

Configure database credentials through `DB_HOST`, `DB_USER`, and `DB_PASSWORD`. Defaults are for local development only; never use them in production.

## Swagger UI

- http://localhost:8081/swagger-ui.html
- http://localhost:8082/swagger-ui.html
- http://localhost:8083/swagger-ui.html
- http://localhost:8084/swagger-ui.html

## Example API flow

1. Create a user with User Service.
2. Create an event with Event Service.
3. Add inventory for the event through Booking Service.
4. Book tickets and inspect the booking status.

Create a user:

```bash
curl -X POST http://localhost:8081/api/users -H 'Content-Type: application/json' -d '{"fullName":"Demo User","email":"demo@example.com"}'
```

Create an event:

```bash
curl -X POST http://localhost:8082/api/events -H 'Content-Type: application/json' -d '{"title":"Tech Summit","venue":"Bengaluru","startsAt":"2027-02-20T10:00:00","totalSeats":100}'
```

Add inventory:

```bash
curl -X POST http://localhost:8083/api/inventory -H 'Content-Type: application/json' -d '{"eventId":"<event-id>","availableSeats":100}'
```

Book tickets:

```bash
curl -X POST http://localhost:8083/api/bookings -H 'Content-Type: application/json' -d '{"userId":"<user-id>","eventId":"<event-id>","quantity":2}'
```

## Testing

Run `mvn test`. The current test suite includes domain tests for ticket inventory and a user-service context test. Add MySQL integration tests and load tests before claiming production readiness.

## Roadmap

- JWT token validation and role-based authorization
- Firestore adapter for notification/audit documents
- Durable notifications with outbox and message broker
- Idempotency keys, booking expiry, and compensation
- Integration tests using Testcontainers
- Load tests for concurrent inventory contention
