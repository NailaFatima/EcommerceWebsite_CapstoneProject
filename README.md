

---

# Ecommerce Website Capstone Project

## Overview

This repository contains the source code for a comprehensive Ecommerce Website project developed as part of a capstone project. The application utilizes a microservice architecture to ensure modularity, scalability, and maintainability. Each service within the application is designed as an independent microservice, allowing seamless integration and independent deployment.

## Architecture and Technologies

### Microservice Architecture
The application is structured into multiple microservices, each responsible for a specific domain or functionality. This ensures that services can be developed, deployed, and scaled independently.

### Technologies Used

1. **Java (100%)**
   - The core language used for developing all microservices.
   - Frameworks: Spring Boot for rapid development and dependency injection.

2. **Kafka**
   - Used for asynchronous communication between microservices.
   - Kafka Producers and Consumers are implemented for message publishing and consumption.
   - Example: The `EmailService` microservice uses Kafka to send and receive email-related messages.

3. **Docker**
   - Each microservice is containerized using Docker, enabling consistent deployment across environments.
   - Ensures that the application runs seamlessly on different systems.

4. **Kubernetes**
   - Kubernetes is used for container orchestration.
   - Role:
     - Ensures high availability and scalability of services.
     - Manages deployment, service discovery, and load balancing of microservices.
   - Configuration files for Kubernetes are included for deploying services as pods and managing them effectively.

5. **Service Discovery**
   - Service discovery is implemented to allow microservices to locate and communicate with each other dynamically.
   - Spring Cloud Netflix Eureka has been used to implement service discovery.
   - Benefits:
     - Allows dynamic registration and discovery of services.
     - Ensures fault tolerance and load balancing.

6. **JWT (JSON Web Tokens)**
   - Used for user authentication and authorization.
   - Authentication Process:
     - Users log in with their credentials.
     - The server generates a JWT containing user information and roles.
     - The JWT is sent to the client and must be included in subsequent requests for secure endpoints.
     - The server validates the JWT to authenticate and authorize the user.

7. **AWS**
   - AWS services are used for hosting and managing infrastructure.
   - Example Roles:
     - S3: Used for storing files and assets.
     - EC2: Instances used for deploying the application.
     - SNS (Simple Notification Service): For notifications as part of the email service.

8. **MySQL**
   - The database used for storing application data.
   - MySQL provides a robust and scalable relational database system for managing user, product, order, and other critical data.

9. **Payment Integration**
   - A dedicated `Payment Service` microservice is included to handle payment processing.
   - Features:
     - Integration with third-party payment gateways (e.g., Stripe, RazorPay).
     - Secure processing of payment transactions.
     - Ensures proper order state management based on payment success or failure.

10. **Maven**
    - Dependency management and project build tool.

### Services Overview

#### 1. Email Service
   - **Purpose**: Handles sending and receiving email notifications.
   - **Technologies**:
     - Kafka: Used for producing and consuming email-related events.
     - Spring Boot: Framework for REST APIs and backend logic.
   - **Key Components**:
     - `SendEmailProducer`: Publishes email messages to the Kafka topic.
     - `SendEmailConsumer`: Listens to the Kafka topic for incoming email messages and processes them.
     - `EmailController`: REST API for sending emails.

   Example Kafka Topic:
   - `sendEmail`: Handles email notifications.

#### 2. Authentication Service
   - **Purpose**: Handles user login, registration, and authentication.
   - **Technologies**:
     - JWT: For generating and validating access tokens.
     - Spring Security: For securing endpoints and managing roles.
   - **Key Features**:
     - Role-Based Access Control (RBAC): Users are assigned roles that determine their access level.
     - Token-Based Authentication: Secure and stateless communication.

#### 3. Product Service
   - **Purpose**: Manages product catalog and inventory.
   - **Key Features**:
     - CRUD operations for product management (Create, Read, Update, Delete).
     - Inventory management to track product availability.
     - Integration with other services to ensure seamless order and inventory updates.

#### 4. Payment Service
   - **Purpose**: Handles payment processing for orders.
   - **Key Features**:
     - Integration with secure third-party payment systems.
     - Manages payment validation, refunds, and order state transitions.
     - Kafka events used for communicating the status of payments to other services.

---

## How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/NailaFatima/EcommerceWebsite_CapstoneProject.git
   cd EcommerceWebsite_CapstoneProject
   ```

2. Build and run services using Docker:
   ```bash
   docker-compose up --build
   ```

3. Deploy services using Kubernetes:
   - Apply the Kubernetes configuration files located in the `k8s-config` directory:
     ```bash
     kubectl apply -f k8s-config/
     ```

4. Access the services via their respective REST endpoints.

---

