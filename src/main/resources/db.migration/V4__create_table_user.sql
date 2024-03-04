create table user (
    user_id varchar(36) not null,
    first_name varchar(50),
    last_name varchar(50),
    email varchar(50),
    password varchar(50),
    user_role varchar(10),
    primary key (user_id))