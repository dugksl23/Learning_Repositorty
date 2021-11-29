


 
 -------------------------------------------------------------------------------
 -------------------------------------------------------------------------------
 
 
 -- 데이터 타입(Data Type)의 종류 -- 
 -- 자료형(data type)이란 시스템과 프로그래밍 언어에서 실수, 소수, 자료형 등의 데이터를 식별하는 타입입니다.
  
  -- 문자형--
  -- 1. char(n) :  고정 길이 데이터타입(최대 2000byte) - 지정된 길이 보다 짧은 데이터가 입력될 시 나머지 공간은 공백으로 채워진다.
  -- 2. varchar2(n) : 가변 길이 데이터 타입 (최대 4000byte) - 지정된 길이보다 짧은 데이터가 입력될 시 나머지 공간은 공백으로 채워진다.
  -- 3. nvarchar2(n) : 가변 길이 유니코드 데이터타입(최대 4000byte)
  -- 4. long - 가변 길이 데이터타입 (최대 2Gbyte)
  -- 5. clob - 대용량 텍스트 데이터 타입(최대 4Gbyte)
  -- 6. NCLOB - 대용량 텍스트 유니코드 데이터타입(최대 4Gbyte)
  
  
  -- 숫자형 --
  
  --1. BINARY_FLOAT : 부동 소수형 데이터 타입(4 byte) - 32 bit 부동 소수
  --2. BINARY_DOUBLE : 부동 소수형 데이터타입 (4 byte) - 64 bit
  --3. NUMBER(P,S) : P,S로 표현 숫자 데이터타입 [:1~38, s: 884~127P(precision):유효 자리수, s(scale) : 소수점 유효자리.
  
  
  --날짜형 데이터 타입 --
  
  -- 1. Date : 고정 길이 날짜
  -- 2. Interval-day : 날짜 및 시간 및 (요일, 시, 분, 초) 형태의 기간 표현 데이터타입.
  -- 3. timestamp : 밀리초(ms)까지 표현데이터 타입
  -- 4. timestamp-with time zone : 날짜 및 시간대 형태의 데이터타입
  -- 5. timestamp_with local time zone - 저장시 데이터 베이스 시간대를 준수, 조회시 조회하는 클라이언트 시간 표현 데이터타입.
  
 
 -------------------------------------------------------------------------------
 -----------------------[ 문법 ]------------------------------------------------
 
 
 
 -- 1. [select A from b]
 
 select table_name from user_table;

 -- *select : 조회 명령어
 --    1. 일종의 for문으로 한줄 한줄 출력하는 방식이다.
 -- *dictionaly : 사용자의 명령에 따라, 만들어지는 data를 저장하는 table.
 -- *table_name : table 명을 의미
 --   1. user_tables에서 table명을 조회하라/
 
-- 2. [ desc(describe)]
 
 desc empployee;
 
 -- employee table의 header와 자료형을 쭉 리스트업 해준다. 
 -- 해당 테이블의 정보를 모두 출력한다.
 
 
select emp_id, emp_name, emp_no from employee;
 
-- employee table의 id와 emp_name과  emp_no를 조회하라.
--***주의 사항 : column은 대소문자를 가리지 않지만, reteral value(실제값은) 대소문자를 엄격히 구분해야 한다.
  
 -- 3. [select * from b]
 select * from employee;
 -- employee table에서 *를 하면, 모두 출력한다는 뜻.
 -- query문 마다 세미콜론을 찍는다.
 -- **현업에서는 "query를 쏜다 or 날린다" 라고 쓴다.
 
 
 
 -- 4. [select A from b where emp_name=204] 
 
 select * from employee where emp_id=204;
-- where : if와 갖은 기능을 가진다.
-- 만약, emp_id가 204와 같다면, employee table에서 *(전부) 출력하시오
-- column은 double quotation
-- reteral 문자는 single quotation
-- 숫자는 no quotation

-- ===> where절에서 column을 조건문으로 지정해줌으로써
--     해당 where문이 true라면 특정 row만(select된 데이터만을( 출력할 수 있다.

-- 5. [ as ]

select emp_name as "직원명", salary as "연봉" from employee;

-- column명을 as "~~"를 사용하여 user가 원하는 이름으로 수정이 가능하다.
-- 이때 as는 생략가능, 단 double quotation ("~")dmf tkdydgksms dldbsms data(즉 retearal 값)이 아니라
-- column 값(table header)값이기 떄문이다.
-- 해당 기능은 ailas 로서 double quotation으로서 column에 명칭을 부여할 수 있다.

-- 6. [*,+,-/] : operation part
 
 
 select emp_name"직원명", salary*12||'원'"연봉" from employee;
 
 
 -- 7. [ || ] : column에 reteral 값 부여하기.
 
select emp_name"직원명", salary*12 || '원'"연봉" from employee; 


-- || 는 뒤에 따라오는 data를 (문자열의 경우에는 ''(single quotation) 뒤에 붙여줄 수 있다.
-- ===> 데이터 꾸밈새의 역할


-- 8. sysdate = 날짜 자료형, date type, 현재 시간을 나타냄.

select emp_name"직원명", hire_date"입사년도" from employee where floor(sysdate-hire_date);

-- **floor() 함수는 괄호안에서 연산된 값 또는 실수의 소수점을 버리고 출력한다.
--  날짜끼리 연산 가능.  
--   = column끼리 연산 가능
-- 현재시간에 입사년도를 빼면 근속일수를 소수점을 빼고 계산하기 floor() 함수
-- floor 함수, 소수점을 버려주는 함수.

-- 9. [ dual ] : oracle에서 test용으로 만들어놓은 table

 select sysdate"현재시간" from dual;
 select *from dual;
 
 -- dummy로서 test 파일이다.
 
 
 
 
 -- 10. [distinct] : 중복제거
 
 
 select distinct job_code from employee;
 
 -- distinct 뒤에 나온 column은 data의 중복을 막아준다.(중복 제거)
 -- 단, 뒤에 다른 column을 넣게 된다면, 중복방지가 사라짐.
 -- distinct는 무조건 column입력부분 제일 맨 앞에 와야한다.

 
 -- 11. [between A and B] - 숫자 및 날짜(date) 대상으로만 하는 a부터 b까지의 범위
 -- 급여가 300만원 or 400만원 이상 출력하시오.
 
 select emp_name, salary from employee where salary between 30000000 and 4000000;
 (select emp_name, salary from employee where salary<=3000000 and salary=>5000000);
 
 -- between 은 a 부터 b까지의 number형에 대해서만 범위를 지정하여 검색하는 기능이다.
 -- 대상이 되는 column으로 크다, 작다의 기호가 위치해있어야 한다.
 
 --12 [ is nu; is not null;]
 
 select emp_name"명칭", bonus"보너스" from employee where bonus is not null;
 select emp_name"명칭", bonus"보너스" from employee where bonus is null;
 
 -- column의 타입이 숫자일 때, 값이 null이 아니라면 출력. 
 --  column의 타입이 숫자일 때, 값이 null이라면 출력

 
 
 -- 13. [ and 와 or ]
 select emp_name, dept_code, salary from employee where dept_code='D1' or dept_code='D2';

 -- ex) and : where salary=>3000000 and salary<=5000000; 
 --     where salary between 3000000 and 5000000;  
 -->    where A>= and b<= 의 부동호를 통한 범위 지정과  where A between a and b;와 같다.
 
 -- ex) or : where salary 3000000 or salary 5000000;
 --     in () : where salary in (3000000, 5000000)l
 --     *subquery문에서는 같다라는 의미를 갖는다.
 
 
 -- 14. [in / not in ] : ~거나, ~거나 ~인 것을 출력하시오/ ~가 아니거나 ~가 아니거나 ~가 아닌 것을 출력
 
 select emp_name"명칭", salary*12||'원'"연봉" from employee where salary not in (3000000, 5000000);
select emp_name, salary from employee where salary>=3000000 and salary<=5000000;
 
 -- 특정 column에서 특정 data를 가진 row를 보고 싶을 때
 -- or가 아닌 column 명(not) in('data', 'data', ....)으로 표현가능.
 -- 갯수 제한 없음
 
 
 
--------------------------------------------------------------------------------------------------
-------------------------------------------[ 포괄 검색 ] ------------------------------------------

-- 15.[ where A like B ] 만약 A가 B와 같다면 
-- 16. [%s%] 글자"중에" s가 들어간 
       [김%] 첫글자가 김일 경우
       [%김] 끝 글자가 김일 경우
-- quiz 1 - email 철자중에 s가 들어간 것 같은 사람들을 모두 출력하시오.

select emp_name, email from employee where email like '%a%';

 
 
 -- 17. [_] 특수 자리를 나타내는 언더바
 
 -- quiz 1 : email에서 아무 글자 세자리가 있고, 성이 전이고 두번째자리와 세번쨰자리는 아무거나.  
 select emp_name"이름" from employee where emp_name like '전__';
 
 
 -- quiz 2 : 성이 민씨이며, 이름의 마지막이 이 자인 사람을 출력하시오.
 
 select emp_name from employee where emp_name like '민%%이';
 
 -- 18. [ - / #_escape ] : 탈출문자이며, 특정 기호에게 능력이 부여되지 않게끔 하는 탈출능력을 부여한다.
 --                       구분자의 역할을 한다.
 
 -- quiz 1 : email에서 아무 글자 세자리가 있고, 앞자리가 _ 언더바인 것을 출력
 
 
 select email from employee where email like '___#_%' escapre'#';
 
 -- 이메일 첫 세글자로 시작하고, 샵을 분기점으로, 언더바가 자릿수의 역할을 하지 않고
 -- 두의 퍼센테이지 기능으로 언더바로 시작하는 email을 찾도록 구분시켜주는 '___#_%'escape'#';
 -- 구분자의 역할 + 뒤의 오는 특수 문자의 기능을 사라지게 함.
 -- ex)    System.out.println("w"hello"w"); 안에 있는 ""을 기능을 상쇄시킴.
 
 
----------------------------------------------------------
---------------[ 응용 문제 ]-------------------------------

-- 1. 모든 직원의 이름과 급여를 출력하시오.


select emp_name"직원", salary"급여" from employee;
-- employee table에서 salary column(title)과, emp_name을 출력하시오.

-- 2. 직원 중에 유재식이 있다면, 이름과 샐러리를 출력하시오.

select emp_name"직원명", salary"급여"||'원', from employee
-- || 는 cloumn(열, title의 집합)의 실제값을 추가. 단 데이터로 저장되지는 않는다.
-- column(열, title의 집합)의 title 명칭 지정은 double quotation => "~명칭 입력~"
-- employee table에서 emp_name과 salary를 출력하시오.



-- 3. 급여가 300만원 이상인 직원 모두 출력하시오.

select * from employee where salary=>3000000;

-- 같다만 비교가능한 것이 아니라, 부등호식을 통한 대소관계도 비교 가능.
-- 급여가 300백만원 이상한 직원의 정보를 employee table에서 *(전부) 출력하라.


-- 4. and / 이름이 송은희이며, 급여가 6백만원 이하인 직원.

select * from employee where emp_name='송은희' and salary<=6000000;
-- java로 치자면 ||


-- 5. or / 직원 이름이 송은희이거나, 직원id가 201인 직원

select * from employee where emp_id=201 or emp_name='송은희';
--총 2개의 raw를 출력한다.


-- 6. select A from B where c;

-- 이것은 자바로 보았을 때,
-- select(for문)이 계속돌면서 출력하라.
--  employee table에서, if(만약에) emp_name=송은희이거나
--  emp_id 201이거나이기에 두명다 출력된다.


-- 7. job table에서 job name만을 출력하시오.

select job_name"직업명" from job_table;


-- 8. employee table을 전부 출력하시오.


select * from employee;


-- 9. employee user table에서 이름, 이메일, 전화번호 고용일 만을 출력하시오.


select emp_name"직원명", email, phone"전화번호", hire_date"고용일" from employee;

-- 10. employee 테이블에서 고용일, 이름, 월급만 출력하세요.

select hire_date"고용일", mep_name"직원명", salary*12||'원' "연봉" from employee;
 
 
 -- 11. employee table에서 월급이 250만원 이상인 사람의 이름과 급여등급(sal_level)을 출력.
 
select emp_name"직원명", salary*12||'원'"연봉" from employee where salary>=2500000;

-- 12 employee 테이블에서 350만 이상이며, j3 직급코드에 속한 사람의 이름과 전화번호를 출력하라.

select emp_name"직원명", phone"전화번호" from empoyee where salary=3500000 and job_code='J3';

-- ***주의사항으로 위에서도 언급했지만, data는 저장된 값에서 오차가 있어선 안된다.
--    따라서 대소문자 구분을 확실하게 해야 함을 잊지 말자, column 및 명령문은 대소문자 관계없다.


-- 13. employee table에서 20년 이상의 근속일자의 이름, 급여, 월급, 보너스를 출력하시오.

select emp_name, salary, bonus from employee where floor(sysdate-hire_date)/365>=20;
-- sysdate-hire_date는 근속한 날짜가 일수로 표현됨.
-- 근속일수를 365로 나누면 년차가 나온다.
 
 
 
 -- 14. 급여가 300만원 이상 400만원 이하인 직원의 이름 부서 급여 출력.
 
 select emp_name, dept_code, salary from empoyee where salary>=30000000 and salary<=4000000;
 sleect emp_name, dept_code, salary from employee where salary between 3000000 and 4000000;
 
 
-- 15. hire_date를 토대로, between 사용해보기.
--   date 함수 0'5/01/01' 에서 '10/12/31'

select emp_name"직원명" from empoyee where hire_date between '05/01/01' and '10/12/31';



-- 16. 이메일 끝 글자가 s인 경우


-- quiz 2 -
select emp_name, email from employee where email like '%s';

 
---------------------------------------------------------------------------------------------
-------------------------------[ 최종 문제 ] -------------------------------------------------


-- 1. '이 '씨 성이 아닌 직원 사번, 이름, 이메일을 조회하세요.


select emp_no"사번", emp_name"이름", email from employee where emp_name not like '이__';
-- 첫글자 이씨가 아니고 두번째와 세번쨰 자리는 아무거나

select emp_no"사번", emp_name"이름", email from employee where emp_name not like '이%';
-- 첫째 자리가 이씨가 아닌 경우(퍼센테이지)


-- 2. employee table에서 고용일이 90/01/01~01/01/01 이내의 사원 모두 출력

select emp_name"직원명", hire_date"고용일" from employee where hire_date between '90/01/01', '01/01/01';

-- 3. employee 테이블에서 이름 끝이 연으로 끝나는 사원 이름 검색

select emp_name"직원명" from emlpoyee where emp_name like '%연';

-- 4. employee table에서 전화번호 처음 3자리가 010이 아닌 사원의 이름 ,전화번호 출력

selectemp_name"직원명", phone from employee where phone not like '010'%;

-- ** phone은 자료형이 num이 아니라 문자형인 varchar이다. 
--     따라서 single quotation
--     숫자는 single quotation or not
--     column의 명칭 부여는 double quotation
--     row에 reteral value 부여는 single quotation.


-- 5. emoloyee table 에서 메일 주소에 s로 들어가면서, dept_code가 d9또는 d6이고
--    고용일이 90/01/01~00/12/01이면서, 월급이 270만원 이상인 사원의 전체 정보를 취득하세요.



select * from employee where 
email like '%s%' and
dept_code in ('D9', 'D6'); and --Reteral value, 즉 data는 반드시 입력된 값대로 입력! 대소문자도 지켜야한다.
hire_date between '90/01/01' and '00/12/31'; and
salary>=2700000;
 
 
