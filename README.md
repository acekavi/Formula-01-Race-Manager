# Formula 01 Race Manager

## Overview
Formula 01 Race Manager is a Java-based application designed to manage and simulate Formula 1 racing events. The project focuses on implementing Object-Oriented Programming (OOP) principles and utilizes the Java Spring framework to handle backend operations.

## Features
- Manage race teams and drivers
- Schedule and simulate races
- Track race results and standings
- User-friendly interface

## OOP Principles
- **Encapsulation**: All class fields are private, and access to them is provided through public getter and setter methods.
- **Inheritance**: Common properties and methods are defined in base classes and inherited by subclasses.
- **Polymorphism**: Methods are overridden in subclasses to provide specific implementations.
- **Abstraction**: Abstract classes and interfaces are used to define common behavior that must be implemented by subclasses.

## Technologies
- Java
- Spring Framework
- Spring Boot
- Maven

## Installation
1. Clone the repository:
   git clone https://github.com/acekavi/Formula-01-Race-Manager.git
   cd Formula-01-Race-Manager

2. Build the project using Maven:
   mvn clean install

3. Run the application:
   mvn spring-boot:run

## Usage
### Running the Application
Start the application and open your web browser to access the interface at http://localhost:8080.

## File Structure
- src/main/java/com/formula1/: Contains the source code.
  - controller/: REST controllers for handling HTTP requests.
  - model/: Domain models representing race entities.
  - repository/: Interfaces for data access.
  - service/: Business logic and service layer.
  - Formula1RaceManagerApplication.java: Main application entry point.
- src/main/resources/: Contains application configuration files.
  - application.properties: Configuration properties for the application.
- README.md: This file.
- CONTRIBUTING.md: Guidelines for contributing to the project.
- LICENSE.md: License information.
- pom.xml: Maven configuration file.

## Contributing
Please read CONTRIBUTING.md for details on our code of conduct and the process for submitting pull requests.

## License
This project is licensed under the MIT License - see the LICENSE.md file for details.
EOL
