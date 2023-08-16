# ALEXANDRIA

This is a Java-based application for managing orders in a café. It allows customers to place orders through a mobile app, and the orders are recorded in a relational database. The system also provides an interface for the administrator to view, add, remove, and modify the available merchandise in the café menu.

## Technologies Used

- Java
- Maven
- Spring Boot
- Lombok
- Log4j
- Thymeleaf
- Spring Boot DevTools
- MySQL
- Spring Security
- HTML
- CSS
- JavaScript

## Project Structure

The application follows the Model-View-Controller (MVC) design pattern for a structured development approach. The project is organized using Maven for dependency management and build processes. The Spring Boot framework is used to simplify the development of Java applications.

## Database Configuration

The application utilizes MySQL as the relational database. Make sure you have MySQL installed and running. Update the database configuration in the `application.properties` file with the appropriate credentials.

## Database Initialization

The application includes an `import.sql` file that populates the initial items and users in the database. You can find this file in the project's resources directory.

The default user for accessing the application is:

- Username: user
- Password: user

You can modify the username and password in the `import.sql` file to change the login credentials as needed.

To import the initial data into the database, follow these steps:

1. Ensure that the MySQL server is running and accessible.
2. Open a MySQL client (such as MySQL Workbench or the MySQL command-line interface).
3. Create a new database for the application (if not already created).
4. Personalize the application properties document including your database name and the correct password in order to initialize te application correctly

## Front-End Development

The front-end of the application is implemented using HTML, CSS, and JavaScript. The Thymeleaf template engine is used to render dynamic content on the server-side.
The application is designed to be responsive and adaptable to different devices. It has been tested and optimized for various screen sizes, including mobile devices, tablets, and desktops.


## Running the Application

To run the application locally, follow these steps:

1. Clone the repository to your local machine.
2. Ensure you have Java, Maven, and MySQL installed.
3. Update the database configuration in `application.properties`, including the database password.
4. Build the project using Maven: `mvn clean install`.
5. Run the application: `mvn spring-boot:run`.
6. Open a web browser and visit `http://localhost:8080` to access the café order management system.

## Additional Notes

- The application uses Lombok to reduce boilerplate code and improve code readability.
- Log4j is integrated for logging purposes.
- Spring Boot DevTools provides additional development features like automatic restart.
- Spring Security can be configured to add authentication and authorization to the application.


