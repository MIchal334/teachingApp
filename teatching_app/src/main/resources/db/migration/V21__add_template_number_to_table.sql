
alter table lessons add lesson_template_id bigint;
alter table levels add level_template_id bigint;



alter table lessons add foreign key (lesson_template_id) references  lesson_template(id);
alter table levels add foreign key (level_template_id) references  level_template(id);
