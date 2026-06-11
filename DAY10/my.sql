sudo mysql -u root -p ""
create database nitte2026
drop database nitte2026


use nitte2026
create table creditcard(
    card_no bigint not null primary key,
    card_holder varchar(255) not null,
    card_limit int not null,
    card_status varchar(255) default 'active'
);

create table merchant(
    merchant_id int not null primary key,
    merchant_name varchar(255) not null,
    merchant_acct bigint not null
);

create table transactions(
    t_id bigint not null primary key auto_increment,
    t_date date not null,
    t_amount double not null,
    t_by bigint not null,
    t_to int not null,
    foreign key(t_by) references creditcard(card_no),
    foreign key(t_to) references merchant(merchant_id)

);

insert into creditcard values(123,'Razak Mohamed',120000,'active');
insert into creditcard values(130,'Prem Kumar',80000,'active');
insert into creditcard values(140,'Patrick',150000,'closed');

insert into merchant values(1234,'Arun',12342);
insert into merchant values(,'Arun',12342);
insert into merchant values(1234,'Arun',12342);


describe transactions;
select * from creditcard
select * from merchant
select * from transactions

select * from 
transactions inner join merchant 
on merchant.merchant_id=transactions.t_to