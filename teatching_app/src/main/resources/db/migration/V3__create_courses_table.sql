CREATE TABLE if not exists courses(

    id BIGINT auto_increment primary key ,
    subject varchar(50),
    average_score float,
    level_of_completion float,
    date_of_start date,
    date_of_end date,
    is_started bit,
    is_finished bit,
    user_id bigint

)
