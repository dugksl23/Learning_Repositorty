


------------------------------------- Chater 1 : subQuery란? -----------------------------------

-- query문 내에 또 다른 query를 추가하는 것을 바로 subQuery라고 한다.
-- JOIN문은 두개의 테이블을 매핑하여 하나의 결과물을 만들어내는 것이라면,
-- subQuery문 또한 두개의 명령어로 하나의 테이블 내에서 검색한 결과를 
-- 다른 테이블에 전달하여 검색하는 것이다. 즉 main query를 subquery와 비교하여 하나의 결과물을 도출해내는 것이다.

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
  where salary =>
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

  where emp_name!='윤은해' and
  salary in
       (select salary from employee where emp_name='윤은해');
       
       
select 
  emp_name,
  emp_id,
  salary
from employee

  where emp_name not like'윤은해' and
  salary =
       (select salary from employee where emp_name='윤은해');       
       
       
-- ** 다행 쿼리문에서는 같다의 의미를 in으로만 표현이 가능하지만,
--    단일 쿼리문은 in, = 둘다 가능하기에 혼동을 피하기 위해서 in으로 통일.


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
 
 
 select      
    (select depT_title from department where dept_code=dept_id),
    emp_name,
    SALARY
 FROM EMPLOYEE
   WHERE DEPT_CODE IN ('D1' , 'D2') AND
               SALARY > (SELECT AVG(SALARY) FROM EMPLOYEE WHERE DEPT_CODE='D5');
 
        
        
        
---------- [ 02. 다중행 subQuery ]----------------------
-- 다중행이란, query 문 내에 조건이 2개 이상 붙었을 경우를 뜻한다.


-- 01. 송종기나 박나라가 /속한 부서에 속한 사람들의 정보.

-- *Output : *               
-- *required table:  EMPLOYEE
-- 1. from(table) : employee
-- 2. where(if)   : dept_code
--                 -- 부서명을 기준으로, subquery에서 (송종기 또는 박나리가 속한 dept_code)를 비교군으로 return.
-- 3. (subquery)  : (select dept_code from department where emp_name='송종기' or emp_name='박나라');                    
--                   
-- 4. group by    :  
-- 5. having      :
-- 6. order by    : 
-- 7. select      : *
 --8. SELECT의 상관 QUERY : 
--   (상호연관 단일 Query) 

select *
from employee
where dept_code in
      (select dept_code from employee where emp_name='송종기' or emp_name='박나라');
                            
-- **부서 정보는 dept_code 또는 dept_title로 해당 정보를 매핑시킬 수가 있다.
--   상기의 문제는 같은 부서라는 전제조건이기에, dept_code만으로도 전제조건을 만족시킬 수가 있다.

               
 SELECT * FROM EMPLOYEE WHERE DEPT_CODE IN
 (SELECT DEPT_CODE FROM EMPLOYEE WHERE EMP_NAME IN ('송종기', '박나라'));    



-- 02. 차태현과 전지연의 급여등급과 같은 사원의 직급코드와 사원명을 출력하시오

-- 차태현, 전지연 사원의 급여등급(sal_level)과 같은 사원의 직급코드 사원명 출력
-- 단, 차태현 전지연은 빼고 출력.


-- *Output : job_code, emp_name              
-- *required table:  EMPLOYEE
-- 1. from(table) : employee
-- 2. where(if)   : sal_level in
--                 -- sal_level을 기준으로, subquery에서 (차태연 또는 전지연의 sal_level)를 비교군으로 return.
-- 3. (subquery)  : (select sal_level from employee where emp_name='차태연' or emp_name='전지연');                    
--                  **두개의 조건을 가진 다중행 커리문이기에 in을 쓴다.) 
-- 4. group by    :  
-- 5. having      :
-- 6. order by    : 
-- 7. select      : job_code, emp_name
 --8. SELECT의 상관 QUERY : 
--   (상호연관 단일 Query) 


select 
    job_code,
    emp_name
