create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products(name, producer, count, price)
values ('product1', 'producer1', 1, 1);

insert into products(name, producer, count, price)
values ('product2', 'producer2', 2, 2);

create or replace procedure p_delete_data()
language 'plpgsql'
as $$
    BEGIN
        delete from products
        where count = 1;
    END
$$;

call p_delete_data();

create or replace function f_delete_data()
returns void
language 'plpgsql'
as
$$
    begin
        delete from products
        where price = 2;
    end;
$$;

select f_delete_data();