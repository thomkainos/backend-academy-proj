# Kainos Job Role Manager - Backend

A service to retrieve and update job roles for Kainos recruitment admin as well as to process applications for applicants.
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

Database Migration is handled by running `docker compose up` please see the [How to start](#start) section

This spins up a database in docker, and then applies the db migration files in `src/main/resources/db/migration` using flyway.

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

## How to start the application

1. cd into docker directory
2. Run `docker compose up -d` where -d runs the command without logs. Note: you can also use intellij to control the services run in the command file
   
1. To check that your application is running enter url:

```
http://localhost:8080/api/job-roles
```

## How to stop the application

1. cd into docker directory
2. Run `docker compose down'
3. Check for any volumes using `docker volume ls`
4. Kill the volumes related to the app by running `docker volume rm <the volume>`

## Connecting to the local databse

In docker the database will run on `mysql:3306/mydb`. Outside of docker this will be `localhost:3306/mydb`

You can use Intellij's database tool to connect to the db. To find the connection parameters see the `.env` and `docker-compose.yml` in the `/docker` directory.


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


## Test
To run the unit tests:
1. Run:

```
mvn clean test
```

Note: integration tests are currently disabled since the backend server is currently not running outside of the application

## Health Checks


To see your applications health enter url `http://localhost:8081/healthcheck`
