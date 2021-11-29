

---- 연습문제-----
------------------- 1. member. 테이블 생성----------------------------


create table  MEMBERS (
m_code number,   --순번
m_id varchar(20) constraint m_id_pk primary key, -- 회원아이디 
m_pwd varchar(20) constraint m_pwd_nn not null, -- 회원 비밀번호
m_name varchar(10) constraint m_name_nn not null, -- 회원이름
m_addr varchar(20), -- 회원 거주지
m_contact varchar(20), -- 연락처
m_ad_accept varchar(5) constraint
m_ad_accept_ck check (m_ad_accept in ('y','n')) not null, -- 광고 수신여부
m_reg_date timestamp default sysdate not null -- 가입일
);

alter table cafe_menu modify is_ice constraint is_ice_nn not null;
alter table members drop constraint m_id_nn;
alter table members modify constraint  m_code_nn;
alter table members modify m_id constraint m_id_pk primary key; 

-- not null은 modify 를 쓰고
-- 다른 constraint는 drop constraint

select * from all_constraints;
drop table members;

drop 

---------- member table 시퀀스 생성

--create sequence cafe_item_id_seq
--start with 1004 increment by 1 nomaxvalue nocache; 


create sequence members_m_code_seq
start with 1 increment by 1 nomaxvalue nocache;

-- 시퀀스 조회
select * from user_sequences;
--시퀀스 삭제
drop sequence products_p_id_seq;


--행 삭제

delete from members where m_code=4;

-- 행입력

insert into members (m_code, m_id, m_pwd, m_name, m_contact, m_ad_accept, m_reg_date) 
 values(
members_m_code_seq.nextval, 'cjfn', 'pass111', 'jack', '010-1234-1234', 'y', sysdate);

insert into members 
 values(
members_m_code_seq.nextval, 'dudgml', 'pass222', 'jane', '서울', '010-4321-4321', 'n', sysdate);

insert into members 
 values(
members_m_code_seq.nextval, 'rlfehd', 'pass333', 'tom', '경기', '','n', sysdate);


select* from members;

------------------- 2. products. 테이블 생성----------------------------


create table products (
p_id number constraint p_id_pk primary key,                -- 상품 고유 코드
p_name varchar(30) constraint p_name_nn not null, -- 상품명
p_price number               -- 상품가격
);

create sequence products_p_id_seq
start with 1 increment by 1 nomaxvalue nocache;

drop sequence products_m_id_seq;

select * from products;
select*from all_sequences;


insert into products values (
products_p_id_seq.nextval, 'TV', 1000000);

insert into products values(
products_p_id_seq.nextval,'Speaker', 300000);


------------------ 3.  Purchase_detail table 생성 --------


create table purchase_details(
    d_id number constraint d_id_pk primary key,
    m_id constraint m_id_fk references members(m_id) on delete cascade,
    p_id constraint p_id_fk references products(p_id) on delete set null,
    d_date date default sysdate
);


-- 외래키 삭제
drop 




------------------4. members table에 insert column


insert into members 
 values(
members_m_code_seq.nextval, 'rlfehd', 'pass333', 'tom', '경기', '','n', sysdate);

select * from user_sequences;
select * from members;

--------------------5. product table에 다음 데이터를 입력.


insert into products values(
products_p_id_seq.nextval, 'Notebook' ,1500000);

select*from products;


------------------ 5.  Purchase_detail table 생성 --------


create table purchase_details(
    d_id number constraint d_id_pk primary key,
    m_id constraint m_id_fk references members(m_id) on delete cascade,
    p_id constraint p_id_fk references products(p_id) on delete set null,
    d_date date default sysdate
);
--
--create sequence products_p_id_seq
--start with 1 increment by 1 nomaxvalue nocache;

select * from all_constraints;
create sequence d_id_seq start with 1 increment by 1 nomaxvalue nocache;
select * from sequences;
select * from purchase_details;





------------------- 6 Purchase_detail table 에 임의의 거래 데이터 2개 insert


-- 데이터 3 : 시퀀스값 / rlfehd / 1002 / 19/12/24
select *  from members;
select * from products;
select * from purchase_details;

insert into purchase_details values (d_id_seq.nextval, 'cjfn',1, sysdate);
insert into purchase_details values(d_id_seq.nextval,'dudgml', 2,sysdate);
insert into purchase_details values(d_id_seq.nextval,'rlfehd',3 ,(sysdate-366));
delete from purchase_details where d_id=17;
-- 모든 컨스트레인 조회
SELECT * FROM    ALL_CONSTRAINTS;
select table_name from user_tables;





---  7. --# 구매기록을 다음과 같이 출력해보세요.
--거래번호 / 구매자이름 / 상품이름 / 가격( ~~~,~~~,~~~형태로 ) / 거래날짜

select 
    d_id"거래번호",
    m_name"구매자 이름",
    p_name"상품 이름",
    to_char(p_price, 'l999,999,999')
from members m, products p, purchase_details d
    where m.m_id=d.m_id and
                d.p_id= p.p_id;
                
-- 08.  
--# test02 계정을 생성하고 이 계정으로 접속하여 회원 목록을 조회할 수 있게 만들고, 실제로 조회해보세요


create user tester02 identified by tester02;

grant resource, connect to tester02;

grant select on tester.members to tester02;



select * from tester.members;
                                                                  
                                                                  
                                                                  
                                                                  
                                                                  
