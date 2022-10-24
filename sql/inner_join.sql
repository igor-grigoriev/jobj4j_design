create table role (
	id serial primary key,
	name varchar(255)
);

create table users (
	id serial primary key,
	name varchar(255),
	role_id int references role(id)
);

insert into role(name) values('role');
insert into users(name,role_id) values('user',1);

select u.name
from role join users as u
on role_id = u.id;

select u.name, r.name
from role as r
join users as u
on role_id = u.id;

select u.name as Пользователь,
r.name as Роль
from role as r
join users as u
on role_id = u.id;