create table event_user
(
    event_id bigint not null
        constraint fkdip9gnrj5k5vd2jlyjmxrnlch
            references events,
    user_id  bigint not null
        constraint fk45uvxaov8fbham8l63a7jkbyr
            references users,
    constraint event_user_pkey
        primary key (event_id, user_id)
);

alter table event_user
    owner to postgres;