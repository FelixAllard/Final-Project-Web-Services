USE `transaction-db`;

DROP TABLE IF EXISTS purchase_receipts;

CREATE TABLE IF NOT EXISTS purchase_receipts (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    purchase_receipt_id VARCHAR(50),
    client_id VARCHAR(50),
    product_id VARCHAR(50),
    employee_id VARCHAR(50),
    amount DECIMAL(10, 2),
    transaction_hour INTEGER(10),
    day INTEGER(10),
    month INTEGER(10),
    year INTEGER(10),
    value DECIMAL(10, 2),
    currency VARCHAR(10),
    street VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(50),
    postal_code VARCHAR(20),
    country VARCHAR(50)
    );

