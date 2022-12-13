show databases;
create database messenger_db;
use messenger_db;

drop table `users`;
create table `users`(
user_name varchar(30) not null,
id varchar(50) not null,
pw varchar(100) not null,
image varchar(1000),
description varchar(2000),
birthday int not null,
mail varchar(100) not null,
phon_number int not null,
status varchar(50),
primary key(id,pw,birthday,mail,phon_number),
UNIQUE KEY uk_name (id,pw,birthday,mail,phon_number)
);
desc users;


drop table `follow`;
create table `follow`(
src_id varchar(50) not null,
tgt_id varchar(50) not null
);
desc follow;


create table  `user_board`(
id varchar(50) not null,
board_id varchar(50) not null,
primary key (id,board_id),
UNIQUE KEY (board_id) 
);
desc user_board;

CREATE TABLE `BOARD`(
   NUM int , 
   TITLE varchar(50), 
   CONTENT varchar(2000), 
   boardId varchar(50), 
   REGDATE DATE,
    
    foreign key (boardId) references user_board(board_id)
   );
desc BOARD;


select * from users;
insert into users values
('name1', 'number1_id', 'number1_pw', "/Users/ajin/Desktop/2022/2022 2학기/데이터베이스 및 실습/Workshop/UserMainPage/profilepic/pic1.JPG", "aaa", 20020101,'number1_id@naver.com',01011111111, NULL), 
('name2', 'number2_id', 'number2_pw', "/Users/ajin/Desktop/2022/2022 2학기/데이터베이스 및 실습/Workshop/UserMainPage/profilepic/pic1.JPG", "aaa", 20020102,'number2_id@naver.com',01022222222, NULL),
('name3', 'number3_id', 'number3_pw', "/Users/ajin/Desktop/2022/2022 2학기/데이터베이스 및 실습/Workshop/UserMainPage/profilepic/pic1.JPG", "aaa", 20020103,'number3_id@naver.com',01033333333, NULL),
('name4', 'number4_id', 'number4_pw', "/Users/ajin/Desktop/2022/2022 2학기/데이터베이스 및 실습/Workshop/UserMainPage/profilepic/pic1.JPG", "aaa", 20020104,'number4_id@naver.com',01044444444, NULL),
('name5', 'number5_id', 'number5_pw', "/Users/ajin/Desktop/2022/2022 2학기/데이터베이스 및 실습/Workshop/UserMainPage/profilepic/pic1.JPG", "aaa", 20020105,'number5_id@naver.com',01055555555, NULL),
('name6', 'number6_id', 'number6_pw', "/Users/ajin/Desktop/2022/2022 2학기/데이터베이스 및 실습/Workshop/UserMainPage/profilepic/pic1.JPG", "aaa", 20020106,'number6_id@naver.com',01066666666, NULL);
select * from users;


# Query 
select * from follow;


# 1) 나를 follow하는 사람 리스트.
select * from follow where src_id = 'number1_id';
create view my_follower as select * from follow where src_id = 'number1_id';
select * from my_follower;

select count(tgt_id) from follow where src_id = 'number1_id';

# 나를 팔로우 하는 사람들 글 불러오기 . 
select * from user_board, my_follower
where user_board.id = my_follower.src_id;

# 2) 내가 follow 하는 사람 리스트.
select * from follow where tgt_id = 'number1_id';



# 3) 내가 follow 하는 사람들의 글을 날짜 순으로 불러오기.
create view following_board as select board_id from user_board natural join user_follower;

select * from follow;

# 내가 follow 하는 사람들의 글을 날짜 순으로 불러오기. ## 결과. 
select * from article natural join follow ORDER BY time_record desc;
select * from article natural join follow ORDER BY time_record asc;

select * from users;

update users 
set user_name = 'guest', 
id = 'guest_000', 
description = 'bbb',
 mail = 'guest@gmail.com'
 where id = 'number6_id';
 
 

select * from follow;
select tgt_id from follow where src_id = 'admin_000';
select src_id from follow where tgt_id = 'admin_000';

delete from follow where src_id = 'admin_000' and tgt_id='number1_id';


insert into user_board values
('number1_id','broad1_id'), 
('number2_id','broad2_id'),
('number3_id','broad3_id'),
('number4_id','broad4_id'),
('number5_id','broad5_id'),
('number6_id','broad6_id');

