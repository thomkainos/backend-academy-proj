CREATE TABLE IF NOT EXISTS `user` (
    username varchar(64) PRIMARY KEY,
<<<<<<< HEAD
    password varchar(100) NOT NULL,
    salt varchar(100) NOT NULL,
    sys_role_id TINYINT NOT NULL,
    FOREIGN KEY (sys_role_id) REFERENCES `system_role`(sys_role_id)
);
=======
    password varchar(64) NOT NULL,
    sys_role_id TINYINT NOT NULL,
    FOREIGN KEY (sys_role_id) REFERENCES `system_role`(sys_role_id)
);
>>>>>>> 6ea3486 (test: created all unit tests for MySqlAuthDao)
