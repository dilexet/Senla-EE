create table roles
(
    id       bigint       not null
        constraint roles_pkey
            primary key,
    name     varchar(255) not null,
    event_id bigint       not null
        constraint fkqsmu03bmbu838d4iidn7ijdgn
            references events,
    user_id  bigint       not null
        constraint fk97mxvrajhkq19dmvboprimeg1
            references users
);

alter table roles
    owner to postgres;