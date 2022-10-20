insert into role(name) values('role');
insert into state(name) values('state');
insert into rules(name) values('rule');
insert into category(name) values('category');
insert into users(name,role_id) values('user',1);
insert into item(name,user_id,category_id,state_id) values ('item',1,1,1);
insert into attachs(name,item_id) values('attach',1);
insert into comments(name,item_id) values('comment',1);
insert into role_rules(role_id,rule_id) values(1,1);