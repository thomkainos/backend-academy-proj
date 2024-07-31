CREATE TABLE IF NOT EXISTS `system_role` (
     sys_role_id TINYINT PRIMARY KEY AUTO_INCREMENT,
     sys_role_name varchar(64) NOT NULL
);

INSERT INTO `system_role`(sys_role_name) VALUES ('User');