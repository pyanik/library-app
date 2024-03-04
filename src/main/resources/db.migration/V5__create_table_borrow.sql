create table borrow (
    borrow_id varchar(36) not null,
    book_id varchar(36) not null
    user_id varchar(36) not null
    date_of_borrow timestamp,
    date_of_return timestamp,
    borrow_status varchar(10),
    primary key (borrow_id),
    foreign key (book_id) references book (book_id),
    foreign key (user_id) references user (user_id));