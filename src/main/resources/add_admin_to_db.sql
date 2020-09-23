
USE spring_boot;

-- Insert data
INSERT INTO users VALUES
(10, '1@1.com', 'ADMIN', 'ADMIN'),
(15,'2@2.com', 'user', 'user');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO users_role VALUES (10, 2);
INSERT INTO users_role VALUES (10, 1);
INSERT INTO users_role VALUES (15, 1);


