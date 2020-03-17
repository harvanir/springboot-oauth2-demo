create table oauth_code
(
    code           VARCHAR(256) PRIMARY KEY,
    authentication bytea
);