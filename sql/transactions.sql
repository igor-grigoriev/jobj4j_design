create table students(
	id serial primary key,
	name varchar(255),
	age integer
);

insert into students(name, age) values('Иван', 19);
insert into students(name, age) values('Петр', 21);