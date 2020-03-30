insert into oauth_client_details(client_id, client_secret, scope, authorized_grant_types, authorities,
                                 access_token_validity, refresh_token_validity)
values ('12345', '{bcrypt}$2y$10$Vie4GGUmxsdlk55FPyR8ReflhE3hbBYGdjvwLp5IEpbtXlMUHJy2i', 'read,write,trust',
        'password,authorization_code,refresh_token,implicit', 'ROLE_CLIENT', 600, 3600);

commit;