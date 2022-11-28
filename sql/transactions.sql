create table students(
	id serial primary key,
	name varchar(255),
	age integer
);

insert into students(name, age) values('Иван', 19);
insert into students(name, age) values('Петр', 21);

begin transaction;

select * from students;

update students set age = 20;

savepoint first;

insert into students(name,age) values('Ivan',33);

savepoint second;

select * from students;

rollback to first;

select * from students;

rollback to second;

select * from students;

rollback;