CREATE TABLE IF NOT EXISTS `user` (
    username varchar(64) PRIMARY KEY,
    password varchar(100) NOT NULL,
    salt varchar(100) NOT NULL,
    sys_role_id TINYINT NOT NULL,
    FOREIGN KEY (sys_role_id) REFERENCES `system_role`(sys_role_id)
);

-- dummy hashes generated with https://bcrypt-generator.com/
-- plain text passwords for user1 = user1, user2 = user2
INSERT INTO `user`(username, password, salt, sys_role_id) VALUES ('user1', '$2a$10$y8xq67JaPFfqobHYtA5uUusEpScoL0pc/S8P3/jrrPMcA0NzOh4v2', '$2a$10$y8xq67JaPFfqobHYtA5uUu', 1);
INSERT INTO `user`(username, password, salt, sys_role_id) VALUES ('user2', '$2a$10$MO2AzV0f0Vtxn6LzQvpx4ef.G/woxZZ88IMZ9QCdF68OmPQ1ivRh.', '$2b$10$y6i/7sKQ5S/jWw8hizueuf', 1);