


-- union은  join문처럼 서로 다른 테이블의 데이터를 집합시키며, data를 붙여넣기 한다는 개념에 더 가깝다.

-- DDL - data definition Language


-- 1. creat, 생성

-- 문법 : create table() (table 이름)
-- 소괄호 내에는 table명(clumn명)과 type 지정
-- ex) dept_code char, number

creat table tableA( -- culmn 명을 지정해준다.

col numer            -- column의 명칭은 col이며, 타입은 number임을 지정하는 것이다.

)

creat tableB(
dept_code num
)


-- 2. insert - 데이터 삽입

-- 문법 : inser into culmn명 value();  
-- value에 생성 시기에 지정한 type을 table에 삽입한다.

inser into tableA value(1);
inser into tableA value(2);


-- 3. union

select * from tableA union
select * from tableB;


--> tableA와 tableB의  table의 data는 독자적으로 보관하고 있으며,
--  union을 통해서 가상의 테이블을 만들어 데이터를 join 시킨다.
--  상기의 query를 출력하면, default로 중복된 데이터들을 제거하고, 
--  중복되지 않은 값들을 전부 table에 옮긴다.
--  union을 시킬 data를 특정할 수 있으며, 또는 *로 전부를 union시킬 수도 있다.

-- 4. union ALL

-- 기본 union은 두개의 테이블 결합에서 중복되는 값들을 가상 테이블에 return한다.
-- 하지만 union ALL은 tableA와 tableB가 가진 중복되는 data까지 모두 반환한다.

select * from tableA
union all
select * from tableB;



-- 5. intersect : 두개의 table 결합에서 중복되는 데이터만 반환


creat table tableA(
dept_id number
)

insert into tableA value(1);
insert into tableA value(2);
insert into tableA value(3);

creat table tableB(
dept_code number
)

insert into tableB value(1);
insert into tableB value(2);
insert into tableB value(3);


select * from tableA;
union ALL select * from tableA;

-- 출력되는 테이블의 이름은 유니온을 시작하는 대상 COLUMN이다.
-- 상기의 QUERY문에서는 tableA로 가상의 테이블을 만들어서 데이터를 집합시킨다.


-- 


-- 6. minus
-- 차집합이며, 공통된 record(행)를 모두, 제외하며 출력을 한다.

select * from tablea
minus select* from tableb;

-- 두 테이블 모두 1,2,3의 숫자를 가지고 있기에
-- 출력된 결과물은 dept_code란 column만 있을 뿐, data는 출력되지 않을 것이다.


----- 7. union 사용시 주의 사항-------------


-- 1. union을 실행하기 위해서는 두개의 table의 column 갯수가 같아야 한다.
-- 2. union의 대상이 되는 table의 type이 같아야 한다.

select * from tableA
union select * from department;

--> tableA의 type은 number이며, department의 type은 char이기에 union되지 않는다.
--> 자료형의 충돌이 일어날 경우에는, expression must have same 
--                                 datatype as corresponding expression.

3. 3개이상의 union 때에도 각각의 table 별로 column의 갯수가 같아야 한다.

select emp_id, emp_name, emp_no from employee
union select * from department.


4. 3개 이상의 table의 union에도 반드시 date 타입이 같아야 한다.

select emp_id, emp_name, salary from employee
union 
select * from department;


--> join은 table(객체) 내의 data에서 column을 기준으로 join을 한다.
--   union은 서로 다른 table의 객체를 조합해야 할 때 사용한다.

