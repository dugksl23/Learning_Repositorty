


------------------------------------- Chater 1 : subQuery란? -----------------------------------

-- query문 내에 또 다른 query를 추가하는 것을 바로 subQuery라고 한다.
-- 예제를 통해 query의 중요성을 알아보자

-- 01. 전지연의 manager를 알아내시오.


-- 1. 전지연 출력
select * from employee
    where emp_name='전지연';
-- 2. 출력된 record에서 manager id 확인
select * from emplyee 
    where manager_id=214;
    
--> 전지연의 manager를 찾아내기 위해서 총 2번의 query를 사용한 셈이다.
--  이번엔 subQuery문을 이용하여, 전지연의 manager 정보를 출력해보자.

------------ subQuery 문제 풀이 방법 -----------------------------

-- 01. 전지연의 manager_id의 모든 정보를 subQuery를 통해 출력하시오.

-- Output : emp_name, manager_id                
-- table :  employee
-- 1. from(table) : employee 
-- 2. where(if)   : emp_id를 기준으로 (manager_id가 같다면) --관리자도 사원으로 속해있다. 
-- 3. (subquery)  : (select manager_id from employee where emp_name='전지연'); 
-- 4. group by    :  
-- 5. having      :  
-- 6. order by    : 
-- 7. select      : *
 
 
 select emp_name, manager_id
  from employee 
  where manager_id in                                        -- in은 = 와 같다는 뜻이다.
    (select manager_id from employee where emp_name='전지현') -- 매니저의 id로 subQuery문 작성
    
    
-- 02. 전직원의 평균 급여보다 더 많이 받는 사람 출력.    

-- *Output : emp_name, salary                
-- *required table:  employee
-- 1. from(table) : employee 
-- 2. where(if)   : salary>   salary를 기준으로 (전직원의 평균급여보다) 크다면  
-- 3. (subquery)  : (select avg(salary) from employee); 
-- 4. group by    :  
-- 5. having      :  
-- 6. order by    : 
-- 7. select      : emp_id, salary
 
select 
  emp_id,
  salary
from employee
  where salary >
    (select avg(salary) from employee);
    
--** 이미 main query에서 조건문으로 평균급여보다 크다 라는 조건문 먼저 실행이 되었기에
--   subQuery는 비교 대상이 되는 employee table의 salary의 평균을 return하면 된다.



-- 일반 Query문

select
 emp_name,
 salary
from employee
  group by salary, emp_name
    having avg(salary)>3000000;


-- *Output : emp_name, salary                
-- *required table:  employee
-- 1. from(table) : employee 
-- 2. where(if)   : (그룹함수인 AVG() 함수를 써야 하므로 WHERE절은 통과)
-- 3. (subquery)  : 
-- 4. group by    : GROUP BY는 노멀 테이블과 출력되지 않으므로, SALARY별 그리고 EMP_NAME별로 묶는다. 
-- 5. having      : HAVING은 GROUPING된 테이블에 조건을 부여하는 IF문으로 
--                  EMP_NAME과 SALARY에 그룹함수와 함꼐 조건문을 부여하고자 할 떄 사용 된다.
--                  SALARY . AVG(SALARY)
-- 6. order by    : 
-- 7. select      : emp_id, salary





------------------------------------- Chater 2 : subQuery의 종류 -----------------------------------


-- 01. 단일행 subQuery
-- 02. 다중행 subQuery
-- 03. 다중열 subQuery
-- 04. 다중행 다중열 subQuery






---------- [ 01. 단일행 subQuery ]----------------------

-- Q1. 윤은해 직원의 급여와 같은 사원들의 사원번호, 급여출력
--     윤은해는 출력하지 말것.



-- *Output : emp_name, salary, emp_id                
-- *required table:  employee
-- 1. from(table) : employee 
-- 2. where(if)   : emp_id!='윤은해' and
--                  salary= 
--                  윤은해 직원의 급여를 기준으로, subquery에서 같은 salary를 비교군으로 작성.

-- 3. (subquery)  : (select salary from employee where emp_id='윤은해'); 

-- 4. group by    :  
-- 5. having      :
-- 6. order by    : 
-- 7. select      : emp_name, emp_id, salary


select 
  emp_name,
  emp_id,
  salary
from employee

  where emp_id!='윤은해' and
  salary in
       (select salary from employee where emp_name='윤은해');
       



-- Q2. 직원중 급여가 가장 많은 사람과 가장 적은 사람의 사번, 사원명, 급여를 출력.

