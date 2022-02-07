-- 아마존에 연결되어있음 id : admin pw : 그거

use journey;

-- 10개 국 : 미국 / 영국 / 일본 / 태국 / 중국 / 필리핀 / 독일 / 이탈리아 / 그리스 / 인도

create table country(
	-- 번호, 국가 이름, 평점(리뷰에서 매긴 평균), 
	code integer primary key auto_increment,
    flag varchar(100) default "",
	countryName varchar(30) not null,
	score varchar(10) default "0",
    content varchar(5000) default "내용이 아직 없습니다."
);

create table review(
	code integer primary key auto_increment,
	countryName varchar(30) not null,
	id varchar(30) not null default "Guest",
	content varchar(5000),
	score integer default 0,
	`date` datetime not null default current_timestamp,
	pw varchar(30),		-- 게스트 상태에서 글을 쓸 경우 사용예정
    attachCnt integer default 0,
    userCode integer
);

create table reReview(
	code integer primary key auto_increment,
	id varchar(30) not null default "Guest",
	content varchar(5000),
	`date` datetime not null default current_timestamp,
	pw varchar(30),		-- 게스트 상태에서 글을 쓸 경우 사용예정
    attachCode integer not null,
    userCode integer
);


drop table country;
drop table review;
drop table reReview;

insert country(countryName) value("미국");
insert country(countryName) value("영국");
insert country(countryName) value("일본");
insert country(countryName) value("태국");
insert country(countryName) value("중국");
insert country(countryName) value("필리핀");
insert country(countryName) value("독일");
insert country(countryName) value("이탈리아");
insert country(countryName) value("그리스");
insert country(countryName) value("인도");

update country set flag="images/usflag.png" where code=1;
update country set flag="images/chflag.png" where code=5;
update country set flag="images/deflag.png" where code=7;
update country set flag="images/grflag.png" where code=9;
update country set flag="images/inflag.png" where code=10;
update country set flag="images/itflag.png" where code=8;
update country set flag="images/jpflag.png" where code=3;
update country set flag="images/phflag.png" where code=6;
update country set flag="images/thflag.png" where code=4;
update country set flag="images/ukflag.png" where code=2;

update country set content="미국에 대한 리뷰 게시판입니다.<br>대표적인 관광지로 타임즈 스퀘어, 자유의 여신상, 금문교(골든게이트 브릿지), 나이아가라 폭포, 월트 디즈니 월드 리조트 등이 있습니다." where code=1;
update country set content="영국에 대한 리뷰 게시판입니다.<br>대표적인 관광지로 타워 브릿지, 대영 박물관, 스톤헨지, 빅 벤(엘리자베스 타워), 버킹엄 궁전 등이 있습니다." where code=2;
update country set content="일본에 대한 리뷰 게시판입니다.<br>대표적인 관광지로 도쿄 타워, 센소지, 유니버설 스튜디오 재팬, 오사카 성 등이 있습니다." where code=3;
update country set content="태국에 대한 리뷰 게시판입니다.<br>대표적인 관광지로 태국 왕궁, 카오산 로드, 짜오프라야 강, 왓 포, 왓 아룬 등이 있습니다." where code=4;
update country set content="중국에 대한 리뷰 게시판입니다.<br>대표적인 관광지로 만리장성, 자금성, 진시황릉 등이 있습니다." where code=5;
update country set content="필리핀에 대한 리뷰 게시판입니다.<br>대표적인 관광지로 초콜릿 힐스, 리잘 파크, 비간 시티, 엘 니도 등이 있습니다." where code=6;
update country set content="독일에 대한 리뷰 게시판입니다.<br>대표적인 관광지로 브란덴부르크 문, 노이슈반슈타인 성, 쾰른 대성당, 베를린 장벽 등이 있습니다." where code=7;
update country set content="이탈리아에 대한 리뷰 게시판입니다.<br>대표적인 관광지로 콜로세움, 판테온, 트레비 분수, 피렌체 대성당 등이 있습니다." where code=8;
update country set content="그리스에 대한 리뷰 게시판입니다.<br>대표적인 관광지로 아크로폴리스, 파르테논 신전, 나바지오, 산토리니 마을 등이 있습니다." where code=9;
update country set content="인도에 대한 리뷰 게시판입니다.<br>대표적인 관광지로 타지마할, 레드 포르, 짐 코벳 국립공원, 엠버 팰리스 등이 있습니다." where code=10;


