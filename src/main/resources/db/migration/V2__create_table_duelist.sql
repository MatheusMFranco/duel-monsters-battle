CREATE TABLE duelist (
    id CHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci PRIMARY KEY,
    email VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL UNIQUE,
    name VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    avatar VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    register_date DATETIME NOT NULL,
    password VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) CHARACTER SET utf8 COLLATE utf8_general_ci;

INSERT INTO duelist (id, email, name, avatar, register_date, password) VALUES  ('4877c970-4c45-4a3d-be3a-77d2f7846499', 'john.doe@example.com', 'John Doe', 'avatar1.png', '2024-08-07', '$2a$12$Dpr9yBjZksrrC34hnQEG1uDyF5HKckz3Cob4j5md1Jl3jXPF1ejzi');