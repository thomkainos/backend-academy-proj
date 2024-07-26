# kainos-job-role-manager-backend

How to start the application
---

1. Set the following environment variables:
    1. DB_USERNAME
    2. DB_PASSWORD
    3. DB_HOST
    4. DB_NAME
1. Run `mvn clean install` to build your application
1. You can start application via:
    1. Terminal: `java -jar target/java-swagger-flyway-starter-1.0-SNAPSHOT.jar server config.yml`
    2. IDE: Edit run configuration -> Add `server` to program arguments -> Run
1. To check that your application is running enter url `http://localhost:8080/api/job-roles`

Testing
--- 
To run the unit tests
1. Run `mvn clean test` to build your application

Note: integration tests are currently disabled since the backend server is currently not running outside of the application

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
