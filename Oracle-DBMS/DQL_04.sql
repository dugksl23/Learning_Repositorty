

-- 요약 정리 --

-- 1. 3강까지 배웠던 orcle 문법은 단일행 함수이다.
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
            
-- 1. 그룹별 급여를 가장 많이 받는 부셔코드를 출력하시오.

select 
  max(salary)
  dept_code
   from employee
    group by dept_code
    order by nulls 1 first;



-- nulls first는 order by에서 상위로 위치시키는 함수이다.
-- 기본값으로는 최하단에 위치하게 된다.
-- ex) order by 1 nulls first or order by 1;          
            
            
-- 2. 부서별 인원수를 구하시오.

select 
  dept_code 부서코드,            
  count(*)
  from employee
  group by dept_code
  order by 1;      



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

--  함수에서는 no quotation
-- r그러나 함수에서는 꼭 double quotation이 필요하다.
            
            
            
-- 5. employee tabledptj 직급이 j1인 사람 제외하고
--    입사년도 및 입사년도별 인원수를 조회하여, 년도 기준 오름차순 정렬.

select
 extract(year from hire_date)입사년도,
  count(*)
    from employee
      where job_code not like 'J1'
         group by extract(year from hire_date)
           order by 2 DESC;

    
select  
job_code, 
to_char(hire_date, 'yy')"입사년도", 
count(*)"년도별 인원수" 
from employee 
where substr(job_code, 1,2) not like 'J1'
group by job_code, to_char(hire_date, 'yy') 
order by 1 asc, 2 desc;            
          


-- 6. employee table에서 연대생 별 급여 평균

select
    substr(emp_no, 1,2) "출생년도",
    to_char(avg(salary), 'l999,999,999') "급여 평균",
    count(*) "년생 숫자"
      from employee
        group by substr(emp_no, 1,2), salary, bonus
         order by 1;
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

-- 
            

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



--- 9. 3000만원 이상
--                  
           
            
            

            
  
  
  


