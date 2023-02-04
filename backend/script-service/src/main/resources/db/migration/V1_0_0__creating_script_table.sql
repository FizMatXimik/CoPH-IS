CREATE TABLE scripts (
     id varchar(40) PRIMARY KEY NOT NULL,
     name varchar(255) NOT NULL,
     path varchar(255),
     url varchar(255),
     last_start_datetime TIMESTAMP,
     last_finish_datetime TIMESTAMP,
     complete BOOLEAN,
     error_message varchar(255),
     created_at TIMESTAMP NOT NULL
);