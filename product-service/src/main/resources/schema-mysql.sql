USE `product-db`;

DROP TABLE IF EXISTS products;

CREATE TABLE IF NOT EXISTS products (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(36),
    name VARCHAR(50),
    description VARCHAR(250),
    pallet_id INTEGER(10),
    manufacturer VARCHAR(30),
    day INTEGER(10),
    month INTEGER(10),
    year INTEGER(10),
    product_availability VARCHAR(15),
    value DECIMAL(10,2),
    currency VARCHAR(3),
    category_name VARCHAR(30),
    category_description VARCHAR(250),
    url VARCHAR(100),
    alt_text VARCHAR(70)
    );