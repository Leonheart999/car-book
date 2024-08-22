create table users
(
    id         bigint not null
        constraint users_id_pk
            primary key,
    user_name     varchar
        constraint users_user_name_pk
            unique,
    password  varchar,
    active     boolean default true
);

alter table users
    owner to car_book;

create unique index users_id_uindex
    on users (id);

create sequence "users_id_seq";

alter sequence "users_id_seq" owned by users.id;

alter table users
    alter column id set default nextval('users_id_seq'::regclass);