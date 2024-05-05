USE `employee-db`;

DROP TABLE IF EXISTS employees;

CREATE TABLE IF NOT EXISTS employees (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    employee_id VARCHAR(36),
    name VARCHAR(255),
    age INTEGER(10),
    current_employment_status VARCHAR(50),
    full_day BOOLEAN,
    start_day INTEGER(10),
    start_month INTEGER(10),
    start_year INTEGER(10),
    end_day INTEGER(10),
    end_month INTEGER(10),
    end_year INTEGER(10),
    reason VARCHAR(255),
    days_of_the_week VARCHAR(255),
    value DECIMAL(10, 2),
    currency VARCHAR(3),
    payment_method VARCHAR(50)
    );

