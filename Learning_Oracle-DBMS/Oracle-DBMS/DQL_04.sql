
---- 이전 문제 풀이 복습 ----


-- 1. 직원명과 이메일, 이메일의 길이를 출력하시오.


select email, lengthb(email) from employee;

-- 2. 직원의 이메일과 이메일 주소중 아이디 부분만 출력

select 
  emp_name 직원,
  email 이메일,
  substr(email, 1, instr(email, '@', 1,1)-1) 
from employee;

-- 3. 60년생의 직원명과 보너스 값을 출력하시오.

select
emp_name 직원명, 
nvl(bonus,0), 
case
  when substr(emp_no, 1,2) >=60 and 
  when substr(emp_no, 1,2) <=69;
end 
from employee;




-- 4. 010 핸드폰을 쓰지 않는 사람의 수

select phone, count(*)||'명' 인원수
from employee
where phone not like '010%';


-- 5. 직원명과 입사년월을 쓰시오.

select 
  emp_name 이름,
  to_char(hire_date, 'yyyy')입사년월 from employee;
  
-- 6. 직원명과 주민번호를 조회하시오.

select
  emp_name 직원명,
  substr(emp_no, 1,8)||'******'주민번호 from employee;
  
  
-- 7. 직원명, 직급코드 연봉(원) 조회하기.

select 
  emp_name 직원명,
  to_char(floor((salary*12)*nvl(bonus,0)), 'l999,999,999') 연봉
from employee;

-- 8. 부서코드가 D5 및 d9이며, 2004년도에 입사한 직원의 수 조회.
--    사원명과 부서코드로 정렬.

select
  emp_name 직원명,
  hire_date
  from employee
    where extract(year from hire_date)>=2004 and
    dept_code in ('D5','D9');
    
 
 select 
  emap_name,
  hire_date,
from employee
  where hire_date like '04'% and
        dept_code='D5' or dept_code='D9';
        
  select 
    emp_name,
    hire_date
  from employee
    where dept_code in ('D5', 'D9') and
          substr(hire_date, 1,2)=2004;
    
    
-- 9. 직원명과 입사일을 오늘까지의 근무일 수를 조회.

select 
  emp_name 직원명,
  ceil(sysdate-hire_date) 근무일수
  from employee;
  
-- 10. 모든 직원의 나이중 가장 많은 나이와 가장 적은 나이만 출력

select 
 120-max(substr(emp_no, 1,2))최연소 and
 120-min(substr(emp_no, 1,2))최장수 and
 from employee;               
                
-- 최연소 21, 최장수 58 출력

-- 11. 회사에서 야근을 해야 되는 부서를 발표한다.
--     부서코드가 D5, D6, D9이며,
--     야근이 없는 곳은 '야근 없음'으로 표시.

SELECT
  emp_name 직원명, case
   when dept_code='D5' then '야근'
   when dept_code='D6' then '야근'
   when dept_code='D9' then '야근'
   else '야근 없음'
   end 야근 유무
    from employee order by 2;     
                
                
                


-- 요약 정리 --

-- 1. 3강까지 배웠던 oracle 문법은 단일행 함수이다.
-- 2. 함수가 행 하나하나에 적용된다는 것이다.
-- 3. 이번 4강에서는 그룹합수에 대해서 알아보자.


----------------[ 그룹합수 ]----------------------

-- 그룹합수란?
-- 그룹 합수란 하나의 table(column)에 대한 압축범위이다.
-- 그룹 함수는 그룹화되지 않은 함수와 함께 출력할 수 없다. ex) single group function 출력 불가.


-- 1. [ sum(column명) ] : record의 column을 모두 더한다.

select sum(salary) from employee

select emp_name, sum(salary) from employee
-- emp_name은 그룹함수가 아니기에 출력되지 않는다.

-- 2. [ avg(column명) ] 


select avg(salary) from employee

select 
  trunc(avg(salary))연봉 평균
  sum(salary)연봉 총계
  from employee;
--같은 그룹함수끼리 출력가능.

--  3. [ count(*)/count(column명) ] : 모든 집합 함수 또는 모든 레코더의 갯수(행 전체의 갯수)를 출력.
-- ** count는 null 값을 출력하지 않는다.
-- ** count는 0을 카운팅한다.

            select count(emp_name) from employee;
            select count(bonus) from employee;
            select count(salary) from employee;
            select count(*) from employee;
            
-- 4. [ max(column명) ] : column(table) 내의 최대값을 return.
            select max(salary) from employee;
            select max(bonus) from employee;
            
-- 5. [ min(column명) ] : column(table) 내의 최소값을 return.
            
            select min(salary) from employee;
            select min(bonus) from employee;
  

-- > 그룹합수는 일반 함수 ex) emp_name 등과 함께 출력되지 않으며, 
--   그룹합수끼리 또는 단독으로 출력이 된다.

            
-- 6. [ group by colmun명 ] : column(table)을 기준으로 그룹핑
-- 중복 제거 및 order by 효과를 갖는다.
            
            
select emp_name from employee group by emp_name;
select dept_code from employee group by dept_code;
            
 -- 단점. 일반함수는 여전히 그룹 합수와 출력되지 않는다.
select emp_name, dept_code from employee group by dept_code;
            -- 출력이 되지 않는다.
            
