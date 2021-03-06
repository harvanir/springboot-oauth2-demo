create table oauth_access_token
(
    token_id          varchar(256),
    token             bytea,
    authentication_id varchar(256),
    user_name         varchar(256),
    client_id         varchar(256),
    authentication    bytea,
    refresh_token     varchar(256)
);

create index oat_idx_token_id on oauth_access_token (token_id);
create index oat_idx_auth_id on oauth_access_token (authentication_id);
create index oat_idx_user_name on oauth_access_token (user_name);
create index oat_idx_client_id on oauth_access_token (client_id);
create index oat_idx_refresh_token on oauth_access_token (refresh_token);
create index oat_idx_user_name_client_id on oauth_access_token (user_name, client_id);