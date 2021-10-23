create table users_chats
(
    users_id integer not null
        constraint fk93treaskxd9hqvhst7qjyvx65
            references users,
    chats_id integer not null
        constraint fk8vjil3rdyjvh9qkyfjpsit2d
            references chats,
    constraint users_chats_pkey
        primary key (users_id, chats_id)
);

alter table users_chats
    owner to postgres;
