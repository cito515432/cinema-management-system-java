# Cinema Management System - Java, Swing and MySQL

Academic software prototype designed to improve cinema operations through object-oriented programming, a graphical desktop interface and a relational MySQL database.

## Project evolution

The project was developed in three phases:

1. Core logic and object-oriented design
2. Graphical user interfaces for cinema interactions
3. MySQL integration and correction of previous issues

## Main capabilities

- Movie management
- Theater management
- Concession / combo management
- User validation against MySQL
- Graphical interfaces built with Java Swing
- JDBC queries using prepared statements
- UML, use-case and database design documentation

## Technologies

- Java
- Java Swing
- Object-Oriented Programming
- MySQL
- JDBC
- NetBeans / Ant

## Database configuration

Database credentials were removed from the original academic source before preparing this public repository. Configure them with environment variables:

```bash
DB_URL=jdbc:mysql://localhost:3306/cinecolombia
DB_USER=root
DB_PASSWORD=your_password
```

The original submission does not include a standalone SQL schema file. The database model is documented in `docs/database_normalization.pdf` and the project report.

The NetBeans project expects MySQL Connector/J 8.3.0 at `lib/mysql-connector-j-8.3.0.jar`; the binary is intentionally not committed.

## Documentation

- `docs/project_report.pdf`
- `docs/uml_diagrams.pdf`
- `docs/database_normalization.pdf`

## Academic context

Course: Object-Oriented Design and Programming, Universidad de San Buenaventura.

Project authored by Andres Felipe Obando Barriga with academic collaborators whose names are omitted for privacy.

## Portfolio note

This is a curated version of the original NetBeans project. Compiled files, IDE-private settings and database credentials were removed.
