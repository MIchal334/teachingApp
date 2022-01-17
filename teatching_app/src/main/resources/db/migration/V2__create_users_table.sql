CREATE TABLE if not exists users(

    id BIGINT auto_increment primary key ,
    username varchar(50),
    password varchar(255),
    name varchar(40),
    surname varchar(40),
    email varchar(60),
    is_deleted bit
)
