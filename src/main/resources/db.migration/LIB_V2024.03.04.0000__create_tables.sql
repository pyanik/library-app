CREATE TABLE library_app.author (
    author_id UUID NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    biography VARCHAR(500),
    PRIMARY KEY (author_id)
);

CREATE TABLE library_app.book_category (
    book_category_id UUID NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(500),
    PRIMARY KEY (book_category_id)
);

CREATE TABLE library_app.book (
    book_id UUID NOT NULL,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(500),
    year INTEGER,
    author_id UUID NOT NULL,
    book_category_id UUID NOT NULL,
    PRIMARY KEY (book_id),
    FOREIGN KEY (author_id) REFERENCES author (author_id),
    FOREIGN KEY (book_category_id) REFERENCES book_category (book_category_id)
);

CREATE TABLE library_app."user" (
    user_id UUID NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    user_role VARCHAR(10) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE library_app.borrow (
    borrow_id UUID NOT NULL,
    book_id UUID NOT NULL,
    user_id UUID NOT NULL,
    date_of_borrow TIMESTAMP NOT NULL,
    date_of_return TIMESTAMP,
    borrow_status VARCHAR(10) NOT NULL,
    PRIMARY KEY (borrow_id),
    FOREIGN key (book_id) REFERENCES book (book_id),
    FOREIGN key (user_id) REFERENCES "user" (user_id)
);