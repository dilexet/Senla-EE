create table chats
(
    id       integer not null
        constraint chats_pkey
            primary key,
    name     varchar(255),
    event_id integer
        constraint fkeop5bdbqhehvh7fjxfnhualil
            references events
);

alter table chats
    owner to postgres;