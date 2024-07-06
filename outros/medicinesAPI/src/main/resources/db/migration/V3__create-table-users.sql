CREATE TABLE users (
    id bigint NOT NULL auto_increment,
    login varchar(100) NOT NULL,
    senha varchar(100) NOT NULL,
    PRIMARY KEY(id)
);