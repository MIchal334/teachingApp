CREATE TABLE if not exists exercises(

    id BIGINT auto_increment primary key ,
    question varchar(255),
    answer varchar(255),
    lesson_id bigint
)
