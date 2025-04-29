CREATE TABLE users (
    id UUID PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    roles VARCHAR(255), -- comma-separated or create a join table for normalization
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
