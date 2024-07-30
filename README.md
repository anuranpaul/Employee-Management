# Employee Management System

This project is a simple Employee CRUD (Create, Read, Update, Delete) API that allows users to manage employee records in a database. The backend is built using Java with Spring Boot and Spring REST, and the frontend is developed with Angular, utilizing Angular Material components for dialog modal views.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Backend Endpoints](#backend-endpoints)
- [Frontend](#frontend)
- [Database](#database)

## Features

- Add a new employee
- Update an existing employee
- View all employees
- View an employee by ID
- Delete an employee by ID

## Technology Stack

### Backend

- Java
- Spring Boot
- Spring REST
- Spring Data JPA
- Spring JDBC
- MariaDB

### Frontend

- Angular
- Angular Material

## Backend Endpoints

The base endpoint for all API operations is `/api/employees`.

- **Add Employee**
  - Endpoint: `/api/employees/add`
  - Method: `POST`
  - Description: Adds a new employee to the database.

- **Update Employee**
  - Endpoint: `/api/employees/update`
  - Method: `PUT`
  - Description: Updates an existing employee in the database.

- **View All Employees**
  - Endpoint: `/api/employees/view`
  - Method: `GET`
  - Description: Retrieves a list of all employees.

- **View Employee by ID**
  - Endpoint: `/api/employees/view/{employeeID}`
  - Method: `GET`
  - Description: Retrieves an employee by their ID.

- **Delete Employee by ID**
  - Endpoint: `/api/employees/delete/{employeeID}`
  - Method: `DELETE`
  - Description: Deletes an employee using their ID.

## Frontend

The frontend of this application is built with Angular and uses Angular Material for the UI components. It includes dialog modal views for adding and updating employees.

## Database

This project uses MariaDB as the database. Spring JDBC is used to connect to the database, and query operations are performed using Spring Data JPA Repository.


