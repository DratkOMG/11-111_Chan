-- drop table if exists book;
-- drop table if exists user_profile;
-- drop table if exists account;
-- drop table if exists Categories;

create table categories
(
    categories_id   bigserial,
    categories_name varchar(50),
    constraint pk_categories_id primary key (categories_id)
);


create table book
(
    book_id       bigserial primary key,
    title         varchar(200),
    image         varchar(200),
    price         integer check ( price > 0 ),
    author        varchar(30),
    description   varchar,
    categories_id int not null,
    constraint fk_book_categories_id
        foreign key (categories_iD) references Categories (categories_id)
            on update cascade,
    seller_id     bigint,
    constraint fk_book_account_id
        foreign key (seller_id) references account (account_id)
            on update cascade

);

alter table book
add column quantity_sold int not null default 0;

create table account
(
    account_id bigserial,
    email      varchar(50),
    password   varchar,
    is_admin   bit,
    is_seller  bit,
    constraint pk_id_email primary key (account_id)
);

create table user_profile
(
    user_id      bigserial,
    email        varchar(50),
    user_name    varchar(30) not null,
    age          int,
    balance      int         not null default 0,
    sex          varchar(10),
    phone_number bigint,
    city         varchar(50),
    constraint fk_user_account_id_email
        foreign key (user_id) references account (account_id)
            on delete cascade
            on update cascade
);


-- drop table if exists orders;

create table orders
(
    order_id bigserial,
    date     timestamptz,
    price    integer check ( price > 0 ),
    user_id  bigint,
    user_name varchar(30) not null default '',
    constraint pk_order_id primary key (order_id),
    constraint fk_order_user_user_id
        foreign key (user_id) references account (account_id)
);

-- drop table if exists "order_information";



create table order_information
(
    order_id bigint  not null,
    title    varchar(200),
    image    varchar(200),
    price    integer check ( price > 0 ),
    quantity integer not null check ( quantity > 0 ),
    constraint fk_info_order_id
        foreign key (order_id) references orders (order_id)
            on delete cascade
            on update cascade
);