select sum(salary), dept_code from employee group by dept_code;
-- sum()의 그룹합수와 group by의 대상인 dept_code는 출력이 된다.
            
            
-- 7. [ having 함수]            
 --having은 group by에서 if문의 역할을 한다.
 -- 일반적인 쿼리 실행 순서는


-- from - where -> group by -> having -> select -> order by

--> 시간의 순서상 where 절은 두번째로 실행되기에 wher 절에서는 그룹함수를 가질 수가 없다.

-- select nvl(dept_code, '인턴') from employee where avg(salary)=30000000 group by dept_code order by 1;
--> 여기서 avg() 그룹함수가 where절에서 나왔기에 불가능하다.
--> 따라서 시간의 순서상 group by에서 if문을 실행할 수 있도록
--> having을 쓴다.
            
select
   nvl(dept_code, '인턴'),
   to_char(floor(avg(salary)), 'l999,999,999')
from employee
where dept_code in ('D6', 'D9')
  group by dept_code
    having avg(salary)>=3000000
  order by 2;
  
            
            
 -- qucik Quiz 01 ---
            
-- 1. 그룹별 급여를 가장 많이 받는 사람의 월급과부셔코드를 출력하시오.

select 
dept_code,
max(salary)
from employee
 where dept_code is not null
group by dept_code;
order by nulls first;


-- nulls first는 order by에서 상위로 위치시키는 함수이다.
-- 기본값으로는 최하단에 위치하게 된다.
-- ex) order by 1 nulls first or order by 1;          
            
            
-- 2. 부서별 인원수를 구하시오.

 select
    dept_code,
    count(*)
from employee
    group by dept_code  
    order by 2 desc;



-- 3. 남녀의 월급 합계를 구하시오.


select case
 when substr(emp_no, 8,1)=1 then '남'
 when substr(emp_no, 8,1)=2 then '여'
end성별,
to_char(sum(salary),'l999,999,999') 급여 합계,        
count(*)
  from employee
    group by case
            when substr(emp_no, 8,1)=1 then '남'
            when substr(emp_no, 8,1)=2 then '여'
            end;
            
            
-- 4. employee table 에서 직급이 j1인 사람을 제외하며,
--    직급, 직급별 사원수 및 평균 급여 출력하시오.


select  job_code 직급,
count(*) "직급별 사원수",
--to_char(avg(salary), 'l999,999,999') "급여 평균"
to_char(avg(salary), 'l999,999,999') "급여 평균" 
    from employee
    where job_code not like 'J1'
      group by job_code
        order by 3;

                 
select job_code, count(*), ceil(avg(salary))
  from employee
where dept_code not in ('j1')
  group by job_code order by 1;
                 
-- 띄어쓰기를 할 때는 "" 필요하며, 띄어쓰기 대신 _ 언더바를 주고 해도 된다.
              
            
            
            
-- 5. employee table의 직급이 j1인 사람 제외하고
--    입사년도 및 입사년도별 인원수를 조회하여, 년도 기준 오름차순 정렬.

select
 extract(year from hire_date)입사년도,
  count(*)
    from employee
      where job_code not like 'J1'
         group by extract(year from hire_date)
           order by 1 asc;

    
select  
to_char(hire_date, 'yy')"입사년도", 
count(*)"년도별 인원수" 
from employee 
where substr(job_code, 1,2) not like 'J1'
group by to_char(hire_date, 'yy') 
order by 1 asc;            
          


-- 6. employee table에서 연대생 별 급여 평균

           
 select 
    substr(emp_no, 1,2),
    ceil(avg(salary)),
    count(*)
from employee
    group by substr(emp_no, 1,2) order by 1;
               
--> group by는 column 그 어떤 것으로도 가능하다.
--> 설령 from에 등장하지 않은 table라고 해도 쓸 수 있다.       


-- 7. 부서코드, 직급코드, 사원수, 급여평균을 출력
--  부서코드 및 잡코드 순으로 그룹핑.
            
 select 
        nvl(dept_code, '인턴') 부서코드,
        job_code "직급 코드",
        count(*) "직급별 사원수",
        sum(salary) 급여평균
            from employee
             group by dept_code, job_code
              order by 1;

--  8. 성별 인원수를 출력.            

select 
    nvl(dept_code, '인턴')"직급코드",
    case
        when substr(emp_no, 8,1)=1 then '남'
        when substr(emp_no, 8,1)=2 then '여'
      end "성별", count(*)
      from employee
      group by dept_code, substr(emp_no, 8,1)
      order by 1;
--> select에 그룹핑 함수가 사용되지 않았다면, group by에 넣어
--  노멀 함수로서의 기능을 상쇄시키고, 그룹함수로서 함께 출력해주자.



--- 9. salary 평균이 3000만원 이상인 직급코드 d6와 d9을 출력


select 
  nvl(dept_code, '인턴')"직급",
  to_char(floor(avg(salary)), 'l999,999,999')"급여 평균"
  from employee
    where dept_code in ('D6', 'D9')
       group by dept_code, salary
           having avg(salary)>=30000000
                order by 1;
                 
            
            

            
  
select  
dept_code,
avg(salary)
from employee
where dept_code in ('D6', 'D9')
 group by dept_code
  having avg(salary)>3000000;
  
  


                
     
