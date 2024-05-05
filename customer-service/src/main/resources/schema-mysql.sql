USE `customer-db`;

DROP TABLE IF EXISTS clients;

CREATE TABLE IF NOT EXISTS clients (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    client_id VARCHAR(36),
    name VARCHAR(50),
    email VARCHAR(40),
    phone VARCHAR(20),
    street VARCHAR(30),
    city VARCHAR(15),
    state VARCHAR(20),
    postal_code VARCHAR(7),
    country VARCHAR(13),
    total_spent DECIMAL(10,2),
    number_of_points INTEGER,
    membership_status VARCHAR(20),
    value DECIMAL(10,2),
    currency VARCHAR(3)
)