from employee
    where sal_level in
          (select sal_level from employee where emp_name in ('차태연','전지연') and
            emp_name!='차태연' and '전지연');
            
 --** 이름이 전지연 또는 차태연이 아닌 사람이며, 이름이 차태연 또는 전지연인 사람의 sal_level
 -- 이라는 구문이 되기에 말이 안되기에, 두사람을 빼는 조건문은 main query에서 수행한다.
            
select 
    job_code,
    emp_name,
    sal_level
from employee
    where emp_name!='차태연' and 
          emp_name!='전지연' and
          sal_level in (select sal_level from employee where emp_name in ('차태연','전지연'));

SELECT EMP_NAME, JOB_CODE
FROM EMPLOYEE
WHERE EMP_NAME NOT IN ('차태현', '전지연')AND SAL_LEVEL IN (SELECT SAL_LEVEL FROM EMPLOYEE WHERE EMP_NAME IN ('차태현', '전지연')) ORDER BY 2
;            

-- 03. 직급이 대표나 부사장이 아닌 /모든 사원의 이름 부서명 직급코드 출력

-- *Output : job_code, emp_name, dept_title              
-- *required table:  EMPLOYEE, department
-- 1. from(table) : employee, department
-- 2. where(if)   :  dept_id=dept_code and job_code
                                    

                   -- 먼저 모든 사원의 이름과 부서명 직급코드를 출력하는 것이기에
                   -- 1차적으로 main query 내에서 매핑을 시킨다.                                                       
                   -- 그다음, employee의 job_code을 기준으로, 
                   -- subquery에서 (직급이 대표나 부사장이 아닌) 비교군으로 return.
                   -- 직급코드를 기점으로 main 쿼리와 subQuery 매핑.                                                       
-- 3. (subquery)  : (select job_code from department, job where job_name not in'('대표','부사장');                    
--                  **두개의 조건을 가진 다중행 커리문이기에 in을 쓴다.) 
-- 4. group by    :  
-- 5. having      :
-- 6. order by    : 
-- 7. select      : job_code, emp_name, dept_title
 --8. SELECT의 상관 QUERY : 
--   (상호연관 단일 Query) 
                                                                          

select 
    emp_name,
    dept_code, 
    dept_title
from employee, department
    where dept_code=dept_id and 
          job_code in (select job_code from job where job_code not in ('부사장','대표'));
                                                               
                                                                       
---------- [ 03. 다중열의 All 과 any == and 와 or ]----------------------                                                                      
 -- in이 == 같다라는 의미를 갖는다면
 -- All 은 A AND B A이며 B인 조건 구문이며,
 -- ANY 는 A OR B A 또는 B인 조건 구문의 SUBQUERY의 제일 앞에 쓰이는 구문이며, 
 -- 이때는 다중행 쿼리문에서 MAIN QUERY의 WHERE 문의 비교구문으로 대상 COLUMN에도>/<를 사용가능.      
 -- AND = BETWEEN A AND B = (QUERY 문 비교시) ><= ALL (2가지의 RETURN값을 가짐.)
 -- OR = IN ('A', 'B') = ><= or (2가지 reutnr 값 가짐)                                                                       
                                                                       
                                                                      
select
    emp_name,
    salary
from employee
    where salary > any (3000000, 5000000);

-- 상기의 코드를 굳이 일반 query문으로 옮기자면,

select 
   emp_name,
   salary
from employee
    where salary >=3000000 or
           salary <=5000000
                                                 
                                                                       
-- ** 단일 subQuery의 return table로는 크다 작다를 비교할 수 있다.
--    하지만 다중 subQuery 같은 경우는 이제까지는, subQuery의 조건구문을 충족하는 subQuery의 return table과
--    main query의 비교 table이 같다라는 전제 즉, in 또는 = 또는 단일문 쿼리의 number형 또는 문자형에 관해서만 크다 작다로만 진행이 되었다.
--    그렇기에 단일 query가 아닌, 다중행 또는 query에서도 크거나 작나 라는 표시를 위해서 any 또는 All을 사용하게 해준 것이다
--     체크 포인트.** 
                                                                       
