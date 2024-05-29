ALTER TABLE library_app.author
ADD business_object_version INTEGER DEFAULT 0;

ALTER TABLE library_app.book_category
ADD business_object_version INTEGER DEFAULT 0;

ALTER TABLE library_app.book
ADD business_object_version INTEGER DEFAULT 0;

ALTER TABLE library_app.borrow
ADD business_object_version INTEGER DEFAULT 0;

ALTER TABLE library_app.user
ADD business_object_version INTEGER DEFAULT 0;