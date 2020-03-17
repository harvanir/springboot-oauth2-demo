insert into users (username, password, enabled)
values ('user@email.com', '{bcrypt}$2y$10$Vie4GGUmxsdlk55FPyR8ReflhE3hbBYGdjvwLp5IEpbtXlMUHJy2i', true);
commit;

insert into authorities (username, authority)
values ('user@email.com', 'ROLE_CLIENT');
commit;