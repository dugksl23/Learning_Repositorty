
---------------[날짜 관련함수] ----------------


--*** 1. sysdate : 시스템에 설정된 시간을 "년/월/일"로 현재시간을 표시해준다.
--    current_date : 현재 속한 국가의 시간대를"년/월일"로 현재시간을 표시해준다.

select sysdate from dual;
select current_date from dual;


-------------------------------------------------------------------------
-------------------------------------------------------------------------

-- 2. to_date 
-- p1 : 숫자 및 문자를 날짜의 형태로 맞추어서 기입
-- p2 : p1의 인자를 출력할 날짜의 형태를 지정, 보통 /, :, "년" 으로 표기.
-- 출력되는 형태는 yyyymmdd or yyyy/mm/dd 로 년월일까지 무조건 출력이 되며,
-- sysdate또한 일까지 표기가 되기에 일자까지 표기가 된다.
-- 하지만 날짜 형태로된 string 문자열 or 숫자를 년월 또는 년도만 입력했을 경우
--  ex) '1990', 'yyyy') 이런식으로 맞춰서 입력해줘야 하며, 나머지 월일은 해당 월로 출력되며,
--                      일자는 1일로 표기된다.
-- 시스템에 설정된 월에 맞춰서 나머지 월일이 출력된다.

select to_date(sysdate, 'yyyy/mm/dd') from dual; --20/09/20
select to_date('19900318', 'yyyy/mm/dd') from dual; -- 90/03/18
select to_date('199006', 'yyyymm') from dual; -- 90년 6월 1일 출력
select to_date('1990', 'yyyy') from dual; -- 90년 9월 1일 출력
select to_date('1990', 'yyyy')-1 from dual; -- 90년 8월 30일 출력
-- to_date처럼 date type에서 -1 및 +1은 하루를 더하고 빼는 것이다.
-- 다만 to_char()는 date 함수가 아니기에 사칙연산이 되지 않는다.


-- 현재 날짜에서 하룰 빼시오.
select to_date(sysdate, 'yyyy/mm/dd')-1 from dual; -- 20년 9월 19일
-- 현재 날짜에서 하룰 빼시오.
select to_date(sysdate, 'yyyy/mm/dd')+1 from dual; -- 20년 9월 19일


-- 1. [ to_timestamp(DATE TYPE) ] : 초단위 이하의 밀리세컨드까지 처리를 한다.

select to_timestamp(sysdate) from dual;

--20/09/19/00:00:00.0000000000, 9자리까지 처리.

select to_char(sysdate, 'yy/mm/dd (dy) hh:mi:ss') from dual;
select to_char(sysdate, 'yy/mm/dd (day) hh:mi:ss') from dual;


-- 2. [ months_between(A, B)] : p1의 인자를 기점으로 p2의 날자까지의 개월수를 반환한다. 
-- return 값은 개월수
-- 년차를 구할 때 많이 쓴다.
-- p1의 날짜 date(시간)을 기점으로
-- p2 인자의 시간이 미래면 -로 출력, 과거면 +로 출력
-- p1 : date 형식의 날자타입
-- p2 : '20/09/01'

-- months_between에 년원일로 입력하면 소숫점 발생.
-- months between (from, to)
select emp_name"직원명", months_between(sysdate, hire_date)"개월수" from employee; -- 367.123123

-- 소숫점으로 인해서 floor로 소숫점 버림.(나눗셈이 있기에)

select emp_name"직원명", floor(months_between(sysdate, hire_date))"개월수" from employee; -- 367.123123

-- 이 개월수를 다시 12개월(1년치)로 환산.
select emp_name"직원명", floor(months_between(sysdate,hire_date)/12)"년차" from employee order by 1 asc;
select emp_name, hire_date, ceil((sysdate-hire_date)/365)"년차" from employee order by 2;
select emp_name, hire_date, ceil(months_between(sysdate, hire_date)/12)"년차" from employee order by 2;

                                           
                                           
-- 3. [ add_months(A, B)]
-- A를 기준으로, B의 숫자(개월수)를 입력하면, B를 더한 미래의 년월일를 RETURN한다.
-- p1 : date type or '19/01/01'
-- p2 : 정수                                           
-- return은 date 타입    
-- 날짜 타입에 정수룰 나눗셈하면 개월수가 됨.
                                           
                                           
                                           
                                           
                                           
 -- 1. 입대일(오늘)과 전역일(1년 8개월)을 구하시오                                          
 select sysdate"입대일", add_months(sysdate, 18)"제대일" from dual;                                         
                                                                                   
 -- 2. 군복무 일수를 구하시오.
  
                                           