insert into user_board values
('admin_000','broad_admin');

    insert into board values
    (1,'yes','yes message','broad3_id','1000-01-01'),
    (2,'no','no message','broad6_id','1000-01-01'),
   (3,'hi','hi message','broad1_id','1000-01-01'),
    (4,'hi','hi message','broad2_id','1000-01-01'),
    (5,'hi','hi message','broad3_id','1000-01-01'),
    (6,'yes','yes message','broad3_id','1000-01-01'),
    (7,'yes','yes message','broad3_id','1000-01-01'),
    (8,'yes','yes message','broad6_id','1000-01-01'),
    (9,'no','no message','broad3_id','1000-01-01'),
    (10,'yes','yes message','broad3_id','1000-01-01'),
    (21,'yes','yes message','broad3_id','1000-01-01'),
    (22,'no','no message','broad6_id','1000-01-01'),
   (23,'hi','hi message','broad1_id','1000-01-01'),
    (24,'hi','hi message','broad2_id','1000-01-01'),
    (25,'hi','hi message','broad3_id','1000-01-01'),
    (26,'yes','yes message','broad3_id','1000-01-01'),
    (27,'yes','yes message','broad3_id','1000-01-01'),
    (28,'yes','yes message','broad6_id','1000-01-01'),
    (29,'no','no message','broad3_id','1000-01-01'),
    (30,'yes','yes message','broad3_id','1000-01-01'),
    (31,'yes','yes message','broad3_id','1000-01-01'),
    (32,'no','no message','broad6_id','1000-01-01'),
   (33,'hi','hi message','broad1_id','1000-01-01'),
    (34,'hi','hi message','broad2_id','1000-01-01'),
    (35,'hi','hi message','broad3_id','1000-01-01'),
    (36,'yes','yes message','broad3_id','1000-01-01'),
    (37,'yes','yes message','broad3_id','1000-01-01'),
    (38,'yes','yes message','broad6_id','1000-01-01'),
   (39,'no','no message','broad3_id','1000-01-01'),
    (40,'yes','yes message','broad3_id','1000-01-01'),
    (41,'yes','yes message','broad3_id','1000-01-01'),
    (42,'no','no message','broad6_id','1000-01-01'),
   (43,'hi','hi message','broad1_id','1000-01-01'),
    (44,'hi','hi message','broad2_id','1000-01-01'),
    (45,'hi','hi message','broad3_id','1000-01-01'),
    (46,'yes','yes message','broad3_id','1000-01-01'),
    (47,'yes','yes message','broad3_id','1000-01-01'),
    (48,'yes','yes message','broad6_id','1000-01-01'),
    (49,'no','no message','broad3_id','1000-01-01'),
    (50,'yes','yes message','broad3_id','1000-01-01'),
    (51,'yes','yes message','broad3_id','1000-01-01'),
    (52,'no','no message','broad6_id','1000-01-01'),
   (53,'hi','hi message','broad1_id','1000-01-01'),
    (54,'hi','hi message','broad2_id','1000-01-01'),
    (55,'hi','hi message','broad3_id','1000-01-01'),
    (56,'yes','yes message','broad3_id','1000-01-01'),
    (57,'yes','yes message','broad3_id','1000-01-01'),
    (58,'yes','yes message','broad6_id','1000-01-01');
    select * from board;
    
    
    insert into users values
('admin', 'admin_000', 'admin0_pw', "/Users/ajin/Desktop/2022/2022 2학기/데이터베이스 및 실습/Workshop/UserMainPage/profilepic/pic1.JPG", "ex", 20020101,'admin0@gmail.com',0101125146, NULL), 
('admin', 'admin_001', 'admin1_pw', "/Users/ajin/Desktop/2022/2022 2학기/데이터베이스 및 실습/Workshop/UserMainPage/profilepic/pic1.JPG", "ex", 20020101,'admin1@gmail.com',0101134216, NULL), 
('admin', 'admin_002', 'admin2_pw', "/Users/ajin/Desktop/2022/2022 2학기/데이터베이스 및 실습/Workshop/UserMainPage/profilepic/pic1.JPG", "ex", 20020208,'admin2@gmail.com',0104691246, NULL), 
('admin', 'admin_003', 'admin3_pw', "/Users/ajin/Desktop/2022/2022 2학기/데이터베이스 및 실습/Workshop/UserMainPage/profilepic/pic1.JPG", "ex", 20020101,'admin3@gmail.com',0101531234, NULL);

select * from users;

delete from follow;
select * from follow where src_id = 'admin_000';
select * from follow where tgt_id = 'admin_000';


insert into follow values
('admin_000', 'admin_001'),
('admin_000', 'admin_002'),
('admin_001', 'admin_003'),
('admin_001', 'number2_id'),
('admin_001', 'number1_id'),
('admin_001', 'number4_id'),
('number1_id', 'number666_id'), 
('number2_id', 'number777_id'),
('number3_id', 'number888_id');

insert into follow values
('admin_000', 'number666_id'),
('admin_000', 'number777_id'),
('admin_000', 'number888_id'),
('admin_000', 'number1_id'),
('admin_000', 'number4_id');

insert into follow values
('admin_001', 'admin_000'),
('admin_002', 'admin_000'),
('admin_003', 'admin_000'),
('number1_id', 'admin_000'),
('number666_id', 'admin_000'),
('number2_id', 'admin_000'),
('number3_id', 'admin_000');
select * from users where user_name like 'name%';
                    