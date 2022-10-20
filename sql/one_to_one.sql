create table vins(
    id serial primary key,
    number int
);

create table cars(
    id serial primary key,
    name varchar(255),
    vin_id int references vins(id) unique
);