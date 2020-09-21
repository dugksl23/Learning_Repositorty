

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
            
            
          
            
            
            
           
            
            

            
  
  
  


