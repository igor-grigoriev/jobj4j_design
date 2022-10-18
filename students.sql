create table students(
	id serial primary key,
	name varchar(255),
	age integer,
	sex character
);
insert into students(name, age, sex) values('Иван', 19, 'm');
select * from students;
update students set name='Иван Петров', age=18;
delete from students;