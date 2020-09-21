
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
    
    
-- 9. 직원명과 입사일을 오늘까지의 근무일 수를 조회.

select 
  emp_name 직원명,
  (sysdate-hire_date) 근무일수
  from employee;
  
-- 10. 모든 직원의 나이중 가장 많은 나이와 가장 적은 나이만 출력

select 
 emp_name 직원명,
 120-max(substr(emp_no, 1,2))최연소 and
 120-min(substr(emp_no, 1,2))최장수 and
 from employee;               
                
-- 최연소 32, 최장수 58 출력

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
    
