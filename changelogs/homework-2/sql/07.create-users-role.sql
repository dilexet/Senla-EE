create table users_role
(
    users_id integer not null
        constraint fkiu0xsee0dmwa28nffgyf4bcvc
            references users,
    role_id  integer not null
        constraint uk_cdpd2ix59qroxmqubyjqplxn1
            unique
        constraint fkeejqlb4gq1av9540jg66ju2pi
            references roles,
    constraint users_role_pkey
        primary key (users_id, role_id)
);

alter table users_role
    owner to postgres;