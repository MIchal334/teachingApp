CREATE TABLE if not exists lessons(

    id BIGINT auto_increment primary key ,
    score float,
    date_of_start date,
    is_finished bit,
    level_id bigint

)
