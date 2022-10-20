create table peoples(
    id serial primary key,
    name varchar(255)
);
 
create table adresses(
    id serial primary key,
    name varchar(255)
);
 
create table peoples_adresses(
    id serial primary key,
    people_id int references peoples(id),
    adress_id int references adresses(id)
);