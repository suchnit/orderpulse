CREATE TABLE inventory (
    id UUID PRIMARY KEY,
    product_code VARCHAR(100) UNIQUE NOT NULL,
    quantity INT NOT NULL
);
