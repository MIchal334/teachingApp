# TeachingApp
## Overview

This application is designed to assist users in learning English. It provides a platform where users can complete lessons, while administrators can manage both lessons and users. The backend is built using Java with the Spring Boot framework, and it features a three-layered architecture. The application uses JWT for authentication and authorization, and the data is managed with a MySQL database, with migrations handled by Flyway. Docker Compose is used to simplify the deployment process.

## Technologies Used

- **Backend**: [Java](https://www.java.com/), [Spring Boot](https://spring.io/projects/spring-boot)
- **Security**: [Spring Security](https://spring.io/projects/spring-security) with [JWT](https://jwt.io/)
- **Database**: [MySQL](https://www.mysql.com/)
- **Database Migration**: [Flyway](https://flywaydb.org/)
- **Containerization**: [Docker](https://www.docker.com/), [Docker Compose](https://docs.docker.com/compose/)
- **Architecture**: Three-layered architecture (Controller, Service, Repository)
