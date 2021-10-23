create table events
(
    chat_id     integer,
    id          integer not null
        constraint events_pkey
            primary key,
    description varchar(255),
    name        varchar(255),
    start_date  date
);

alter table events
    owner to postgres;