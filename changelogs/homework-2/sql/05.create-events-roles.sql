create table events_roles
(
    events_id integer not null
        constraint fk5m2y96gkmrwsuhd5oldoh1a60
            references events,
    roles_id  integer not null
        constraint uk_othehqy8qpdvg3v7ggvwwx2k0
            unique
        constraint fk1tviks2cn4hsndwy2athdyrp0
            references roles,
    constraint events_roles_pkey
        primary key (events_id, roles_id)
);

alter table events_roles
    owner to postgres;