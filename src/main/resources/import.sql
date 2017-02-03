
insert into APP_Book(id, NAME, AUTHOR, isbn, STATUS) values(1, 'insertbook1', 'author1', 'isbn1', true);
insert into APP_Book(id, NAME, AUTHOR, isbn, STATUS) values(2, 'insertbook2', 'author2', 'isbn2', true);

insert into APP_USER(id, NAME, AGE, ADDRESS, SEX) values(1, 'insertuser1', 20, 'address1', 'male');
insert into APP_USER(id, NAME, AGE, ADDRESS, SEX) values(2, 'insertuser2', 25, 'address2', 'female');

insert into person_books(id, user_id, book_id) values(1, 1, 1);
insert into person_books(id, user_id, book_id) values(2, 1, 2);

