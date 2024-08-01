CREATE TABLE IF NOT EXISTS `user` (
    username varchar(64) PRIMARY KEY,
    password varchar(100) NOT NULL,
    salt varchar(100) NOT NULL,
    sys_role_id TINYINT NOT NULL,
    FOREIGN KEY (sys_role_id) REFERENCES `system_role`(sys_role_id)
);
