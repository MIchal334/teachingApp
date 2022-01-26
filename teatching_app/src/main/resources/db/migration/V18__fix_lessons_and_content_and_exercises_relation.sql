
alter table lesson_contents DROP FOREIGN KEY lesson_contents_ibfk_1 ;
alter table exercises DROP FOREIGN KEY exercises_ibfk_1;


alter table lesson_contents add foreign key (lesson_id) references lesson_template(id);
alter table exercises add foreign key (lesson_id) references lesson_template(id);

alter table exercises drop answer;

