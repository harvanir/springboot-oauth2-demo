create table oauth_refresh_token
(
    token_id       VARCHAR(256),
    token          bytea,
    authentication bytea
);
create index ort_idx_token_id on oauth_refresh_token (token_id);