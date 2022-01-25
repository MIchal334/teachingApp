CREATE TABLE if not exists lesson_template(

    id BIGINT auto_increment primary key ,
    topic varchar(200),
    number int,
    level_template_id bigint

)
