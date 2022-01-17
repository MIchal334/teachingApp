CREATE TABLE if not exists levels(

    id BIGINT auto_increment primary key ,
    average_score float,
    level_of_completion float,
    date_of_start date,
    date_of_end date,
    is_started bit,
    is_finished bit,
    course_id bigint

)
