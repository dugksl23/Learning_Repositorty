
---------------[날짜 관련함수] ----------------


--*** 1. sysdate : 시스템에 설정된 시간을 "년/월/일"로 현재시간을 표시해준다.
--    current_date : 현재 속한 국가의 시간대를"년/월일"로 현재시간을 표시해준다.

select sysdate from dual;
select current_date from dual;


-------------------------------------------------------------------------
-------------------------------------------------------------------------

-- 2. to_date 

-- 1. [ to_timestamp(DATE TYPE) ] : 초단위 이하의 밀리세컨드까지 처리를 한다.

select to_timestamp(sysdate) from dual;

--20/09/19/00:00:00.0000000000, 9자리까지 처리.


-- 2. [ months_between(A, B)] : p1의 인자를 기점으로 p2의 날자까지의 개월수를 반환한다. 
-- return 값은 개월수
-- 년차를 구할 때 많이 쓴다.
-- p1의 날짜 date(시간)까지.
-- p2 인자의 시간부터

-- months_between에 년원일로 입력하면 소숫점 발생.
select emp_name"직원명", months_between(sysdate, hire_date)"개월수" from employee; -- 367.123123

-- 소숫점으로 인해서 floor로 소숫점 버림.(나눗셈이 있기에)

select emp_name"직원명", floor(months_between(sysdate, hire_date))"개월수" from employee; -- 367.123123

-- 이 개월수를 다시 12개월(1년치)로 환산.
select emp_name"직원명", floor(months_between(sysdate,hire_date)/12)"년차" from employee order by 1 asc;

                                           
                                           
-- 3. [ add.months(A, B)]
-- A를 기준으로, B의 숫자(개월수)를 입력하면, B를 더한 미래의 개월수를 RETURN한다.
                                           
                                           
                                           
                                           
                                           
                                           
                                           
