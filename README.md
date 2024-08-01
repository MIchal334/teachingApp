# TeachingApp
## Overview

This application is designed to assist users in learning English. It provides a platform where users can complete lessons, while administrators can manage both lessons and users. The backend is built using Java with the Spring Boot framework, and it features a three-layered architecture. The application uses JWT for authentication and authorization, and the data is managed with a MySQL database, with migrations handled by Flyway. Docker Compose is used to simplify the deployment process.

## Features

### User Features
- **Lesson Participation**: Users can access and complete available lessons.
- **Profile Management**: Users can view and edit their profiles.

### Admin Features
- **Lesson Management**: Admins can create, update, and delete lessons.
- **User Management**: Admins have the ability to manage user accounts, including adding and removing users.


## Technologies Used

- **Backend**: Java, Spring Boot
- **Security**: Spring Security with JWT
- **Database**: MySQL
- **Database Migration**: Flyway
- **Containerization**: Docker, Docker Compose
- **Architecture**: Three-layered architecture (Controller, Service, Repository)
