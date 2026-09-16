# D287-Java-Inventory-Management
Java Spring inventory management application using MVC architecture, repositories, services, validation, and automated testing.


# Java Inventory Management System

A Java-based inventory management web application built using the
Spring Framework and a layered MVC architecture.

The application manages parts and products, supports multiple part
types, performs inventory validation, and uses repositories and service
layers to separate application responsibilities.

This project was originally developed as part of my Computer Science
coursework at Western Governors University.

## Project Overview

The Inventory Management System provides a web-based interface for
managing parts and products within an inventory.

Users can create and manage inventory records while the application
performs validation to ensure that inventory and product information
meets the required business rules.

The project demonstrates the development of a structured Java
application using controllers, domain models, repositories, services,
validators, HTML templates, and automated tests.

## Technologies

- Java
- Spring Framework
- Spring MVC
- Maven
- HTML
- CSS
- JUnit
- Git

## Application Architecture

The project uses a layered architecture to separate different
responsibilities within the application.

### Controllers

Controllers handle user requests and coordinate interactions between
the web interface and the application's business logic.

Examples include controllers for:

- Adding in-house parts
- Adding outsourced parts
- Adding general parts
- Adding products
- Managing the main inventory screen

### Domain Models

The domain layer represents the primary inventory entities.

The application includes:

- `Part`
- `InhousePart`
- `OutsourcedPart`
- `Product`

Inheritance is used to represent different types of parts while
maintaining common part information and behavior.

### Repositories

Repository classes provide the application's data-access layer.

Separate repositories are provided for:

- Parts
- In-house parts
- Outsourced parts
- Products

This separates persistence operations from controllers and other
application logic.

### Services

The service layer sits between the controllers and repositories and
provides operations for working with inventory data.

Service interfaces and implementations are used for:

- Parts
- In-house parts
- Outsourced parts
- Products

This structure helps separate business logic from web request handling
and data access.

## Validation

Custom validation logic is included to enforce inventory and product
business rules.

The application contains validators related to:

- Product pricing
- Required part quantities
- Part deletion
- Product and part relationships

These validators help prevent invalid inventory states from being
accepted by the application.

## User Interface

The web interface uses HTML templates and CSS to provide screens for
working with the inventory system.

The application includes pages for:

- Viewing inventory
- Adding and modifying parts
- Adding and modifying products
- In-house part management
- Outsourced part management
- Confirmation messages
- Validation and error messages

## Automated Testing

The project includes JUnit tests covering multiple application
components.

Tests are included for domain objects and other application
functionality, including:

- `Part`
- `InhousePart`
- `OutsourcedPart`
- `Product`
- Repository behavior
- Service behavior
- Application startup

Including automated tests helped verify application behavior while
changes were made to the codebase.

## Project Structure

```text
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── bootstrap/
│   │   ├── controllers/
│   │   ├── domain/
│   │   ├── repositories/
│   │   ├── service/
│   │   └── validators/
│   │
│   └── resources/
│       ├── static/
│       ├── templates/
│       └── application.properties
│
└── test/
    └── java/com/example/demo/
        ├── domain/
        ├── repositories/
        └── service/