select emp_name, salary
from employee where salary>
(select salary from employee where salary=3000000 or salary= 5000000);
-- 일반적으로 query문 내에서 number를 통한 다중행(2중 조건문 where 내의 in이나 or 이나 and)나                                                                      
-- 일반 char 형에서의 in 과 or을 통한 다행중 query는 ~이거나 ~일때를 기준에서 같을 때만 비교 대상으로 작용한다.
-- 즉 in 이라면 가능하다..

where job_code in job_code in (select job_code from job where job_code not in ('부사장','대표'));
where job_code in sal_level in (select sal_level from employee where emp_name in ('차태연','전지연'));
where dept_code in (select dept_code from employee where emp_name='송종기' or emp_name='박나라');
where salary in (select salary from employee where salary=3000000 or salary= 5000000);

-- 다만, 예외적으로 단일 query where절이 붙지 않는 비교구분으로는 가능.
salary >(select avg(salary) from employee);                                                                                
salary > (select salary from employee where salary=3500000);                                                                                  


-- 따라서 subquery 이중 조건문에서도 비교를 가능하게 하려면  가능하게 하는 것이 바로 
-- ALL과 ANY이다. 사용법을 아래 예시를 통해서 알아보자.
                                                                                  
                                                                                  
                                                                                  
                                                                                  
-- Q1. D1 또는 D5 부서의 사원들의 급여중에서
--     EMPLOYEE TABLE의 SALARY보다 / 적은 급여를 가진 사원, 부서코드, 급여를 모두 출력하시오.
                                                                   

select emp_name, SALARY, DEPT_CODE
    from employee
        where dept_code in('D1', 'D5') AND
        salary < ANY (select salary from employee);

-- 또는 이기에 ANY이다.
-- single-row subquery returns more than one row
-- 즉 다중행에 서브쿼리에서 2개 이상의 데이타를 반환해서 발생했기에 불가능하다는 것이다.
-- 이를 해결하기 위해서는 쓰는 것이 all 또는 any이다.
                                                                                          
--** ANY는 subQuery의 where절의 비교구문에 a or b
--   and는 subQuery의 where절의 비교구문에 a and b                                                                       
                                                                          
                                                       
                                                                                 
-- Q2. 부서별 평균 급여 중 /가장 낮은 부서의 급여보다/ - subquery
--     크거나 같은 급여를 가진 사원의 이름, 급여, 부서명 출력 - /main

-- *Output : salary, emp_name, dept_title              
-- *required table:  EMPLOYEE, department
-- 1. from(table) : employee, department
-- 2. where(if)   :  dept_code, salary, 
--                                  
                   -- 먼저 모든 사원의 이름과 부서명 직급코드를 출력하는 것이기에
                   -- 1차적으로 main query 내에서 매핑을 시킨다.                                                       
                   -- 그다음, employee의 salary와 dept_code를 기준으로, 
                   -- subquery에서 (AVG(salary)를 부서별로 그룹핑하는 것을) 비교군으로 salary를 return.
                   -- 직급코드를 기점으로 main 쿼리와 subQuery 매핑.                                                       
-- 3. (subquery)  : (select avg(salary) from employee);                    
--                  
-- 4. group by    :  (broup by dept_code)
-- 5. having      :   
-- 6. order by    : 
-- 7. select      : salary, emp_name, dept_title
 --8. SELECT의 상관 QUERY : 
--   (상호연관 단일 Query) 
                                                                     

select 
    emp_name,
    salary,
    dept_title
from employee, department
    where dept_code=dept_id and
    salary < any (select min(salary) from employee group by dept_code); 
                                                                                  

--**직급별 최소값, 직급별 최대값, 부서별 평균 연봉값을 비교군으로 둘때 크다 작다라면
-- where 절에서 dept_code=dept_id를 묶고 그 다음에 부서별 평균 급여를 서브쿼리로 두면 된다.                                                                                  
-- 하지만 in 일 때에는 다중행 다중열 query로서 인자가 2개가 된다..

                                                                                  
                                                                                  
                                                                                  
                                                                                  
                                                                                  
-- Q3. D2부서의 모든 사람들보다 /작은 급여를 받는 사람을 출력하세요./ - subquery

