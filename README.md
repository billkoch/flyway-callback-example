# Flway Callback Example
How to insert seed data with Spring Boot + FlywayCallbacks

## Getting Started

Spin up MySQL:
```bash
$ docker-compose up -d
```

Running with the `seed-data` profile:
```bash
$ SPRING_PROFILES_ACTIVE=seed-data ./gradlew bootRun
```

Running without seeding data:
```bash
$ ./gradlew bootRun
```
~~~~