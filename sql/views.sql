create table car_bodies (
	id serial primary key,
	name varchar(255)
);

create table car_engines (
	id serial primary key,
	name varchar(255)
);

create table car_transmissions (
	id serial primary key,
	name varchar(255)
);

create table cars (
	id serial primary key,
	name varchar(255),
        body_id int references car_bodies(id),
        engine_id int references car_engines(id),
        transmission_id int references car_transmissions(id)
);

insert into car_bodies(name)
values('body');

insert into car_engines(name)
values('engine');

insert into car_transmissions(name)
values('transmission');

insert into cars(name,body_id,engine_id,transmission_id)
values('car',1,1,1);

create view show_cars
as select c.id, c.name as car_name, b.name as body_name,
e.name as engine_name, t.name as transmission_name
from cars as c
left join car_bodies as b
on c.body_id = b.id
left join car_engines as e
on c.engine_id = e.id
left join car_transmissions as t
on c.transmission_id = t.id;