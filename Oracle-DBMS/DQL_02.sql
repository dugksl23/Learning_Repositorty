-- Dql_02_기초문법

--- [ 문법 ] --- 


--------- [ 1. order by 숫자 column(data type : numer, char) asc, desc ] ----------


-- 오름차순, 내림차순
-- number이면 숫자의 오름,내림차순
-- char이면 알파벳, 한글 철자순 ㄱ~ㅎ, ㅎ~ㄱ
-- char,num ex) D1,D2,D3


--01. 급여 순으로 오름차순

select emp_name"직원명", salary"급여" from employee order by salary asc;
-- order by 문법에서 asc는 디폴트값이다.

-- 02. 급여 순으로 내림차순.

select emp_name"직원명", salary"급여" from employee order by salary desc;


-- 03. colmn으로 정렬 가능.

select emp_name"직원명",  salary from employee order by 2;

-- 04. dept_code로 오름차순

select emp_name, dept_code from employee order by 2;

-- D1, D2, D3, F1 별로 정렬이 된다.

-- 05. 부서별 오름차순 및 job_code 오름차순

select * from employee order by dept_code, job_code;
-- 부서별 오름차순 먼저 후에 job_code 오름차순


--------- [ 2. null first ] ----------
-- 정렬 대상에 null 값은 자동적으로 후순으로 배치된다.
-- null 값을 order by에서 상위에 출력하는 문법.

select emp_name, salary from employee order by salary null first;
select emp_name, salary from employee order by salary asc null first;






--------- [ 이전 수업 복습 문제 ] ----------


-- 1. 입사일이 5년 이상, 10년 이하인 직원 이름, 주민번호, 급여, 입사일 출력

select emp_name"직원명", emp_no"주민번호", salary"급여", to_char(hire_date, 'yyyy"년" mm"월" dd"일" (dy)')"입사일" from employee 
where floor((sysdate-hire_date)/365) between 5 and 10;


-- 2. 퇴사(ent_y)가 아닌 재직(ent_n) 중인 직원의 이름과 부서코드 출력

select emp_name"직원명", dept_code"부서" from employee where enp_yn='N';
-- where 문에서 실제값을 비교할 때는 반드시 single quotation을 붙여야 한다.


-- 3. 근속년수가 10년 이상인 직원의 이름, 급여, 극속연수(소수점 버리기)를 출력
--   근속연수 오름차순 출력
--   급여는 50&인상.

select emp_name"직원명", salary*1.5"급여", floor((sysdate-hire_date)/365)"극속연수" from employee where floor((sysdate-hire_date)/365)>=10 order by 3;


-- where 에서 부등호를 통한 조건식을 걸고
-- between, in 등은 입력된 date를 범위로 하여 비교한다.


-- 4. 입사일 이 99/01/01 ~ 10/01/01 직원 출력하는데, 급여가 200이하인 사람의 이름, 주민번호, 이메일, 폰번호, 급여 출력

select emp_name"직원", emp_no"주민번호", email, nvl(phone, 0), salary"급여", nvl(bonus,0) from employee where hire_date between
'99/01/01' and '10/01/01' and
salary<=2000000;



-- 5. 급여가 200만~300만원 사이인 여직원 중에서 4월 생일자인 직원을 검색하여 이름 주민번호 급여 부서코드를 출력하되,
--   주민번호 순으로 내림차순 출력

select emp_name"이름", salary"급여", emp_no"주민번호", nvl(dept_code, '없음')"부서코드" from employee where 
(salary between 2000000 and 3000000) and
emp_no like '___4__-2%' order by 3;

-- 6. 남자 사원 중 보너스가 없는 사원의 오늘까지 근무일을 측정하여,
-- 1000일 마다 (소수점 제외) 급여의 10% 보너스를 계산하여 
-- 이름 특별 보너스(계산금액을)을 출력하시오.

select emp_name"직원명", to_char((floor((sysdate-hire_date)/1000)*(salary*0.1)), 'l999,999,999')"보너스", floor((sysdate-hire_date))||'일'"근무일수" from employee
where emp_no like '______-1%' and
         bonus is null;



--------------------[  DQL_02 : 기초 문법  ] -------------------------------------





-- 1. length : 문자열을 인자로 return 하는 함수select abc)_


select 'char_length_test'"입력 char",length('안녕하세요')"문자 길이" from dual. - 6출력
select '10000000'"입력 num", length(1000000)"숫자 길이" from dual; -- 7출력

-- '~~'를 통해서 새로운 column을 생성해낼 수 있다.




-- 2. lengthb : 문자열을 인자로 문자열의 byte 크기를 return 하는 함수

select '안녕하세요'"byte test", lengthb('안녕하세요') from dual; -- 15 byte 출력

-----------------------------------------------------------------------------------------
 -----------------------------------------------------------------------------------------
 

-- 3. instr : 문자열 내에서 특정문자를 탐색하여, 해당 문자의 index를 return하는 함수.
-- string(문자열) in(내에서) 해당문자를 검색하여, 해당문자가 위치한 index를 return.

