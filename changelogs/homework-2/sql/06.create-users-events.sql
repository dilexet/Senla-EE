create table users_events
(
    users_id  integer not null
        constraint fk7ycpyp61jrg0dxos7vfd30t1h
            references users,
    events_id integer not null
        constraint fk56qu3rya56tll071qq9emrfwn
            references events,
    constraint users_events_pkey
        primary key (users_id, events_id)
);

alter table users_events
    owner to postgres;