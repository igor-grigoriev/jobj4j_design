﻿create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function nalog_after()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + 1
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create or replace function nalog_before()
    returns trigger as
$$
    BEGIN
        new.price = new.price + 1;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger nalog_after_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure nalog_after();
	
create trigger nalog_before_trigger
    before insert on products
    for each row
    execute procedure nalog_before();
	
create or replace function history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name,price,date) values(new.name,new.price,current_date);
		return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger history_of_price_trigger
    after insert on products
    for each row
    execute procedure history();