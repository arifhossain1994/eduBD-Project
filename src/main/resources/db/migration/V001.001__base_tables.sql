CREATE TABLE profiles
(
    id           BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    project      VARCHAR(255) NOT NULL,
    created_date BIGINT(25)   NOT NULL,
    updated_date BIGINT(25) DEFAULT NULL
);

CREATE TABLE users
(
    id           BIGINT(20)          AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255)        NOT NULL,
    email        VARCHAR(255)        UNIQUE NOT NULL,
    password     VARCHAR(255)        NOT NULL,
    status       VARCHAR(255)        NOT NULL,
    image        VARCHAR(255)        NOT NULL,
    created_date BIGINT(20)          NOT NULL,
    updated_date BIGINT(20)          DEFAULT NULL,
    role         varchar(255)        NOT NULL,
    created_by   BIGINT(20)          NULL,
    updated_by   BIGINT(20)          NULL
);



CREATE TABLE user_audit (
                            id           BIGINT(20)          AUTO_INCREMENT PRIMARY KEY,
                            user_id      BIGINT (20)         NOT NULL,
                            name         VARCHAR(255)        NOT NULL,
                            email        VARCHAR(255)        UNIQUE NOT NULL,
                            password     VARCHAR(255)        NOT NULL,
                            status       VARCHAR(255)        NOT NULL,
                            image        VARCHAR(255)        NOT NULL,
                            created_date BIGINT(20)          NOT NULL,
                            updated_date BIGINT(20)          DEFAULT NULL,
                            role         varchar(255)        NOT NULL,
                            created_by   BIGINT(20)          NULL,
                            updated_by   BIGINT(20)          NULL,
                            FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE goals
(
    id                  BIGINT(20)      AUTO_INCREMENT PRIMARY KEY,
    user_id             BIGINT(20),
    name                VARCHAR(255)    NOT NULL,
    description         TEXT            NULL,
    current_value       DECIMAL(15,2)   NULL,
    total_value         DECIMAL(15,2)   NULL,
    status              VARCHAR(255)    NOT NULL,
    created_date        BIGINT(20)      NOT NULL,
    updated_date        BIGINT(20)      DEFAULT NULL,
    created_by          BIGINT(20)      NULL,
    updated_by          BIGINT(20)      DEFAULT NULL,
    FOREIGN KEY (user_id)    REFERENCES     users(id),
    FOREIGN KEY (created_by) REFERENCES     users(id),
    FOREIGN KEY (updated_by) REFERENCES     users(id)
);


CREATE TABLE goal_audit(
                           id           BIGINT AUTO_INCREMENT PRIMARY KEY ,
                           goal_id      BIGINT NOT NULL ,
                           user_id      BIGINT NOT NULL ,
                           name         VARCHAR(40) NOT NULL ,
                           description  VARCHAR(255) ,
                           status       VARCHAR (30),
                           current_value FLOAT (4), --(SIZE OF BITS, DIGITS AFTER DECIMAL)
                           total_value   INT (4) NOT NULL ,
                           created_date  TIMESTAMP NOT NULL ,
                           updated_date  TIMESTAMP DEFAULT NULL ,
                           created_by    BIGINT(20)          NULL,
                           updated_by    BIGINT(20)          NULL,
                           FOREIGN KEY (goal_id) REFERENCES goals(id)
);

CREATE TABLE post(
                     id             BIGINT AUTO_INCREMENT PRIMARY KEY ,
                     user_id        BIGINT NOT NULL ,
                     goal_id        BIGINT NOT NULL,
                     name           VARCHAR(40) NOT NULL ,
                     image          VARCHAR(255) DEFAULT NULL,
                     status         VARCHAR (30),
                     current_value FLOAT (4), --(SIZE OF BIT)
                     created_date   TIMESTAMP NOT NULL ,
                     updated_date   TIMESTAMP DEFAULT NULL ,
                     created_by     BIGINT(20)          NULL,
                     updated_by     BIGINT(20)          NULL,
                     FOREIGN KEY (user_id) REFERENCES users(id),
                     FOREIGN KEY (goal_id) REFERENCES goals(id)

);

CREATE TABLE post_audit(
                           id           BIGINT AUTO_INCREMENT PRIMARY KEY ,
                           post_id      BIGINT NOT NULL ,
                           user_id      BIGINT NOT NULL ,
                           goal_id      BIGINT NOT NULL ,
                           name         VARCHAR(40) NOT NULL ,
                           image        VARCHAR(255) ,
                           status       VARCHAR (30),
                           current_value FLOAT (4), --(SIZE OF BIT)
                           created_date  TIMESTAMP NOT NULL ,
                           updated_date  TIMESTAMP DEFAULT NULL ,
                           created_by    BIGINT(20)          NULL,
                           updated_by    BIGINT(20)          NULL,
                           FOREIGN KEY (post_id) REFERENCES post(id)
);

