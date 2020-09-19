-- Dql_02_기초문법

--- [ 문법 ] --- 


--[ 1. order by 숫자 column(data type : 숫자) asc, desc ] : 오름차순, 내림차순

-- 01. 급여 순으로 오름차순

select emp_name"직원명", salary"급여" from employee order by salary asc;
-- order by 문법에서 asc는 디폴트값이다.

-- 02. 급여 순으로 내림차순.

select emp_name"직원명", salary"급여" from employee order by salary desc;
