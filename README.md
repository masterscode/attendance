# Employee Attendance Register API

A REST API built with **Java** and **Spring Boot** for managing employee attendance within a medical organization.
This project was developed as part of a backend coding assessment. 

---

# Features

## Employee Management

* Create a new employee
* Update an existing employee
* Retrieve all employees
* Filter employees by department
* Support for employee classification:

    * Medical
    * Non-Medical

## Department Management

* Retrieve all departments
* Associate employees with departments

## Attendance Management

Supports the following attendance events:

* Sign In
* Sign Out
* Sick Leave
* Absence

Attendance records can also be queried within a specified date range.

---

# Technology Stack

| Technology              | Purpose               |
| ----------------------- | --------------------- |
| Java                    | Programming Language  |
| Spring Boot             | Backend Framework     |
| Spring Web              | REST API              |
| Spring Data JPA         | Data Persistence      |
| PostgreSQL              | Relational Database   |
| Redis                   | Caching               |
| Flyway                  | Database Versioning   |
| Docker & Docker Compose | Containerization      |
| OpenAPI / Swagger       | API Documentation     |
| Maven                   | Dependency Management |

---

# Architecture

The project follows a layered architecture to promote separation of concerns.

```
                Client
                   │
                   ▼
          REST Controllers
                   │
                   ▼
             Service Layer
                   │
                   ▼
           Repository Layer
                   │
                   ▼
             PostgreSQL
```

Redis is used to cache frequently accessed data where appropriate to reduce database load and improve response times.

---

# Design Patterns

## Strategy Pattern

Attendance operations are implemented using the Strategy Pattern.

Each attendance action has its own strategy implementation, making the system easy to extend without modifying existing business logic.

Current strategies include:

* Sign In
* Sign Out
* Sick Leave
* Absent

Adding a new attendance action (for example, **Work From Home**) only requires implementing a new strategy.

---

## Factory Pattern

Factory classes are used to centralize object creation and keep business logic clean.

Examples include:

* EmployeeFactory
* AttendanceFactory

This avoids object creation logic being scattered across service classes.

---

## Repository Pattern

Persistence concerns are isolated using Spring Data JPA repositories, providing a clean abstraction over database operations.

---

## Specification Pattern

Dynamic filtering is implemented using Spring Data Specifications.

This provides flexible querying without creating numerous repository methods and keeps the codebase maintainable as filtering requirements grow.

---

# Project Structure

```
src
└── main
    ├── java
    │   └── ...
    │       ├── attendance
    │       ├── employee
    │       ├── department
    │       ├── common
    │       ├── config
    │       └── ...
    └── resources
        ├── db
        │   └── migration
        ├── application.yml
        └── ...
```

Each feature is organized into its own package containing:

* Controller
* Service
* Repository
* Entity
* DTO
* Mapper
* Specification
* Factory (where applicable)

This modular structure keeps related components together and improves maintainability.

---

# Getting Started

## Prerequisites

Ensure the following are installed:

* Java 21+
* Maven
* Docker
* Docker Compose

---

# Clone the Repository

```bash
git clone https://github.com/masterscode/attendance.git

              OR

git clone git@github.com:masterscode/attendance.git

cd employee-attendance
```

---

# Configure Environment Variables

Create a `.env` file (or update the application configuration) with values similar to:

```properties
DB_HOST=localhost
DB_PORT=5432
DB_NAME=attendance
DB_USERNAME=postgres
DB_PASSWORD=password

REDIS_HOST=localhost
REDIS_PORT=6379
```

---

# Run with Docker

Start all required services using Docker Compose.

```bash
docker compose up --build
```

---

## Prerequisites

- Java 21+
- Docker & Docker Compose
- GNU Make

---

## Running the Project

The project includes a `Makefile` to simplify common development tasks.

### Start the application

```bash
make start
```

### Build the project

```bash
make build
```

### Run locally (without Docker)

```bash
make run
```

### Stop Docker containers

```bash
make stop
```

### Rebuild Docker images

```bash
make rebuild
```

### Clean the project

```bash
make clean
```

Alternatively, you can use Maven or Docker Compose directly if preferred.
---

# Database Migrations

Database schema changes are managed using **Flyway**.

Migrations execute automatically during application startup.

No manual SQL setup is required.

---

# API Documentation

After starting the application, Swagger documentation is available at:

```
http://localhost:8080/swagger-ui.html
```

or

```
http://localhost:8080/swagger-ui/index.html
```

(depending on the SpringDoc version).

# Assumptions

The following assumptions were made during implementation:

* An employee belongs to a single department.
* Employees are classified as either **Medical** or **Non-Medical**.
* Attendance records are maintained independently for each employee.
* Attendance history can be queried using a date range.
* Departments are relatively static and suitable for caching.

---


# Possible Improvements

If this project were to evolve further, the following enhancements could be added:

* Authentication & Authorization (JWT/OAuth2)
* Pagination and Sorting
* Comprehensive Unit Tests
* Integration Tests
* API Rate Limiting
* Metrics and Monitoring (Micrometer + Prometheus)
* CI/CD Pipeline
* Audit Logging
* Event-Driven Notifications

---

# Author

**Emmanuel Ogbinaka**

Backend Software Engineer

Thank you for taking the time to review this project.
