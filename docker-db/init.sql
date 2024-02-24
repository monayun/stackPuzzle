CREATE TABLE user
(
    seq      BIGINT       NOT NULL,
    user_id  VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    reg_no   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (seq)
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_user UNIQUE (user_id);

INSERT INTO user (seq, user_id, password, name, reg_no) VALUES (0, 'test', 'test', 'test', 'test');
INSERT INTO user (seq, user_id, password, name, reg_no) VALUES (1, 's', 's', 's', 's');