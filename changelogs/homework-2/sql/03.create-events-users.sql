create table events_users
(
    events_id integer not null
        constraint fk888vn18ltgvladmyxv3yy2a0u
            references events,
    users_id  integer not null
        constraint fk2xqmgftqei5c4s82ruxl74w9a
            references users,
    constraint events_users_pkey
        primary key (events_id, users_id)
);

alter table events_users
    owner to postgres;