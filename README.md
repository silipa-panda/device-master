## Device Master

### Tech Stack

- Spring Boot
- Spring Data JPA
- H2 Database
- Mockito for unit testing
- Docker
- Swagger (OpenAPI 3)

### Prerequisites

- Java 17
- Maven
- Docker (optional)

### Installation

**1. Use the .zip file**

```text
Unzip the device-master.zip file 
```

**2. Build Project :**
```sh
mvn clean install
```

### Run the application 

**Using Maven**
```sh
mvn spring-boot:run
```

**Docker Compose**
```sh
docker-compose up --build
```

### Running Tests

```sh
mvn test
```

### Test Coverage Report

```sh
mvn jacoco:report
```

### Swagger Documentation


**1. By running using maven use below link**

```bash
http://localhost:8090/swagger-ui.html
```


**2. By running using docker compose use below link**

```bash
http://localhost:9090/swagger-ui.html
```