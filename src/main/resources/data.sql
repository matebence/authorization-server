DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details (
    client_id VARCHAR(256) PRIMARY KEY,
    resource_ids VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
);

DROP TABLE IF EXISTS oauth_client_token;

CREATE TABLE oauth_client_token (
    token_id VARCHAR(256),
    token bytea,
    authentication_id VARCHAR(256),
    user_name VARCHAR(256),
    client_id VARCHAR(256)
);
DROP TABLE IF EXISTS oauth_access_token;

CREATE TABLE oauth_access_token (
    token_id VARCHAR(256),
    token bytea,
    authentication_id VARCHAR(256),
    user_name VARCHAR(256),
    client_id VARCHAR(256),
    authentication bytea,
    refresh_token VARCHAR(256)
);
DROP TABLE IF EXISTS oauth_refresh_token;

CREATE TABLE oauth_refresh_token (
    token_id VARCHAR(256),
    token bytea,
    authentication bytea
);
DROP TABLE IF EXISTS oauth_code;

CREATE TABLE oauth_code (
    code VARCHAR(256), authentication bytea
);

INSERT INTO accounts (user_name, password, balance, is_activated, created_by, created_at, is_deleted)
VALUES ('matebence', '$2a$10$jbIi/RIYNm5xAW9M7IaE5.WPw6BZgD8wcpkZUg0jm8RHPtdfDcMgm', 200.00, TRUE, 1, CURRENT_TIMESTAMP, FALSE);
INSERT INTO accounts (user_name, password, balance, is_activated, created_by, created_at, is_deleted)
VALUES ('ss', '$2a$10$jbIi/RIYNm5xAW9M7IaE5.WPw6BZgD8wcpkZUg0jm8RHPtdfDcMgm', 500.00, TRUE, 1, CURRENT_TIMESTAMP, FALSE);
INSERT INTO logins (account_id, no_trys, last_login, ip_address)
VALUES (1, 0, CURRENT_TIMESTAMP, '192.168.99.100');
INSERT INTO roles (name, created_by, created_at, is_deleted)
VALUES ('ADMIN_ROLE', 1, CURRENT_TIMESTAMP, FALSE);
INSERT INTO privileges (name, created_by, created_at ,is_deleted)
VALUES ('CREATE_TABLE_NAME', 1, CURRENT_TIMESTAMP, FALSE);
INSERT INTO privileges (name, created_by, created_at, is_deleted)
VALUES ('READ_TABLE_NAME', 1, CURRENT_TIMESTAMP, FALSE);
INSERT INTO privileges (name, created_by, created_at, is_deleted)
VALUES ('UPDATE_TABLE_NAME', 1, CURRENT_TIMESTAMP, FALSE);
INSERT INTO privileges (name, created_by, created_at, is_deleted)
VALUES ('DELETE_TABLE_NAME', 1, CURRENT_TIMESTAMP, FALSE);
INSERT INTO role_privilege_items (role_id, privilege_id)
VALUES (1, 1);
INSERT INTO role_privilege_items (role_id, privilege_id)
VALUES (1, 2);
INSERT INTO role_privilege_items (role_id, privilege_id)
VALUES (1, 3);
INSERT INTO role_privilege_items (role_id, privilege_id)
VALUES (1, 4);
INSERT INTO account_role_items (user_id, role_id)
VALUES (1, 1);