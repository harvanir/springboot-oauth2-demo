insert into user_roles (username, authority)
values ('user@email.com', 'ROLE_CLIENT');

insert into user_roles (username, authority)
values ('admin@email.com', 'ROLE_ADMIN');

commit;