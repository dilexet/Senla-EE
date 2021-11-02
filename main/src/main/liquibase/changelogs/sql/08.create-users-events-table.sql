create table users_events
(
    user_id  bigint not null
        constraint fkqq8vriw3reievxhjwkfnrehsr
            references users,
    event_id bigint not null
        constraint fkrbcvthksj0joq0x32bjxsd0oe
            references events,
    constraint users_events_pkey
        primary key (user_id, event_id)
);

alter table users_events
    owner to postgres;