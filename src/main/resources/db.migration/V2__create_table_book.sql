create table book (
    book_id varchar(36) not null,
    title varchar(50),
    description varchar(250),
    year integer
    author_id varchar(36) not null
    book_category_id varchar(36) not null
    primary key (book_id),
    foreign key (author_id) references author (author_id),
    foreign key (book_category_id) references conference (book_category_id));