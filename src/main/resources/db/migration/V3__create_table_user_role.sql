CREATE TABLE user_role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    duelist_id CHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    role_id TINYINT NOT NULL,
    FOREIGN KEY (duelist_id) REFERENCES duelist(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
) CHARACTER SET utf8 COLLATE utf8_general_ci;

INSERT INTO user_role (duelist_id, role_id) VALUES ('4877c970-4c45-4a3d-be3a-77d2f7846499', 1);
INSERT INTO user_role (duelist_id, role_id) VALUES ('4877c970-4c45-4a3d-be3a-77d2f7846499', 2);