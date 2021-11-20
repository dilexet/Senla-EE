create table messages
(
    id        bigint       not null
        constraint messages_pkey
            primary key,
    send_date date         not null,
    text      varchar(255) not null,
    chat_id   bigint       not null
        constraint fk64w44ngcpqp99ptcb9werdfmb
            references chats,
    user_id   bigint       not null
        constraint fkpsmh6clh3csorw43eaodlqvkn
            references users
);

alter table messages
    owner to postgres;