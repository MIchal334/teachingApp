CREATE TABLE if not exists roles(

    id smallint auto_increment  primary key ,
    role_name varchar(20)
);

INSERT INTO roles (id,role_name) values (1 , 'USER');
INSERT INTO roles (id,role_name) values (2 , 'ADMIN');
