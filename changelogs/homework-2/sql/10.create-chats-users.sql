create table chats_users
(
    chats_id integer not null
        constraint fk73kgno5353a06xm1t97uwvhkv
            references chats,
    users_id integer not null
        constraint fkfyoluwdbw5qc5vd3hv2u56pma
            references users,
    constraint chats_users_pkey
        primary key (chats_id, users_id)
);

alter table chats_users
    owner to postgres;