insert review(countryName, id, content, score, attachCnt) values("미국", "으악", "으아악", 6, 2);
insert review(countryName, content, score, pw) values("영국", "으악!", 8, "1234");
insert review(countryName, content, score, pw, attachCnt) values("미국","정말 멋있었다", 9, "1234", 1);
insert review(countryName, content, score, pw) values("영국","정말 멋있었다", 8, "1234");
insert review(countryName, content, score, pw) values("일본","정말 멋있었다", 7, "1234");
insert review(countryName, content, score, pw) values("태국","정말 멋있었다", 8, "1234");
insert review(countryName, id, content, score) values("중국", "A회원", "정말 멋있었다", 9);
insert review(countryName, content, score, pw) values("중국","정말 멋있었다", 4, "1234");
insert review(countryName, content, score, pw) values("독일","정말 멋있었다", 3, "1234");
insert review(countryName, content, score, pw) values("이탈리아","정말 멋있었다", 6, "1234");
insert review(countryName, content, score, pw) values("그리스","정말 멋있었다", 7, "1234");
insert review(countryName, content, score, pw) values("인도","정말 멋있었다", 10, "1234");

insert review(countryName, id, content, score) values("미국", "으악3", "으아악으아악", 6);
insert review(countryName, id, content, score) values("미국", "으악4", "으아악333", 9);
insert review(countryName, id, content, score) values("미국", "으악5", "으아악44444", 8);
insert review(countryName, id, content, score) values("미국", "으악6", "으아악55555", 7);
insert review(countryName, id, content, score) values("미국", "으악8", "으아악666", 6);
insert review(countryName, id, content, score) values("미국", "으악7", "으아악777", 5);
insert review(countryName, id, content, score) values("미국", "으악9", "으아악888", 10);
insert review(countryName, id, content, score) values("미국", "으악0", "으아악999", 9);
insert review(countryName, id, content, score) values("미국", "으악-", "으아악111123", 2);
insert review(countryName, id, content, score) values("미국", "으악=", "으아악32131", 6);
insert review(countryName, id, content, score) values("미국", "으악11", "으아악32323", 7);
insert review(countryName, id, content, score) values("미국", "으악12", "으아악43434", 7);

insert review(countryName, content, pw, score) values("미국", "오오오오오오오", "1234", 10);
insert review(countryName, content, pw, score) values("미국", "오오오오오오오", "1234", 10);
insert review(countryName, content, pw, score) values("미국", "오오오오오오오", "1234", 10);
insert review(countryName, content, pw, score) values("미국", "오오오오오오오", "1234", 10);
insert review(countryName, content, pw, score) values("미국", "오오오오오오오", "1234", 10);
insert review(countryName, content, pw, score) values("미국", "오오오오오오오", "1234", 10);
insert review(countryName, content, pw, score) values("미국", "오오오오오오오", "1234", 10);
insert review(countryName, content, pw, score) values("미국", "오오오오오오오", "1234", 10);
insert review(countryName, content, pw, score) values("미국", "오오오오오오오", "1234", 10);
insert review(countryName, content, pw, score) values("미국", "오오오오오오오", "1234", 10);

insert reReview(id, content, attachCode) values("대댓글다는 사람", "ㄹㅇㅋㅋ", 1);
insert reReview(id, content, attachCode) values("대댓글다는 사람2", "ㄹㅇㅋㅋ2", 1);
insert reReview(id, content, attachCode) values("대댓글다는 사람", "ㄹㅇㅋㅋ", 3);
insert reReview(id, content, pw, attachCode) values("Guest", "omg", "1234", 3);
insert reReview(id, content, pw, attachCode) values("Guest", "ooooommmmmgggggg", "1234", 3);
insert reReview(id, content, pw, attachCode) values("Guest", "오마이갓 와우", "1234", 1);
insert reReview(id, content, pw, attachCode) values("Guest", "오마이갓 omg", "1234", 1);
insert reReview(id, content, pw, attachCode) values("Guest", "오 쉿", "1234", 1);
insert reReview(id, content, pw, attachCode) values("Guest", "오메", "1234", 1);


select * from review where countryName="미국";
select * from reReview where attachCode=1;
delete from review where code=18;
update review set pw="1234" where code=3;
delete from reReview where attachCode=3;
update review set attachCnt=3 where code=3;
update reReview set attachCode=28 where code=5;
select * from reReview where attachCode=28;

update reReview set pw=1234, content="왜 안되는건가" where code=10;

select * from review where id = "asdf";

select * from country;
select * from review;
select * from reReview;
select * from users;
select * from blog;