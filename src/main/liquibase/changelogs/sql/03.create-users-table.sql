create table users
(
    id   bigint       not null
        constraint users_pkey
            primary key,
    name varchar(255) not null
);

alter table users
    owner to postgres;