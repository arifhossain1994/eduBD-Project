CREATE TABLE schools
(
    id                  BIGINT(20)          AUTO_INCREMENT PRIMARY KEY,
    school_name         VARCHAR(255)        NOT NULL,
    school_email        VARCHAR(255)        UNIQUE NOT NULL,
    school_street       VARCHAR(255),
    school_house        VARCHAR(255),
    school_zip          BIGINT(10),
    school_city         VARCHAR(255),
    school_state        VARCHAR(255),
    school_country      VARCHAR(255),
    school_phone        BIGINT(20),
    status              VARCHAR(255)        NOT NULL,
    image               VARCHAR(255),
    created_by          BIGINT(20)          NULL,
    created_date        BIGINT(20)          NOT NULL,
    updated_date        BIGINT(20)          DEFAULT NULL,
    updated_by          BIGINT(20)          NULL
);

CREATE TABLE admins
(
    id           BIGINT(20)          AUTO_INCREMENT PRIMARY KEY,
    school_id    BIGINT(20)          NOT NULL,
    first_name   VARCHAR(255)        NOT NULL,
    last_name    VARCHAR(255)        NOT NULL,
    email        VARCHAR(255)        UNIQUE NOT NULL,
    password     VARCHAR(255)        NOT NULL,
    admin_phone  BIGINT(20),
    status       VARCHAR(255)        NOT NULL,
    image        VARCHAR(255)        NOT NULL,
    created_date BIGINT(20)          NOT NULL,
    updated_date BIGINT(20)          DEFAULT NULL,
    role         varchar(255)        NOT NULL,
    created_by   BIGINT(20)          NULL,
    updated_by   BIGINT(20)          NULL,
    FOREIGN KEY (school_id) REFERENCES schools(id)
);