-- '안녕하세요'에서 요의 index를 반환하시오.
select '안녕하세요'"instr test", instr('안녕하세요', '요', 1) from dual; 

-- 5를 출력

-- [ 문법 풀이] --

-- instr(p1, p2, p3, p4);
-- p1 : 탐색 문자열
-- p2 : 문자열 내에서 찾을 글자. 문자열이면 singlequotation, 숫자면 no quotation.
-- p3 : 문자열 내에서 n 번째 index부터 p2 검색 수행. 
-- p4 : p2가 등장하는 n번째 index를 return;

select 'Hello World Hi Java'"Instr test", instr('Hello World Hi Java', 'J', 1,1) from dual;


-- quick Quiz 01 : Employee table에서 각 직원의 이메일 @ 기호 위치를 출력해보세요.
             
             
 *검색 대상 : 직원의 이메일
 *적용 조건 : 이메일 내에서 @의 인덱스를 출력
 *return type:char형
 *적용함수 : instr();
 
 select emp_name, email, '@'"index_test", instr(email, '@', 1,1)"index" from employee; 
 
 

 

-- 4. substr : 문자열 내에서 특정 문자를 꺼내온다.


-- [ 문법 풀이] --


-- ** 정수는 좌측을 기준으로 자를 위치를 결정

-- instr(p1, p2, p3, p4);
-- p1 : 탐색 문자열
-- p2 : 문자열 내에서 n번쨰 인덱스부터 문자를 잘라온다.
-- p3 : p1을 기점으로 n 번(p3)째까지 잘라온다.


select '안녕하세요를 빼와보자'"substr_test", substr('안녕하세요를 빼와보자', 1, 4) from dual;


-- ** 음수 기준, 오른쪽을 기점으로 하여, 오측에서 2번째까지.

select substr('안녕하세요들?', -3, 2) from dual;

-- 요들까지 출력됨

-- quick Quiz 01  사원명에서 성만 중복없이 사전순으로 출력.
select distinct emp_name"직원명", substr(emp_name, 1, 1)||'**'"직원 성" from employee order by emp_name;

-- quick Quiz 02  사번, 주민번호, 급여 출력
--                주민번호 뒷자리는 6자리는 *표로 표시

select substr(emp_name, 1,1)||'**'"성", substr(emp_no, 1,9)||'******'"주민번호" from employee;





-- 5. replace : 문자열 내에서 특정 문자열을 교환한다.


--- [ 문법 풀이] ----
-- p1 : 대상 문자열
-- p2 : p1에서 바꿀 문자를 인자로 넣는다.
-- p3 : p2에서 교환할 문자를 지정.

select abc('안녕하세요')from dual;
select '안녕하세요.'"replace_test", replace('안녕하세요.', '안녕', '잘가') from dual;

--------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------

-- [ 숫자 처리 관련 함수 ] ---


-- 6. [ abs ] : 절대값을 구하는 함수 absolute value
-- 절대값이란, 음수 및 양수를 제외한 데이터 본질의 값이다.

select '-3'"나 음수임", abs(-3) from dual;


-- 7. [ mod ] 나머지 값 구하기.


select 14/7 from dual; -- 출력값이 2
select 15/7 from dual; -- 출력값이 2.1~~

select mod(15/7) from dual; --나머지 값 2출력


--*** 8. [ round ] : 반올림. 소수점 자리르 지정하기 위해서 round 함수를 사용한다.

 -- 123.456
 ---3-2-1.012

-- 1. 정수 : 소숫점

-- **소수점 첫번째 자리, 파라미터 0은 생략가능
--    정수는 0이 첫번째, 1이 두번째자리이다.
--    반올림할 자릿수의 결과물의 자릿수를 입력.
select round(3.14) from dual; --3
select fround(3.14, 0) from dual;-- 3

select fround(3.5) from dual; -- 4
select fround(3.5, 0) from dual; -- 4 


-- 2. 음수 : 0의 자리부터 1
--          10의 자리를 2
--          정수 기준 우측에서부터 -1이다.

select round(2433, -3) from dual; -- 2000;
select round(2500, -3) from dual; -- 3000;
select round 2500, -4) from dual; 0;
select round(2433, -2) from dual; --2400;
select round(2433, -1) from dual; -- 2430;




--*** 9. [ floor ] : 소숫점자리를 모두 버린다.


select 1000/3 from dual; -- 333.333333;
select floor(1000/3) from dual; --333;
select floor((sysdate-hire_date)/365) from employee;



--*** 10. [ trunc ] : 소숫점자리를 지정하여 버린다.
-

select trunc(1234.543, 2) from dual; --1234.54
select trunc(1234.549, 2) from dual; --1234.54


-- *** [ ceil ] : 올림함수, 
-- 소숫점 자리를 지정할 순 없으며, 소숫점 자리를 모두 올림한다.

select cell(1234.567) from dual; --1240;



