insert into categories(categories_name)
values ('Fantasy'),
       ('Self-help'),
       ('Non-fiction'),
       ('Crime novel'),
       ('Business & Money'),
       ('Health, Fitness & Dieting');


insert into book(Title, Image, Price, Author, Description, categories_id)
VALUES ('The Alchemist', 'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1654371463i/18144590.jpg', 2, 'Paulo Coelho', 'abc', 1),
       ('How to Win Friends and Influence People', 'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1442726934i/4865.jpg', 5, 'Dale Carnegie', 'abc', 2),

       ('Think and Grow Rich', 'https://m.media-amazon.com/images/I/718wzK6mymL.jpg', 10, 'Napoleon Hill', 'abc', 5),

       ('How to Stop Worrying and Start Living', 'https://cdn1.ozone.ru/s3/multimedia-h/6341055053.jpg', 7, 'Dale Carnegie', 'abc', 2),

       ('The Godfather', 'https://www.logobook.ru/make_nimage.php?uid=11572759', 4, 'Mario Puzo', 'abc', 4),

       ('The Magic of Thinking Big', 'https://www.moscowbooks.ru/image/book/682/orig/i682491.jpg?cu=20191204115528', 21, 'David J. Schwartz', 'abc', 2),

       ('The One Thing', 'https://m.media-amazon.com/images/I/31emFJveEWL.jpg', 23, 'Gary W. KellerJay Papasan', 'abc', 5),

       ('A New Earth: Awakening to Your Life’s Purpose', 'https://www.logobook.ru/make_nimage.php?uid=11460505', 30, 'Eckhart Tolle', 'abc', 6),

       ('The 7 Habits of Highly Effective People', 'https://m.media-amazon.com/images/I/81bgPVZOAUL.jpg', 19, 'Stephen R. Covey', 'abc', 2),

       ('Life''s Greatest Lessons', 'https://m.media-amazon.com/images/I/91cQlUph6DL.jpg', 4, 'Hal Urban', 'abc', 2),

       ('You Can Read Anyone', 'https://kbimages1-a.akamaihd.net/68f40486-3f17-457b-b735-43ef8ab1be54/1200/1200/False/you-can-read-anyone-never-be-fooled-lied-to-or-taken-advantage-of-again-2.jpg', 25, 'David J. Lieberman', 'abc', 2),

       ('The Power of Attitude Hardcover', 'https://m.media-amazon.com/images/I/51Q8jmTFY4L._AC_SY780_.jpg', 4, 'Mac Anderson', 'abc', 5);


update book
set quantity_sold = quantity_sold + 3
where book_id = 7;

select title, sum(quantity)
from order_information
group by title
order by title;