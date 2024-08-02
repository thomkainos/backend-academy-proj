FROM maven:latest

WORKDIR /code

COPY . /code

ARG API_URL
ARG DB_HOST
ARG DB_PASSWORD
ARG DB_USERNAME
ARG DB_NAME

ENV API_URL ${API_URL}
ENV DB_HOST ${DB_HOST}
ENV DB_PASSWORD ${DB_PASSWORD}
ENV DB_USERNAME ${DB_USERNAME}
ENV DB_NAME ${DB_NAME}

RUN mvn clean install -DskipTests=true

EXPOSE 8080

CMD ["java","-jar", "/code/target/kainos-job-role-manager.jar", "server", "/code/config.yml"]