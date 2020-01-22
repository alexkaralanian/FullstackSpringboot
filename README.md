///// App summary
Fullstack_springboot is an simple application that allows users to Create and Retrieve students and persists those records into a Postgres relational database via a Java/Springboot service.

This project contains 2 separate applications:

1. A Java/Springboot service connected to a Postgres database running on port 8080.
2. A React client running on port 3000.

Creating and connecting to Docker/Postgres database (Do this first):
1. Install Docker desktop client (https://docs.docker.com/install/)
2. $ docker run --name fullstack_springboot -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:alpine
3. $ docker exec -it fullstack_springboot /bin/bash (bash into Docker container)
4. $ psql -U postgres (login as Postgres user)
5. $ CREATE DATABASE fullstack_springboot;
6. $ \c fullstack_springboot (connect to database)

Installing Yarn for Mac (optional):
https://yarnpkg.com/lang/en/docs/install/#mac-stable

Starting the application:
1. Start Springboot application in your IDE
2. $ cd {project_root}/src/js && npm (or yarn) install
3. npm (or yarn) start
4. Go to localhost:3000

////

Endpoints:
GET http://localhost:8080/api/students (returns all students)
POST http://localhost:8080/api/students (create new student)

/////

Dependencies / Frameworks / Libraries:

Back-end:
Springboot 2.2.2, Flyway DB version control, Docker, Postgres.

Front-end:
Create-react-app, Ant-Design CSS library, Formik, Styled-Components, Axios.

/////

Technologies / Lessons Learned:

Docker: How to bash into a container, manage containers.
PSQL: CLI tool, describing databases + schemas.
Flyway: Database migrations, version control.
SQL: Queries, creating tables, adding/dropping constraints, joins, type casting.
JAVA / Springboot: RESTful service architecture, DB layer access, Error handling, Object oriented programming.
REACT - React Hooks (useState, useEffect).
