create table roles
(
    id       integer not null
        constraint roles_pkey
            primary key,
    name     varchar(255),
    event_id integer
        constraint fkqsmu03bmbu838d4iidn7ijdgn
            references events,
    user_id  integer
        constraint fk97mxvrajhkq19dmvboprimeg1
            references users
);

alter table roles
    owner to postgres;