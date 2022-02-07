use rentcar;

create table car(
	carCode varchar(30) not null primary key,
	carName varchar(30) not null,
	imgPath varchar(200),
	price integer not null,
	stock integer default 4,
	rented integer default 0
);

create table `user`(
	id varchar(30) not null primary key,
	pw varchar(30) not null,
	userName varchar(30) not null,
	age integer not null,
	rentCnt integer default 0,
	totalPrice integer default 0
);

create table board(
	num integer primary key auto_increment,
	title varchar(50) not null,
	content varchar(5000),
	userName varchar(30) not null,
	pw varchar(30) not null,
	`view` integer default 0,
	`like` integer default 0,
	`time` datetime not null default current_timestamp
);

insert board(title, content, userName, pw) values("그럴 듯한 이름", "그럴 듯한 내용", "aaaa", "aaaa");
insert board(title, content, userName, pw) values("안 그럴 듯한 이름", "그럴 듯한 내용", "aaaa", "aaaa");
insert board(title, content, userName, pw) values("대충 그럴 듯한 이름", "그럴 듯한 내용", "aaaa", "aaaa");
insert board(title, content, userName, pw) values("그냥 그럴 듯한 이름", "그럴 듯한 내용", "aaaa", "aaaa");
insert board(title, content, userName, pw) values("전혀 그럴 듯하지 않은 이름", "그럴 듯한 내용", "aaaa", "aaaa");
insert board(title, content, userName, pw, `view`, `like`) values("좋은 이름", "좋은 내용", "aaaa", "aaaa", 100, 99);

select* from board where num=2;

insert car(carCode, carName, imgPath, price) values("tng-1", "더 뉴 그랜저", "image/theNewGrandure.jpg", "200000");
insert car(carCode, carName, imgPath, price) values("K-5", "K5", "image/K5.png", "150000");
insert car(carCode, carName, imgPath, price) values("stf-1", "산타페", "image/산타페.png", "220000");
insert car(carCode, carName, imgPath, price) values("srt-1", "쏘렌토", "image/소렌토.png", "160000");
insert car(carCode, carName, imgPath, price) values("abt-1", "아반떼", "image/아반떼.png", "170000");
insert car(carCode, carName, imgPath, price) values("csp-1", "캐스퍼", "image/캐스퍼.png", "190000");
insert car(carCode, carName, imgPath, price) values("ts-1", "투싼", "image/투싼.png", "230000");
insert car(carCode, carName, imgPath, price) values("ps-1", "펠리세이드", "image/펠리세이드.png", "300000");

insert `user`(id, userName, pw, age) values("1234", "조성민", "1234", 26);
insert `user`(id, pw, userName, age) values("aaaa", "aaaa", "아아아아", 21);

Select* from car;
Select* from `user`;
Select* from board;

drop table `user`;
drop table car;
drop table board;