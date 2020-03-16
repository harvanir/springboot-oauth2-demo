insert into users (username, password, enabled, client_id)
values ('user@email.com', '{bcrypt}$2y$10$Vie4GGUmxsdlk55FPyR8ReflhE3hbBYGdjvwLp5IEpbtXlMUHJy2i', true, '12345');
commit;

insert into authorities (username, authority)
values ('user@email.com', 'ROLE_CLIENT');
commit;