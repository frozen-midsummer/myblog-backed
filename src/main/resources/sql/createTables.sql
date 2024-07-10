CREATE TABLE UserTasks (
    username VARCHAR(255) NOT NULL,
    createdTime DATETIME,
    updatedTime DATETIME,
    deadline DATETIME,
    description VARCHAR(255),
    alarm VARCHAR(255),
    PRIMARY KEY (username)
)

ALTER TABLE usertasks
ADD COLUMN taskid INT AUTO_INCREMENT PRIMARY KEY;