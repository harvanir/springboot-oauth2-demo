insert into users (username, password, enabled)
values ('user@email.com', '{bcrypt}$2y$10$Vie4GGUmxsdlk55FPyR8ReflhE3hbBYGdjvwLp5IEpbtXlMUHJy2i', true);

insert into users (username, password, enabled)
values ('admin@email.com', '{bcrypt}$2y$10$TWB3Y00Xg.sp5Y3tezONru0GDU5rvSJbk67i0YtK0xm73guC1at/C', true);

commit;