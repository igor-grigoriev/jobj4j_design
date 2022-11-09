create table departments (
	id serial primary key,
	name varchar(255)
);

create table employees (
	id serial primary key,
	name varchar(255),
        department_id int references departments(id)
);

create table teens (
	id serial primary key,
        name varchar(255),
        gender varchar(255)
);

insert into departments(name)
values('department1');

insert into departments(name)
values('department2');

insert into employees(name,department_id)
values('employ1',1);

insert into employees(name,department_id)
values('employ2',2);

insert into teens(name,gender)
values('name1','man');

insert into teens(name,gender)
values('name2','woman');

select * from departments d
left join employees e
on e.department_id = d.id;

select * from departments d
right join employees e
on e.department_id = d.id;

select * from departments d
full join employees e
on e.department_id = d.id;

select * from departments d
cross join employees e;

select * from departments d
left join employees e
on e.department_id = d.id where e.id is null;

select d.id, d.name, e.id, e.name
from departments d
left join employees e
on e.department_id = d.id;

select d.id, d.name, e.id, e.name
from employees e
right join departments d
on e.department_id = d.id;

select * from teens t1
cross join teens t2
where t1.gender <> t2.gender;