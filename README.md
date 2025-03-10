# Employee Management System

An educational Employee Management API web application, built with Spring Boot, Java 17, and Oracle 11g XE.
This project is to demonstrate the basic setup of CRUD operations.

## Notes
Personal notes on how to build such project step by step is available here:
https://monogr.ph/67c8977646327331aae4f8e0

## Features

- **CRUD Operations**: Create, Read, Update, and Delete employees.
- **RESTful API**: Exposes endpoints to manage employees.
- **Database Integration**: Utilizes Oracle 11g XE as the database.
- **Hibernate**: Leverages JPA for entity mapping and database operations.
- **Seamless Layered Architecture**: Includes Controller, Service, Repository, and Entity layers.
- **Spring Boot Starter**: Simplifies application configuration and setup.

## Technologies Used

- **Java 17**: Programming language.
- **Spring Boot**: Framework for rapid application development.
  - Spring Data JPA
  - Spring Web
- **Oracle 11g XE**: Database system.
- **Hibernate**: ORM for database interactions.
- **Maven**: Build tool.

## Prerequisites

Before running the project, make sure you have the following installed:

- JDK 17 or higher
- Maven
- Oracle 11g XE (with a schema ready to use)

## Setup Instructions

### Clone the Repository

```sh
git clone https://github.com/your-username/employee-management.git
cd employee-management
```

### Configure the Database

1. Update the `application.properties` file with your Oracle database configuration:
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.Oracle10gDialect
   spring.jpa.hibernate.ddl-auto=update
   ```

2. Create the required sequence and trigger for the `employees` table (Oracle 11g lacks the `IDENTITY` feature):
   ```sql
   CREATE SEQUENCE employees_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

   CREATE OR REPLACE TRIGGER employees_bir
   BEFORE INSERT ON employees
   FOR EACH ROW
   WHEN (new.ssn IS NULL)
   BEGIN
       SELECT employees_seq.NEXTVAL INTO :new.ssn FROM dual;
   END;
   /
   ```

### Build and Run

1. Build the project using Maven:
   ```sh
   mvn clean install
   ```

2. Run the application:
   ```sh
   mvn spring-boot:run
   ```

### Test the Application

The application exposes RESTful endpoints that you can test with tools like Postman or curl:

- `GET /employees`: Get a list of all employees.
- `POST /employees`: Add a new employee.
- `PUT /employees/{id}`: Update an existing employee.
- `DELETE /employees/{id}`: Delete an employee.

## Contributing

If you would like to contribute, please submit a pull request or open an issue for discussion.

## License

This project is licensed under the MIT License.

---
