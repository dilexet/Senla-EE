create table chats_messages
(
    chats_id    integer not null
        constraint fkss8vyj7gos9tvhtpw7soteh55
            references chats,
    messages_id integer not null
        constraint uk_dvq7vtmykmop3jsntw68spp3
            unique
        constraint fkofq36r2soyfr1exsj0kih7n55
            references messages,
    constraint chats_messages_pkey
        primary key (chats_id, messages_id)
);

alter table chats_messages
    owner to postgres;