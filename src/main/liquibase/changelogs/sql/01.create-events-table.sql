create table events
(
    id          bigint       not null
        constraint events_pkey
            primary key,
    description varchar(255) not null,
    name        varchar(255) not null,
    start_date  date         not null
);

alter table events
    owner to postgres;