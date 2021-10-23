create table users
(
    id   integer not null
        constraint users_pkey
            primary key,
    name varchar(255)
);

alter table users
    owner to postgres;