# ProjectManager. This service is designed for project management.
The following technologies were used in the development this program:
- Spring Boot, Spring Web, Spring Security, Spring Data JPA, criteria API;
- Liquibase;
- PostgreSQL;
- MongoDB;
- Redis (cash);
- Mapstruct;
- JUnit;
- Mockito;
- Apache CSV;
- Swagger;
- Lombok;
- Open Feign;
- Kafka;
- Docker;
- Kubernetes;

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
> you can execute data.sql;
- postman/insomnia/ or ...localhost:8081/swagger: /auth/login enter login/password (admin/admin), get token, 
then go to needed url and use header: Authorization, value: token without quotes. 