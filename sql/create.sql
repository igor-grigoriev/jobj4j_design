create table role (
	id serial primary key,
	name varchar(255)
);

create table rules (
	id serial primary key,
	name varchar(255)
);

create table state (
	id serial primary key,
	name varchar(255)
);

create table category (
	id serial primary key,
	name varchar(255)
);

create table users (
	id serial primary key,
	name varchar(255),
	role_id int references role(id)
);

create table item (
	id serial primary key,
	name varchar(255),
	user_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);

create table attachs (
	id serial primary key,
	name varchar(255),
	item_id int references item(id)
);

create table comments (
	id serial primary key,
	name varchar(255),
	item_id int references item(id)
);

create table role_rules (
	id serial primary key,
	role_id int references role(id),
	rule_id int references rules(id)
);