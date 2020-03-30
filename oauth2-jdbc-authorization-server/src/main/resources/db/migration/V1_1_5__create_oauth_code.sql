create table oauth_code
(
    code           varchar(256) primary key,
    authentication bytea
);