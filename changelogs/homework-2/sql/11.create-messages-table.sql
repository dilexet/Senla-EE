create table messages
(
    id        integer not null
        constraint messages_pkey
            primary key,
    send_date date,
    text      varchar(255),
    sender_id integer
        constraint fk4ui4nnwntodh6wjvck53dbk9m
            references users
);

alter table messages
    owner to postgres;