# ProjectManager. This service is designed for project management.
The following technologies were used in the development this program:
- Spring Boot, Spring Web, Spring Security, Spring Data JPA, criteria API;
- Liquibase;
- PostgreSQL;
- Mapstruct;
- JUnit;
- Mockito;
- Apache CSV;
- Swagger;
- Open Feign;
- Kafka;
- Docker.

 The payment function is implemented in the AccountManager microservice (It is launched simultaneously with this service).

For run this app:
- start postgres in your system or by command:
>docker-compose -f -d docker-compose.yml up
> or
> docker-compose -d up

- create database "management_db" (PostgresSQl);
- http://localhost:5432/ with login "postgres", password "12345".

For access to API:
- create user: admin, adminov, admin, admin (notice: insert encrypt of admin into db)
and role: 1, ADMIN
> you can execute admin.sql;
- go to localhost:8080/swagger-ui.html and sign with admin, admin