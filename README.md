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


## <a name="#db-setup">Setting up the Database</a>
### <a name="#db-local">Database Migration - Local</a>


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

### <a name="#db-prod">Database Migration - Production</a>


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

## <a name="#start">How to start the application</a>

1. Set the following environment variables:
    1. DB_USERNAME
    2. DB_PASSWORD
    3. DB_HOST
    4. DB_NAME
   
1. To build your application run:

```
mvn clean install
```

1. You can start application via:
    1. Terminal: 

```
java -jar target/java-swagger-flyway-starter-1.0-SNAPSHOT.jar server config.yml
```

   2. IDE: Edit run configuration -> Add `server` to program arguments -> Run
   
1. To check that your application is running enter url:

```
http://localhost:8080/api/job-roles
```

## <a name="#endpoints">Endpoints</a>
### Get list of open job roles

`GET /api/job-roles`


## <a name="#test">Test</a>
To run the unit tests:
1. Run:

```
mvn clean test
```

Note: integration tests are currently disabled since the backend server is currently not running outside of the application

## <a name="#health">Health Checks</a>


To see your applications health enter url `http://localhost:8081/healthcheck`
