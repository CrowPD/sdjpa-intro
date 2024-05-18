drop table if exists book;
create table book
(
    id        bigint not null,
    isbn      varchar(255),
    publisher varchar(255),
    title     varchar(255),
    primary key (id)
) engine = InnoDB;

drop table if exists book_seq;
create table book_seq
(
    next_val bigint
) engine = InnoDB;

insert into book_seq
values (1);