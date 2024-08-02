# Kainos Job Role Manager - Backend
A service to retrieve and update job roles for Kainos recruitment admin as well as to process applications for applicants.

Try out the application in production here: https://w5sqymg6ru.eu-west-1.awsapprunner.com/swagger#/Kainos%20Job%20Role%20Manager%20API/getListOpenJobRoles
- Make sure to select https

## Table of Contents

- [Setting up the Database](#db-setup)
    - [Database Migration - Local](#db-local)
    - [Database Migration - Production](#db-prod)
- [How to start](#start)
- [Endpoints](#endpoints)
- [Testing](#test)
- [Health Checks](#health)


## Setting up the Database
### Database Migration - Local
1. Add your SQL script to `resources.db.migration` directory
2. Add the following lines to your ~/.zshrc file:

```
export FLYWAY_URL="jdbc:mysql://YOUR_DB_HOST/YOUR_DB_NAME"
export FLYWAY_USER="YOUR_DB_USERNAME"
export FLYWAY_PASSWORD="YOUR_DB_PASSWORD"
export FLYWAY_BASELINE_ON_MIGRATE=true
```

3. Reload your terminal session if required:

```
. ~/.zshrc
```

4. Run Flyway command through Maven:

```
mvn flyway:migrate
```

### Database Migration - Production


1. Add following secrets to your Github repo:

```
DB_USERNAME - the prod db username
DB_PASSWORD - the prod db password
DB_HOST - the prod db host
DB_NAME - the prod db name
```

2. Raise a pull request with your script in the `resources.db.migration` directory
3. After approvals, merge pull request; this will trigger the migration action to run in Github
4. Ensure migration successfully runs against prod database

How to start the application (locally)
---
1. Set the following environment variables:
    1. DB_USERNAME
    2. DB_PASSWORD
    3. DB_HOST
    4. DB_NAME
1. Run `mvn clean install` to build your application
1. You can start application via:
    1. Terminal: `java -jar target/kainos-job-role-manager.jar server config.yml`
    2. IDE: Edit run configuration -> Add `server` to program arguments -> Run
1. To check that your application is running enter url `http://localhost:8080/api/job-roles`

## Endpoints
### Get list of open job roles
#### Request
`GET /api/job-roles`

```
curl -i -H 'Accept: application/json' http://localhost:8080/api/job-roles
```

#### Response
    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 2
    
    [
        {
            "roleId": 11,
            "roleName": "Software Engineer",
            "location": "London",
            "capability": "Full Stack Development",
            "band": "Band 1",
            "closingDate": 1723694400000,
            "roleStatus": 1
            },
        {
            "roleId": 12,
            "roleName": "Product Manager",
            "location": "Belfast",
            "capability": "Product Strategy",
            "band": "Band 2",
            "closingDate": 1724299200000,
            "roleStatus": 1
        }
    ]

### Login
#### Request
`POST /api/auth/login`

```
curl -X POST "http://localhost:8080/api/auth/login" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"username\": \"user1\", \"password\": \"user1\" }"
```

#### Response
    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 2
    
    Response body:

    eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MjI2MTUxOTYsImV4cCI6MTcyMjYxNTE5NiwiUm9sZSI6MSwic3ViIjoidXNlcjEiLCJpc3MiOiJLYWlub3MgSm9iIFJvbGUgTWFuYWdlciJ9.Fm7Ty50S3DIKjNYeRva3_axkYsg2hc3p193BDTKS9_8

## Test
To run the unit tests:
1. Run:

```
mvn clean test
```

Note: integration tests are currently disabled since the backend server is currently not running outside of the application

## Health Checks

To see your applications health enter url `http://localhost:8081/healthcheck`
