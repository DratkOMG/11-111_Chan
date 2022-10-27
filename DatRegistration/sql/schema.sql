drop table if exists account;
drop table if exists user_profile;
create table account
(
    email         varchar(50) not null,
    password      varchar,
    year_of_birth int,
    constraint pk_email primary key (email)
);

create table user_profile
(
    id           bigserial,
    email        varchar(50) not null,
    user_name    varchar(20) not null,
    age          int,
    phone_number bigint,
    constraint fk_user_account
        foreign key (email) references account (email)
            on delete cascade
            on update cascade
);
insert into account(email, password, year_of_birth)
values ('dat@email.com', '123asd', 2000);
insert into account(email, password, year_of_birth)
values ('dat09@gmail.com', '123123123', 2003);
insert into user_profile(email, user_name, age, phone_number)
values ('dat@email.com', 'dattran', 20, 12342134);
update user_profile
set user_name = 'username1'
where user_name = 'dattran';


select email
from account
where email = 'dat@email.com';

select *
from user_profile
where email = 'dat09@gmail.com';


-- select *
-- from users
-- where email = 'dat@email.com'
--   and password = '123asd';
--
-- update users
-- set password = '1234qwer'
-- where email = '123@gmail.com'

