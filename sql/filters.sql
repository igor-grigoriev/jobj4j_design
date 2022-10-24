create table type (
	id serial primary key,
	name varchar(255)
);

create table product (
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date, 
	price float
);

insert into type(name) values('type');

insert into product(name,type_id,expired_date,price)
values('product',1,'24.10.2022',1);

select * from product
where type_id =
(select id from type
where name = 'СЫР');

select * from product
where name like '%мороженое%';

select * from product
where expired_date < current_date;

select * from product where price =
(select max(price) from product);

select t.name, count(p)
from type as t
join product as p
on type_id = t.id
group by t.name;

select * from product
where type_id =
(select id from type
where name = 'СЫР'
or name = 'МОЛОКО');

select t.name, count(p)
from type as t
join product as p
on type_id = t.id
group by t.name
having count(p) < 10;

select p.name, t.name
from type as t
join product as p
on type_id = t.id;