-- init the schema/database
CREATE DATABASE apps;

-- set the new created schema as the default db
USE apps;

-- create tables (not completed yet)
CREATE TABLE account (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(256) NOT NULL UNIQUE,
    email VARCHAR(256) NOT NULL UNIQUE,
    first_name VARCHAR(256),
    last_name VARCHAR(256),
    password VARCHAR(256) NOT NULL,
    created TIMESTAMP,
    last_updated TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE task (
    id INT NOT NULL AUTO_INCREMENT,
    account_id INT NOT NULL,
    name VARCHAR(256) NOT NULL,
    start_time DATETIME,
    end_time DATETIME,
    description VARCHAR(1024),
    created TIMESTAMP,
    last_updated TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE
);

CREATE TABLE contact (
    id INT NOT NULL AUTO_INCREMENT,
    account_id INT NOT NULL,
    contact_id INT NOT NULL,
    alias VARCHAR(256),
    created TIMESTAMP,
    last_updated TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES account(id) ON DELETE CASCADE
);