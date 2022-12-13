CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into customers(first_name,last_name,age,country)
values ('Иван','Петров',31,'Россия');
insert into customers(first_name,last_name,age,country)
values ('Петр','Иванов',32,'Беларусь');

insert into orders(amount,customer_id) values(2,1);
insert into orders(amount,customer_id) values(3,2);

select * from customers
where age = (select min(age) from customers);
select * from customers
where id not in
(select customer_id from orders where amount > 0);