select sysdate"입대일", add_months(sysdate, 18)"제대일", (add_months(sysdate, 18)-sysdate)"복무일" from dual;
                                       
-- **날짜끼리는 뺄셈만 가능하다.
                                           
                                           
-- 4. [ next_day ] : 특정 날짜를 기준으로 가장 가까운 요일을 얻어오는 함수
                                           
 select next_day(sysdate, '월요일') from dual;
 -- session이 한국으로 되어있기에 한국어로 표기한다.
 -- 이외에도 (일월화수목금토) 순으로 숫자를 입력해도 된다.
 -- return 값은 date함수이며, 날짜끼리 뺼셈할 땐 일수로 계산되어 뺄셈이 됨.
 
 -- 1234567
 -- 일~토

select next_day(sysdate, 2) from dual; -- 20/09/21(월)이 출력됨.
select next_day(sysdate, 2)-1 from dual; -- 20/09/20(일)이 출력됨.y
select next_day(sysdate, 2)+1 from dual; --  20/09/22(화) 출력
-- -1 및 +1은 date 함수에서만 가능하다.
                                           
                                          
-- 5. [ last_day ] 특정 날짜 기간의 마지막 일자를 return 한다.
-- ex) 지금이 9월이기에 sysdate을 하면 9월이 뜬다.
--     따라서 9월의 마지막 날인 31일을 return

select last_day(sysdate) from dual;

-- qick quiz_01 다음달의 마지막 일을 구하라.

-- 필요한 return 값은 다음달 마지막 일자.
-- 미래의 개월수를 받아올 함수, add_months(sysdate, 1)
-- 특정 미래 기간의 마지막 일자를 구하는 함수 last_day()

select last_day(add_months(sysdate, 1))"다음달 마지막 일" from dual;
select to_date(last_day(add_months(sysdate, 1)), 'dd')"다음달 마지막 일" from dual;

                        
                        
-- qick quiz_02 지난달의 마지막 일을 구하라.

select sysdate"현재일", last_day(add_months(sysdate, -1)) from dual; -- 20/08/31 출력
                        
                        
                        
                        
                        

-- 5. [ extract : 발췌 ] 
-- 특정 시간을 기점으로 해서 년 월 일을 뽑는다.

select extract(year from sysdate) from dual;
select extract(month from sysdate) from dual; 
select extract(day from sysdate) from dual; 

-- 현재 시점에서 내년을 뽑아내시오.
                        
select extract(year from (add_months(sysdate, 4)))||'년' from dual;

-- 현재 시점에서 작년을 뽑아오세요.

select extract(year from sysdate)-1 from dual; -- 2019년
select extract(year from (add_months('20190909', -1))) from dual; -- 2019
select extract(year from (to_date(20190909, 'yyyymmdd'))) from dual; -- 2019

** -- 날짜 관련함수에서 to_char는 되지 않음.
select extract(year from (to_char(20190909, 'yyyymmdd'))) from dual;
 
                                  
                                  
 
                                  
                                  
               
                                     
                                   
                                     

------------------[ 문제 ]----------------


--Q1. employee 테이블에서 사원의 이름, 입사일, 근속년차를 출력하세요.
-- 입사일을 출력할 땐 yyyy년 m월 d일로 출력 to_date
-- 년차를 출력할 땐 올림하여 출력 ceil
-- 입사일 기준으로 오름차순 정렬
                                     
select emp_name"직원명", salary, hire_date, round((sysdate-hire_date)/365,0)||'년차' from employee;                                  
select emp_name"직원명", salary, hire_date, ceil((sysdate-hire_date)/365)||'년차' from employee;                                  
-- 소숫점 자리 다 올림;
               
               
              
                        
                        
                        
                        
--Q2. employee 테이블에서 사원의 이름, 입사일 출력
-- ||'년'|| => + 개념으로 보면 된다. 
select emp_name"직원명",
extract(year from hire_date)||'년'||
extract(month from hire_date)||'월'||
extract(day from hire_date)||'일'||"입사일"
from employee order by 2;               
                           
                                           
                                           
---------------------------------------------------------------------------------------------------
               
               
----- [ 형변환 함수 ] -------------
               
