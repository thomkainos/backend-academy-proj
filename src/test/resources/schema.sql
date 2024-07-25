CREATE TABLE roles
(
    role_id      INT PRIMARY KEY,
    role_name    VARCHAR(255),
    location     VARCHAR(255),
    capability   VARCHAR(255),
    band         VARCHAR(255),
    closing_date DATE,
    role_status  INT
);
