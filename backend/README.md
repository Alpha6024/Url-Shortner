# URL Shortener

A Spring Boot REST API that shortens URLs, backed by MySQL for persistence and Redis for caching.

## Tech Stack

- Java 21 / Spring Boot 4.1
- Spring Data JPA + MySQL
- Spring Data Redis
- Spring Validation
- Lombok
- Spring Actuator

## Prerequisites

- JDK 21+
- MySQL running with a database created
- Redis running on default port (6379)

## Configuration

Copy and fill in your local settings (this file is git-ignored):

```properties
# src/main/resources/application-local.properties
spring.datasource.url=jdbc:mysql://localhost:3306/<your_db>
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

## Run

```bash
./mvnw spring-boot:run
```

## Build

```bash
./mvnw clean package
java -jar target/shortner-0.0.1-SNAPSHOT.jar
```

## Health Check

```
GET /actuator/health
```