-- *Output : salary, emp_name              
-- *required table: EMPLOYEE
-- 1. from(table) : employee
-- 2. where(if)   : salary < any               
                   -- employee의 salary을 기준으로, 
                   -- subquery에서 (mini(salary)를 부서별로 그룹핑하는 것을) 비교군으로 salary를 return.
                                                                  
-- 3. (subquery)  : (select salary from employee);                    
--                  
-- 4. group by    :  (Group by dept_code)
-- 5. having      :   
-- 6. order by    : 
-- 7. select      : salary, emp_name
 --8. SELECT의 상관 QUERY : 
--   (상호연관 단일 Query)                                                                     
                                                                     

select
 emp_name,
 salary
from employee
 where salary < ANY (select salary from employee where dept_code='D2');
-- 즉 모든 사람들이기에 한사람 별로 작은 사람들을 모두 출력한다. 그렇기에 ALL 이 아닌 ANY(개별로)                                

                                                                     
--** 다중행 subQUERY(WHERE 절에 2중 조건문이 있는 subQuery문)은 크다 작다를 all any를 붙여줘야지만 가능하다.
--    단일행 쿼리 및 노멀 query문에서는 크다 작다 같다 비교가 모두 가능하지만,
--     서브쿼리의 다중행 쿼리에서는 여러조건들을 가지기에 비교할 때에는 필수적으로 />= all/ <= any /in을 쓴다.
                                                                     
 

--------------------------- [ 04. 다중행&다중열 subQuery ] --------------------------------------
      
-- 다중행, 다중열은 where 절에서 비교를 할 데이블도 두개이다.
-- 따라서 main query의 비교대상이 될 where 절의 table도 동일해야 한다.

-- 01. 기술지원부이면서 급여가 200만원인 직원 이름, 부서코드, 급여 출력
 -- 기술지원부 이면서 급여 = dept_code=dept_id, salary   

SELECT   
    EMP_NAME,
    SALARY,
    DEPT_CODE  
from employee
    where salary=2000000 and dept_code in (select dept_id from department where dept_title='기술지원부');
 
                                                                                  
                                              
   SELECT
    EMP_NAME,
    DEPT_CODE,
    SALARY
 FROM EMPLOYEE   
  WHERE (DEPT_CODE, SALARY) IN (SELECT DEPT_ID, SALARY FROM DEPARTMENT, EMPLOYEE WHERE SALARY=2000000 AND DEPT_TITLE='기술지원부');
                                              
                                                                                  
                                                                                  
                                                                     
-- 02. 직급별로 가장 낮은 /급여를 받는 직원의 이름, 사번, 부서 코드, 입사일과 연봉.
-- 직급별 가장 낮은 급여 -- group by job_code


select
  emp_name,
  dept_code,
  emp_id,
  salary*12,
  hire_date 
from employee 
    where salary in
          (select min(salary) from employee group by job_code);

-- ** 문제점
-- 직급별로 가장 낮은 급여를 받는 사람이란 직급별로 한사람씩이라는 건데... 상기의 코드로는 윤은혜가 출력됨.                                                                     
-- 단순한 group by를 통한 정렬은 중복을 제거해도... 잡코드로 그룹핑한 후에 최소값이랑 비교해서
-- 같은 값이기에 결국 그 값과 같은 모든 사람을 출력하는 구문이라서 틀렸다.
-- 따라서 직급별, 가장 낮은 급여를 따로 매핑해줘야 한다.
                                                                     
                                                                     
     select 
        emp_name,
        emp_id,
        salary,
        salary*12,
        to_char(hire_date, 'yyyy"년"mm"월"dd"일"')
      from employee  
        where (salary, job_code) in (select min(salary), job_code from employee group by job_code);
                                                                     


-- 03. 기술 지원부에 속한 사람 중 연봉이 가장 높은 사람의 이름 부서코드 급여 출력.

