# DNB Z Backend

Spring Boot backend for the DNB Z group assignment. It exposes REST endpoints for accounts, customers, budgets, savings targets, recurring transfers and transaction logs backed by MySQL via ORMLite.

## Features
- REST controllers for core banking flows (accounts, transfers, budgets, savings targets, login/password)
- ORMLite data layer with service classes per domain and validation helpers
- Spring Boot with JUnit tests for database services and request handling

## Tech Stack
- Java 8
- Spring Boot 1.4.x
- ORMLite
- MySQL
- Maven Wrapper

## Getting Started
1) Install Java 8+ and Maven (the repository includes `./mvnw`/`./mvnw.cmd`).
2) Create a MySQL database and user. Update `Nytt_Prosjekt/db.properties` with the correct `db.databaseUrl`, `db.user` and `db.password` values for your environment.
3) From the project root, run:
```bash
cd Nytt_Prosjekt
./mvnw spring-boot:run
```

## Testing
Run the JUnit test suite with:
```bash
cd Nytt_Prosjekt
./mvnw test
```

## Project Layout
- `src/main/java/no/westerdals/pj3100g15` – controllers, services, ORM models, validation and exception handling
- `src/test/java` – database and request mapping tests
- `db.properties` – database connection settings

## Contributing
Issues and pull requests are welcome. Please include tests for new functionality where possible.

## License
No explicit license file is present. Treat the code as “all rights reserved” unless a license is added.
