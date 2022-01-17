
alter table users add column role smallint;
alter table users add foreign key (role) references roles(id);

