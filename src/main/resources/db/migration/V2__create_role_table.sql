CREATE TABLE roles (
   role_id int PRIMARY KEY AUTO_INCREMENT,
   role_name varchar(150),
   location varchar(150),
   capability varchar(150),
   band varchar(100),
   closing_date DATETIME,
   role_status tinyint default 1
);

INSERT INTO roles (role_name, location, capability, band, closing_date, role_status) VALUES
 ('Software Engineer', 'London', 'Full Stack Development', 'Band 1', '2024-08-15 23:59:59', 1),
 ('Product Manager', 'Belfast', 'Product Strategy', 'Band 2', '2024-08-22 23:59:59', 1),
 ('UX Designer', 'Dublin', 'User Experience Design', 'Band 1', '2024-09-01 23:59:59', 0),
 ('Data Analyst', 'Edinburgh', 'Data Analysis', 'Band 1', '2024-08-30 23:59:59', 1),
 ('DevOps Engineer', 'Cardiff', 'DevOps Practices', 'Band 2', '2024-09-05 23:59:59', 1),
 ('Business Analyst', 'Glasgow', 'Business Analysis', 'Band 3', '2024-08-20 23:59:59', 0),
 ('QA Tester', 'Manchester', 'Quality Assurance', 'Band 1', '2024-09-10 23:59:59', 1),
 ('HR Specialist', 'Birmingham', 'Human Resources', 'Band 2', '2024-08-25 23:59:59', 1),
 ('Marketing Manager', 'Leeds', 'Marketing Strategies', 'Band 2', '2024-09-12 23:59:59', 0),
 ('Project Coordinator', 'Liverpool', 'Project Coordination', 'Band 3', '2024-09-18 23:59:59', 1);