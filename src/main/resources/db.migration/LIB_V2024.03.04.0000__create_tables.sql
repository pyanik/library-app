CREATE TABLE library_app.author (
    author_id VARCHAR(36) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    biography VARCHAR(250),
    PRIMARY KEY (author_id)
);

CREATE TABLE library_app.book_category (
    book_category_id VARCHAR(36) NOT NULL,
    name VARCHAR(50),
    description VARCHAR(250),
    PRIMARY KEY (book_category_id)
);

CREATE TABLE library_app.book (
    book_id VARCHAR(36) NOT NULL,
    title VARCHAR(50),
    description VARCHAR(250),
    year INTEGER,
    author_id VARCHAR(36) NOT NULL,
    book_category_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (book_id),
    FOREIGN KEY (author_id) REFERENCES author (author_id),
    FOREIGN KEY (book_category_id) REFERENCES book_category (book_category_id)
);

CREATE TABLE library_app."user" (
    user_id VARCHAR(36) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(50),
    user_role VARCHAR(10),
    PRIMARY KEY (user_id)
);

CREATE TABLE library_app.borrow (
    borrow_id VARCHAR(36) NOT NULL,
    book_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    date_of_borrow TIMESTAMP,
    date_of_return TIMESTAMP,
    borrow_status VARCHAR(10),
    PRIMARY KEY (borrow_id),
    FOREIGN key (book_id) REFERENCES book (book_id),
    FOREIGN key (user_id) REFERENCES "user" (user_id)
);