-- 1. [ to_char ]- 날짜를 문자로 변환한다.
 -- 숫자나 날짜 타입을 , 문자로 변환.
 --'yyyy/mm/dd hh12:mi:ss day'
 -- hh12/24 시간을 12시간 단위 or 24 시간 단위로 표기
 -- mi minute의 약자로 분을 표기
 -- dy or day => 일 or 일요일              
               
select to_char(sysdate, 'yyyy"년" mm"월" dd"일" hh12: mi: ss dy') from dual;
               
-- Quiz employee table에서 사원명, 고용일을(1990.02.06(화))로 출력.
               
select emp_name"직원명", to_char(hire_date, 'yyyy.mm.dd (dy)')"고용일" from employee;               
                                           
-- Quiz employee table에서 사원명, 고용일을(1990(화))로 출력.
select to_char(hire_date, 'yy"년" (dy)') from employee; -- 20년 (일) 출력
-- 날짜를 원하는 요일과 형태로 출력할 땐 to_date보다는 to_char가 효율적.
               
               
                                           
-- 2. [ to_char() ] - 숫자를 문자로.
-- l은 자국통화를 나타낸다.
-- 999,999,999로 표기하는 이유는 자릿수가 모자르면 글자가 깨진다.
-- ex) ###,###,### 그렇기에 충분하게 차리를 만들어줘야 한다.
select to_char(salary, 'l999,999,999')"급여" from employee;               
                                           

                                           
 -- 3. [ltrim or ltrim ] "좌측 정렬 or 우측 정렬
               
               
 select emp_name"직원명", ltrim(to_char(salary, 'l999,999,999'))"급여" from dual;
                                     
-------------------------------------------------------------------------------------------
                                     
                                     
                                     
                                     
 -- 4. [ to_date ] 문자 또는 숫자 형식의 날짜로 type의 형태로 만들어서 date 를 출력한다.
-- 숫자 
select to_date(20200920, 'yyyy/mm/dd') from dual; -- 20/09/20 출력
select to_date(202009, 'yyyy/mm') from dual; -- 20/09/01 출력
select to_date(2020, 'yyyy') from dual; -- 20/09/01 출력
                                     
                                     
-- 5. [ nvl(A, 0) ] A가 NULL이면 0을 Return
-- p1의 인자값은 number
                                     
select emp_name"직원", salary, nvl(bonus, 0) from employee;                                     
                       
                                     
-- [ nvl2(A, 1, 0) ] A가 NULL 아니라면 A, null이면 0;
                                     
select emp_name, nvl2(bonus, 1,0) from employee;                                     
select nvl2(bonus, '보너스 없음', '보너스 있음') from employee;                                     



--------------------------------------------------------


-- 6. [ decode(A, A, 'A입니다.', B. 'B입니다.')]  -- java로는 switch문
-- P1의 분석할 대상값
-- P2, P2의 인자가 P1과 같다면 P2를 출력
-- P4, P4의 인자가 P1과 같다면 P4를 출력                                     
-- 기본 전제가 ==와 같다라면이 전제이며, 크고 작고의 부등호는 조건식 형립이 안 되어서 제한이 많다.
                                     
Select decode(1, 1, '1입니다.', 2, '2입니다.') from employee;
                                     
-- quiz 01 직원들의 성별을 출력;
                                     
return : char for emp_no, 성별                                     
method : instr(a, -1, 1,1)                                     
                                     
select emp_name, emp_no, decode((substr(emp_no, 8,1)), 1, '남', 2, '여')"성별" from employee order by 2;
-- substr은 숫자로 받아서 문자로 return하지만 오라클의 문맥 인식기능이 있기에
-- 설령 문자로된 1이라 해도 비교군에 sinlge quotation이 아닌 숫자 1이라고 해도
-- 문맥검사기에 자동으로 형변환이 이루어지기에 비교군으로서 성립이 된다.
-- 즉 숫자로 substract했을 때, 비교군이 숫자인(no quotation)이여도 비교군으로서 성립이 된다.




-- ** 7. [ case when end ] java의 if문
                                     
-- 직원들의 성별을 그리고 2000년생 을 나누시오.
-- switch문인 decode의 기능을 모두 수행가능.

select emp_name"직원명", emp_no"생년월일", case
when substr(emp_no, 8,1)=1 then '남'
when substr(emp_no, 8,1)=2 then '여'
else '2000년생'
end "성별"
from employee;
                                     
                                     
                               

                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                     
                                           
                                           
