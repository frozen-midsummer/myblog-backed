CREATE TABLE usertasks (
    task_id BIGINT NOT NULL,
    username VARCHAR(255) NOT NULL,
    created_time DATETIME,
    updated_time DATETIME,
    deadline DATETIME,
    description VARCHAR(255),
    alarm VARCHAR(255),
    PRIMARY KEY (task_id)
)

CREATE TABLE users (
    id BIGINT NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
)
