# Java Spring JDBC
The target of this exercise is to practice Spring JDBC with Java 17.

## Features
- CRUD of user project
- Generate data in memory and INSERT batches
- Specific SELECT with JOINs

## Requirements
- Install Docker
- Download Postgres Docker image
- Install psql client

## Run the project
1. Run postgres through Docker with following command: `docker run --name lil-postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres`
2. Run psql command to create DB: `psql -h localhost -U postgres -f database.sql` and enter the password: `password`
3. Run Main program.