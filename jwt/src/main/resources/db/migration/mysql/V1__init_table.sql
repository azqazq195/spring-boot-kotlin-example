CREATE TABLE tb_user
(
    id          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at  DATETIME(6)  NOT NULL,
    modified_at DATETIME(6)  NOT NULL,
    deleted_at  DATETIME(6),
    age         INTEGER,
    email       VARCHAR(20)  NOT NULL UNIQUE,
    name        VARCHAR(10)  NOT NULL,
    password    VARCHAR(255) NOT NULL
);

CREATE TABLE authority
(
    name VARCHAR(20) NOT NULL PRIMARY KEY
);

CREATE TABLE tb_user_authority
(
    tb_user_id       bigint      NOT NULL,
    authorities_name VARCHAR(20) NOT NULL,
    PRIMARY KEY (tb_user_id, authorities_name),
    FOREIGN KEY (tb_user_id) REFERENCES tb_user (id),
    FOREIGN KEY (authorities_name) REFERENCES authority (name)
);

CREATE TABLE token
(
    email         VARCHAR(255) NOT NULL PRIMARY KEY,
    access_token  VARCHAR(255),
    expired_at    DATETIME(6),
    refresh_token VARCHAR(255)
);

/* 초기 필수 권한 */
INSERT INTO AUTHORITY (name)
VALUES ('ROLE_MEMBER');
INSERT INTO AUTHORITY (name)
VALUES ('ROLE_USER');