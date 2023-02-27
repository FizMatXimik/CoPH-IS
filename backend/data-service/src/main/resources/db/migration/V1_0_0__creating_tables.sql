CREATE TABLE companies (
     id varchar(40) PRIMARY KEY NOT NULL,
     name varchar(255) NOT NULL,
     inn varchar(20),
     phone varchar(20),
     home_url varchar(255),
     email varchar(255),
     type_id int,
     address varchar(255),
     description text,
     created_at TIMESTAMP NOT NULL
);