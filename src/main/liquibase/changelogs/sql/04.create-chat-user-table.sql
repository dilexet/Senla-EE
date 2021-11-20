create table chat_user
(
    chat_id bigint not null
        constraint fklvwdc7tp67s3s4m5rrsa2nafj
            references chats,
    user_id bigint not null
        constraint fkb1gw4q5ahnprgk3f47gj5o3nw
            references users,
    constraint chat_user_pkey
        primary key (chat_id, user_id)
);

alter table chat_user
    owner to postgres;