
alter table lesson_contents add foreign key (lesson_id) references levels(id);
alter table exercises add foreign key (lesson_id) references levels(id);

