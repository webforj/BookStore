drop table if exists author cascade;
drop table if exists book_author cascade;
drop table if exists books cascade;
drop table if exists publishers cascade;
drop table if exists genres cascade;
drop table if exists reviews cascade;

create table author
(
    birth_date    timestamp(6) not null,
    death_date    timestamp(6),
    birth_name    varchar(255),
    full_name     varchar(255),
    id            varchar(255) not null,
    name          varchar(255) not null,
    nationality   varchar(255),
    notable_works text,
    profession    varchar(255),
    primary key (id)
);
create table book_author
(
    author_id varchar(255) not null,
    book_id   varchar(255) not null
);
create table books
(
    price          float(53)    not null,
    genre          varchar(255) not null,
    id             varchar(255) not null,
    isbn           varchar(255) not null,
    publisher_id   varchar(255),
    title          varchar(255) not null,
    year_published varchar(255),
    primary key (id)
);
create table genres
(
    id   varchar(255) not null,
    name varchar(255) not null,
    primary key (id)
);
create table publishers
(
    id   varchar(255) not null,
    name varchar(255) not null,
    primary key (id)
);
create table reviews
(
    rating  integer      not null,
    book_id varchar(255) not null,
    content varchar(255) not null,
    id      varchar(255) not null,
    primary key (id)
);
alter table if exists book_author
    add constraint FKbjqhp85wjv8vpr0beygh6jsgo foreign key (author_id) references author;
alter table if exists book_author
    add constraint FK91ierknt446aaqnjl4uxjyls3 foreign key (book_id) references books;
alter table if exists books
    add constraint FKayy5edfrqnegqj3882nce6qo8 foreign key (publisher_id) references publishers;
alter table if exists reviews
    add constraint FK6a9k6xvev80se5rreqvuqr7f9 foreign key (book_id) references books;
