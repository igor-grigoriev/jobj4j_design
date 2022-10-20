create table users (
    id serial primary key,
    name varchar(255),
    email varchar(255)
);

create table accounts (
    id serial primary key,
    login varchar(255),
    password varchar(255),
    user_id int references users(id)
);