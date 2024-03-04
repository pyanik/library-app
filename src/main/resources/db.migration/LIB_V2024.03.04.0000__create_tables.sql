create table library_app.author (
    author_id varchar(36) not null,
    first_name varchar(50),
    last_name varchar(50),
    biography varchar(250),
    primary key (author_id));

create table library_app.book (
    book_id varchar(36) not null,
    title varchar(50),
    description varchar(250),
    year integer
    author_id varchar(36) not null
    book_category_id varchar(36) not null
    primary key (book_id),
    foreign key (author_id) references author (author_id),
    foreign key (book_category_id) references conference (book_category_id));