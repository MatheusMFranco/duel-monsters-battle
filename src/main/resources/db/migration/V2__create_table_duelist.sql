CREATE TABLE duelist (
    id CHAR(36) PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    avatar VARCHAR(255) NOT NULL,
    register_date DATETIME NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO duelist (id, email, name, avatar, register_date, password) VALUES  ('550e8400-e29b-41d4-a716-446655440000', 'john.doe@example.com', 'John Doe', 'avatar1.png', '2024-08-07', '$2a$12$Dpr9yBjZksrrC34hnQEG1uDyF5HKckz3Cob4j5md1Jl3jXPF1ejzi');