-- *Output : salary, emp_name, dept_code              
-- *required table: EMPLOYEE
-- 1. from(table) : employee
-- 2. where(if)   : salary in              
                   -- employee의 salary을 기준으로, 
                   -- subquery에서 (mix(salary)를 부서별로 그룹핑하는 것을) 비교군으로 salary를 return.
                                                                  
-- 3. (subquery)  : (select salary from employee, department where dept_code=dept_id and dept_title='기술지원부');                    
--                  
-- 4. group by    :  (Group by dept_code)
-- 5. having      :   
-- 6. order by    : 
-- 7. select      : salary, emp_name
 --8. SELECT의 상관 QUERY : 
--   (상호연관 단일 Query)                                                                        
 
select     
   emp_name,
   dept_code,
   salary
from employee
   where salary in (select max(salary) from employee, department where dept_code=dept_id and dept_title='기술지원부');
                                                                          
 --- 다중행 다중열 쿼리                                                                    

 select 
    emp_name,
    salary,
    dept_code
from employee 
    where (salary, dept_code) in (select max(salary), dept_code from employee, department where dept_code=dept_id group by dept_code, dept_title having dept_title='기술지원부');
                                                                                                           
 --- 4. 매니저가 있는 직원중 급여가 전체사원 급여 평균을 넘는 직원의 사번, 이름, 매니저 이름,
--      급여(만원단위)를 출력.
                                                                     
  

  select
    e1.emp_name,
    e1.emp_id,
    e2.emp_name,
    to_char(e1.salary*12, 'l999,999,999')
from employee e1, employee e2
    where e1.manager_id is not null and  
                e1.manager_id=e2.emp_id and
                e1.salary > (select avg(salary) from employee);                                                                  
                                                                     
                                                                     
 --  Q5. 각 직급별/ 급여 등급이 가장 높은 직원의 이름, 직급 코드, 급여등급.

 select 
    emp_name"dlfma",                                                                     
    job_code"직급코드", 
    sal_level"급여등급",
    ltrim(to_char(salary, 'l999,999,999'), ' ')"급여"
from employee
    where( sal_level, job_code) in (select min(sal_level), job_code from employee group by job_code);

 --  Q6.  부서별 평균 급여가 220만원 이상인 부서명, 평균급여를 출력해주세요.

select
    dept_title"부서",
    ltrim(to_char(floor(avg(salary)),'l999,999,999'), ' ')"급여"
from employee, department
    where  dept_id=dept_code                                                    
        group by dept_title
         having avg(salary)>2200000
          order by 2;
                     
                                                                     
                                                                     
   --------------------------------- [ 05 순위 매기는 함수 ] ----------------------------------
                                                                     
                                                                     
  ---- 1. ranl() over()

-- over의 소괄호에 누굴 기준으로 정렬할지를 정한다.
-- 단 동점인 경우에는 한 랭크를건너 뛰고 정렬하며
-- 다음 순위를 건너띄기도 한다.
-- 즉 21위가 두명, 다음 순위는 23위가 된다.

select rank() over(order by salary desc)순위, emp_name, salary from employee;
-- 좌측에 순위가 1등부터 정렬되어서 매겨진다.


-- 02. 동점 순위(21ㅇ 만들지 않는 방식.
--     동점 순위를 20위가 2명, 그다음은 21위로 바로 이어진다.

select dense_rank()over(order by salary desc)순위, emp_name, salary from employee;


-- 03. row number() over()
-- 행마다 고유한 번호를 가지며, 임의에 번호 및 순위를 매기려고 할 때 사용된다.
-- 사용빈도는 매우 높기에 외워두자!
                                                                     

select row number() over(order by salary desc)순위, emp_name, salary from employee;




--------------------------[ column들을 담아두는 임시 테이블 wuth temp ] -----

with temp as ( select emp_id, emp_name, emp_no from employee)
select emp_id from temp;
                                                                     
                                                                     
                                                                     
                                                                     
                                                                     
                                                                     
                                                                     
                                                                     
                                                                     
                                                                     
                                                                     
                                                                     
                                                                   
                                                                          