-- *Output : emp_name, salary, emp_id                
-- *required table:  employee
-- 1. from(table) : employee 
-- 2. where(if)   : salary
--                  salary= 
--                  급여를 기준으로, subquery에서 max(salary)와  min(salary)를 비교군으로 return.
-- 3. (subquery)  : (select max(salary) from employee) or 
--                  (select min(salary) from employee);
                    --> 그룹합수인 max()와 min()는 되도록이면 단일 query문으로서 빼주는 것이 좋다.
-- 4. group by    :  
-- 5. having      :
-- 6. order by    : 
-- 7. select      : emp_name, emp_id, salary



select 
  emp_id,
  emp_name,
  salary
from employee
  where salary = (select max(salary) from employee) or
        salary =(select min(salary) from employee);
    
    
-- ** 단일 query의 단점으로는 비교군 하나당 하나의 단일 query를 형성해야 한다는 단점이 있기에
--    main query에서 max(salary)와 min(salary)를 비교하기 위해서, where 절에서 2개의 salary(인자)로 받아야 한다.
--    또한 max와 min 및 count는 그룹함수이기에 단일 query 절로 빼는 것이 간결하다.


-- Q3. D1과 D2 부서에 근무하는 사람들 중에서 
--     D5 부서 직원들의 평균 급여보다 많이 받는 직원들의 부서번호, 사원번호, 사원명, 급여출력

-- *Output : emp_name, salary, emp_id, dept_CODE                
-- *required table:  employee, department
-- 1. from(table) : employee, department
-- 2. where(if)   : dept_code in ('D1', 'D2');
--                  salary >
--                  급여를 기준으로, subquery에서 (D5부서의 평균)를 비교군으로 return.
-- 3. (subquery)  : (select avg(salary) from employee where dept_code='D5'group by dept_code);                    
--                   **그룹합수인 max()와 min()는 되도록이면 단일 query문으로서 빼주는 것이 좋다.
-- 4. group by    :  
-- 5. having      :
-- 6. order by    : 
-- 7. select      : emp_name, emp_id, salary


SELECT
  EMP_NAME,
  EMP_ID,
  DEPT_CODE,
  SALARY
FROM EMPLOYEE
  WHERE DEPT_CODE IN ('D1' , 'D2') AND
        SALARY >
        (SELECT AVG(SALARY) FROM EMPLOYEE WHERE DEPT_CODE='D5');
        
        
-- WHERE 절을 생략하고 GROUP BY를 통핸 그룹핑의 대상을 선정하고 HAVING에서 조건을 부여.

SELECT
  EMP_NAME,
  EMP_ID,
  DEPT_CODE,
  SALARY
FROM EMPLOYEE
  WHERE DEPT_CODE IN ('D1' , 'D2') AND
        SALARY >
        (SELECT AVG(SALARY) FROM EMPLOYEE GROUP BY DEPT_CODE HAVING DEPT_CODE='D5');
        

-- Q4. D1과 D2 부서에 근무하는 사람들 중에서 
--     D5 부서 직원들의 평균 급여보다 많이 받는 직원들의 부서번호, 사원번호, 사원명, 급여출력, 부서명까지 출력.

-- *Output : emp_name, salary, emp_id, dept_title , DEPT_CODE               
-- *required table:  employee, department
-- 1. from(table) : employee, department
-- 2. where(if)   : dept_code in ('D1', 'D2');
--                  salary >
--                  급여를 기준으로, subquery에서 (D5부서의 평균)를 비교군으로 return.
-- 3. (subquery)  : (select avg(salary) from employee where dept_code='D5'group by dept_code);                    
--                   **그룹합수인 max()와 min()는 되도록이면 단일 query문으로서 빼주는 것이 좋다.
-- 4. group by    :  
-- 5. having      :
-- 6. order by    : 
-- 7. select      : emp_name, emp_id, salary
 --8. SELECT의 상관 QUERY : (SELECT DETP_TITLE FROM DEPARTMENT WHERE DEPT_ID=DEPT_CODE)
--   (상호연관 단일 Query) 


select 
  (select dept_title from department where dept_id=dept_code),  --부서명 출력을 위한 상관 Query 작성
  emp_name,
  dept_code,
  salary,
  emp_id
from employee                                                                                     -- 상관쿼리는 from이라는 employee라는 for문 내에서
  where dept_code in ('D1', 'D2') AND                                                             -- employee의 dept_code와 상관쿼리인 from의
        SALARY >                                                                                  -- department 테이블의 column인 dept_id와 매핑될 때         
        (SELECT AVG(SALARY) FROM EMPLOYEE GROUP BY DEPT_CODE HAVING DEPT_CODE='D5');              -- department table에서 dept_title column을 return한다.                                                       
                                                               
                                                               
