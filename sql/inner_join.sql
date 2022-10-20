select user.name, role.name from role join users on role_id = u.id;
select u.name, r.name from role as r join users as u on role_id = u.id;
select u.name as Пользователь, r.name as Роль from role as r join users as u on role_id = u.id;