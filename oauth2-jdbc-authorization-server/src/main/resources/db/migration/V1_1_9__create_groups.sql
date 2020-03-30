create table groups
(
    id         bigint generated by default as identity (start with 1) primary key,
    group_name varchar(256) not null
);

create table group_authorities
(
    group_id  bigint      not null,
    authority varchar(50) not null,
    constraint fk_group_authorities_group foreign key (group_id) references groups (id),
    unique (group_id, authority)
);

create table group_members
(
    id       bigint generated by default as identity (start with 1) primary key,
    username varchar(50) not null,
    group_id bigint      not null,
    constraint fk_group_members_group foreign key (group_id) references groups (id),
    constraint fk_group_members_users foreign key (username) references users (username)
);