insert into categories(categories_name)
values ('Literature & Fiction');

insert into book(title, image, price, author, description, categories_id)
values ('Da nhat kiem tien', 'https://static.kinhtedothi.vn/w960/images/upload/2021/12/24/sach-huan-1.jpg',
        10, 'Huan hoa hong', 'co lam thi moi co an', 5);

alter table book
alter column title
type varchar;