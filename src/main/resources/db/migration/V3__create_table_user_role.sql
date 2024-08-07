CREATE TABLE user_role (
    duelist_id CHAR(36) NOT NULL,
    role_id TINYINT NOT NULL,
    PRIMARY KEY (duelist_id, role_id),
    FOREIGN KEY (duelist_id) REFERENCES duelist(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

INSERT INTO user_role (duelist_id, role_id) VALUES ('550e8400-e29b-41d4-a716-446655440000', 1);