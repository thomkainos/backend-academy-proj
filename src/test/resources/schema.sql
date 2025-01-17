CREATE TABLE IF NOT EXISTS roles
(
    role_id      int PRIMARY KEY AUTO_INCREMENT,
    role_name    varchar(150),
    location     varchar(150),
    capability   varchar(150),
    band         varchar(100),
    closing_date DATETIME,
    role_status  tinyint default 1,
    description varchar(5000),
    responsibilities varchar(5000),
    job_link varchar(5000)
);
