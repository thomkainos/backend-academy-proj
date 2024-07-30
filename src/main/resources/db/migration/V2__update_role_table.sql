ALTER TABLE roles
ADD `description` varchar(5000),
ADD responsibilities varchar(5000),
ADD  job_link varchar(5000);

UPDATE roles
SET
    description = 'A Software Engineer develops, tests, and maintains software solutions, working to ensure functionality, performance, and scalability in alignment with user requirements and business goals.',
    responsibilities = 'Designing and developing software applications, Writing and maintaining code, etc.',
    job_link = 'http://example.com/job-link/SoftwareEngineer'
WHERE role_id = 1;

UPDATE roles
SET
    description = 'A Product Manager oversees all aspects of a project, ensuring it is completed on time, within budget, and to the required quality standards while managing resources, risks, and stakeholder expectations',
    responsibilities = 'Defining project scope and objectives, Developing detailed project plans and schedules, etc.',
    job_link = 'http://example.com/job-link/ProjectManager'
WHERE role_id = 2;

UPDATE roles
SET
    description = 'A UX Designer creates intuitive and engaging user experiences by researching user needs, designing wireframes and prototypes, and testing interfaces to ensure seamless and user-friendly interactions',
    responsibilities = 'Conducting user research and gathering insights, Creating user personas and journey maps, etc.',
    job_link = 'http://example.com/job-link/UXDesigner'
WHERE role_id = 3;

UPDATE roles
SET
    description = 'A Data Analyst interprets complex data sets to provide actionable insights, generate reports, and support data-driven decision-making by analyzing trends, patterns, and relationships within the data.',
    responsibilities = 'Collecting, cleaning, and organizing data from various sources, Analyzing data to identify trends, patterns, and insights, etc.',
    job_link = 'http://example.com/job-link/DataAnalyst'
WHERE role_id = 4;

UPDATE roles
SET
    description = 'A DevOps Engineer automates and optimizes the software development and deployment process, integrating development and operations to enhance collaboration, improve system reliability, and accelerate delivery.',
    responsibilities = 'CImplementing and managing continuous integration and continuous deployment (CI/CD) pipelines, etc.',
    job_link = 'http://example.com/job-link/DevOpsEngineer'
WHERE role_id = 5;

UPDATE roles
SET
    description = 'A Business Analyst evaluates and interprets business needs, processes, and data to provide actionable insights and solutions that drive strategic decision-making and improve organizational efficiency.',
    responsibilities = 'Gathering and documenting business requirements and objectives, Analyzing business processes and identifying areas for improvement, etc.',
    job_link = 'http://example.com/job-link/BusinessAnalyst'
WHERE role_id = 6;

UPDATE roles
SET
    description = 'A QA Tester ensures software quality by designing and executing test cases, identifying and documenting defects, and verifying that applications meet specified requirements and standards before release.',
    responsibilities = 'Designing and executing detailed test plans and test cases, Identifying, documenting, and tracking software defects and issues, etc.',
    job_link = 'http://example.com/job-link/QATester'
WHERE role_id = 7;

UPDATE roles
SET
    description = 'An HR Specialist manages various human resources functions, including recruitment, employee relations, benefits administration, and compliance, to ensure a productive and legally compliant workplace.',
    responsibilities = 'Managing recruitment and hiring processes, including job postings and interviews, Administering employee benefits and compensation programs, etc.',
    job_link = 'http://example.com/job-link/HRSpecialist'
WHERE role_id = 8;

UPDATE roles
SET
    description = 'A Marketing Manager develops and implements marketing strategies and campaigns to drive brand awareness, attract customers, and achieve business growth objectives.',
    responsibilities = 'Developing and executing marketing strategies and campaigns, Conducting market research and analyzing consumer trends, etc.',
    job_link = 'http://example.com/job-link/MarketingManager'
WHERE role_id = 9;

UPDATE roles
SET
    description = 'A Project Coordinator supports project execution by organizing tasks, managing schedules, coordinating team activities, and ensuring that project milestones are met efficiently and on time.',
    responsibilities = 'Assisting in the development and maintenance of project plans and schedules, Coordinating and scheduling project meetings and communications, etc.',
    job_link = 'http://example.com/job-link/ProjectCoordinator'
WHERE role_id = 10;