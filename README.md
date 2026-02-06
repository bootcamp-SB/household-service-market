# Nestify – Domestic Service Market (Backend)

## Introduction

Nestify is a full-stack web application backend built with **Spring Boot**, designed to help homeowners find and connect with domestic service providers. This backend handles user authentication, booking management, role-based access, service management, and email notifications, providing a secure and robust foundation for the Nestify platform.

<p align="center">
  [![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE) &nbsp;&nbsp;
  
  [![Last Commit](https://img.shields.io/github/last-commit/bootcamp-SB/household-service-market)](https://github.com/bootcamp-SB/household-service-market) &nbsp;&nbsp;
  
  [![Top Language](https://img.shields.io/github/languages/top/bootcamp-SB/household-service-market)](https://github.com/bootcamp-SB/household-service-market) &nbsp;&nbsp;
  
  [![Repo Size](https://img.shields.io/github/repo-size/bootcamp-SB/household-service-market)](https://github.com/bootcamp-SB/household-service-market)
  
</p>


## Key Features

- **User registration & login** via Keycloak with role-based access
    
- **Booking system** for scheduling services
    
- **Admin dashboard** for managing users and services
    
- **Role-based access** for homeowners, providers, and admins
    
- **Creating service gigs** for providers
    
- **Searching providers** based on service and location
    
- **Email service** with HTML templates powered by Thymeleaf for notifications
    

## Tech Stack

- **Backend:** Spring Boot
    
- **Database:** MySQL
    
- **Authentication:** Keycloak (with roles)
    
- **API Documentation:** Swagger
    
- **Templating & Email:** Thymeleaf for HTML email templates
    
- **DevOps / Tools:** Docker, postman,  dbeaver, intelij 
    

## Installation & Setup

### Prerequisites

- Java 17+ installed
    
- Docker installed
    
- MySQL running
    

### Steps

1. **Clone the repository**

	`git clone https://github.com/yourusername/nestify-backend.git`
	`cd nestify-backend`

2. **Configure environment variables**  
    Create a `.env` file in the root directory with the following placeholders:

```
 	ACTIVE_PROFILE= active application property file
	DATABASE_HOST = database host name
	DATABASE_NAME = database name
	DATABASE_PORT = database port
	DATABASE_PROD_PORT = production port
	JWK_URI = keycloak jwt verifing link
	SECRET_CLIENT = keycloak client secret
	DB_USERNAME=your-database-username 
	DB_PASSWORD=your-database-password 
	KEYCLOAK_SERVER_URL=your-keycloak-url 
	REALM_NAME=your-realm 
	KEYCLOAK_CLIENT_ID=your-client-id 
	MAIL_USERNAME=your-email-username 
	MAIL_PASSWORD=your-email-password
   ```

3. **Run the application locally**

	`./mvnw spring-boot:run`

	`docker-compose up --build` --> to run the keycloak server and the database

## Usage

- Access the backend API at `http://localhost:8080`
    
- Swagger documentation is available at `http://localhost:8080/swagger-ui.html`
    
- Use Postman or similar tools to test API endpoints
    
- Admin and provider roles allow additional operations like creating gigs or managing bookings
    
- Email notifications are sent via Thymeleaf templates for events such as registration confirmations, booking updates, and provider notifications
    

## Contributing

Contributions are welcome! Please fork the repository, create a branch for your feature/fix, and submit a pull request.

## 🔗 Link 

Front end link =  [front-end](https://github.com/YasasSri2002/nestify-eServices)