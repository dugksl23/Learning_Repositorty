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
-- p4 : 찾을 글자가 등장하는 n번째 index;

select 'Hello World Hi Java'"Instr test", instr('Hello World Hi Java', 'J', 1,1) from dual;


-- quick Quiz 01 : Employee table에서 각 직원의 이메일 @ 기호 위치를 출력해보세요.
             
             
 *검색 대상 : 직원의 이메일
 *적용 조건 : 이메일 내에서 @의 인덱스를 출력
 *return type:char형
 *적용함수 : instr();
 
 select emp_name, email, '@'"index_test", instr(email, '@', 1,1)"index" from employee; 
 
 
 
