create table users_chats
(
    user_id bigint not null
        constraint fkm9idubc8h2nd586vuvands3ti
            references users,
    chat_id bigint not null
        constraint fkpnxkruh2u71cnyc8y91s6ydpf
            references chats,
    constraint users_chats_pkey
        primary key (user_id, chat_id)
);

alter table users_chats
    owner to postgres;