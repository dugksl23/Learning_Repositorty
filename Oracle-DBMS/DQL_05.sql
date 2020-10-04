

-- join문

-- 일반적으로 table 별로 data가 집합되어져 있다.
-- 하지만, 서로 다른 table에 있는 data를 조합(join)시킬 때,
-- 가상 table을 생성하여 조합을 시킨다.
-- 이것을 join문이라고 한다.



-- 1. corss join
-- 일반적으로 잘 쓰이지는 않는 문법이다.


select 
  e.emp_name 직원명 
  d.dept_title 부서명
  d.dept_code 부서코드
  from employee, department


--> oracle 문법에서는 from 뒤에 새 table명을 추가시켜주면된다.
--> 그러나 이 문법은 총 207개의 table이 생성이 된다.
--> emp_name을 출력하고 department의 첫번째 행부터 마지막 행까지 다 출력을 한다.
--> 즉, 한사람당 부서코드 하나씩 매칭시켜서 recore(행)으로 만들어준다.
--> 이를 for문처럼 반복하며 결국 207개의 table이 생성된다.
--> 그렇기에 cross join은 잘 사용되어지지 않으며,
--> 여기서 매칭시켜줘야 할 data끼리 묶어서 출력할 수 있는
--> inner join 등가조인에 대해서 알아보자.
 
 
 
 -- 2. inner join
 --> inner 조인은 cross join에서 where절이 붙어서 같은 조건끼리만 출력하겠다고 하는 문법이다.
 
 -- ex) employee의 dept_code와 department의 dept_id가 일치할 때까지 
 --     select(for문으)로 출력하겠다.
 
 select 
 e.emp_name 직원명,
 e.dept_code 부서코드,
 d.dept_title 부서명
  from employee e, department d
    where e.dept_code=d.dept_id
       order by 2;



--> 그러나 상기의 query에는 21명밖에 나오지가 않는다.
--> why? employee의 dept_code에는 부서에 소속되지 않아서
--> 그 값이 null로서 표기된 것이 존재하기 때문이다.
--> 따라서 inner가 아니더라도 일치하지 않는 것까지 모두 출력시켜주겠다고 해서
--> outer join이라고 한다.



--- 3. outer join

--> outer join은 null 값 또는 매핑될 inner를 갖지 못하는 table을
--> 출력시켜주기 위한 용도이다
--> 이때 출력시켜줄 table에 (+)를 해주면 된다.

select 
  e.emp_name 직원명,
  e.dept_code 부서코드,
  d.dept_title 부서명,
    from employee e, department d
      where e.dept_code(+)=d.dept_id
          order by 2;
       
       
--> 이로써 해외영업부 3팀, 마케팅부, 국내영업부 모두 출력되었다.
--> 해당 column은 직원이 퇴사하여 정보를 갖지 있지 않지만
--> 출력용도로 채우주었다.


-- 4. labeling을 통한 모호성 회피.
--> java에서 처럼 각 table에 라벨링을 할 수 있따.

select 
  e.emp_name 직원명,
  e.emp_id 직원ID,
  j.job_name 직급명,
  from employee e, job j --> 모호성 회피를 위한 labeing, 보통 해당 table명의 첫알파벳으로 짓는다.
  where e.job_code=j.job_code --> 다른 두개의 table job_code가 있기에 모호성 회피를 위한 라벨링기능 추가.
    order by 3;
----------- [ 연습 문제 ] ---------------------


-- 1. 직원 id 및 직원명과 직급명이 출력.


select 
 e.emp_id 직원ID,
 e.emp_name 직원명,
 j.job_name 직급명
 
 from employee e, job j
  where e.job_code=j.job_code
    order by 3;
 
 
 
 -- 2.depart_title과 national_code를 출력하시오.
 
 
 select 
  e.emp_name 직원명,
  d.dept_title 부서명,
  l.national_code 국가코드
  from employee e, department d, location l
  where 
  e.dept_code=d.dept_id and
  d.location_id=l.local_code
    order by 2;
    
    
  -- 3. 다중 join 연습
  
  -- 01 . 직원별, 근무국가 이름 출력
  
  select 
    e.emp_name 직원명,
    n.national_name 근무국가
    from employee e, national n, location l, department d
      where 
        d.location_id=l.local_code and
        d.dept_id=e.dept_code and
        n.national_code=l.national_code
         order by 2;
    
    
    -- 02. 직원별 이름, 나이, 부서명, 직급명 출력
 select
  e.emp_name 직원명,
  (121-substr(e.emp_no,1,2))나이,
  d.dept_title 부서명,
  j.job_name 직급명
              
  from employee e, department d, job j
    where 
      e.dept_code=d.dept_id and 
      e.job_code=j.job_code
        order by 3,4,2;
   
              
 --- 03. 2025년 12월 25일이 무슨 요일인지 출력.
              
 select to_char(to_date(20251225, 'yyyymmdd'), 'day') from dual;
              
 -- 해당 날짜의 요일을 알 수 없기 때문에
 -- to_date을 통해 해당날짜를 지정하여 날짜 type yyyymmdd로 만들고
 -- to_char는 년월일 시분초를 원하는 형태로 출력이 가능하기에
 -- to_char(to_date(20251225,'yyyymmdd'),'day') from dual;
   
 -- to_char(251225, 'yyyymmdd')는 문자열에서 문자열이기에 불가능하다.
 -- tochar는 문자열과 숫자를 년월일이 아니라, to_date으로 날자를 만들고 해야 한다.             
              
 -- 04. 주민번호가 1970년대생이면서 성별이 여자이며
 --     성이 전씨인 직원들의 사원명, 주민번호, 부서명, 직급명
              
select 
 e.emp_name 직원명,
 substr(e.emp_no,1,2)||'년'년생,
 d.dept_title 부서명,
 j.job_name 직급명
from employee e, department d, job j
where 
 e.dept_code=d.dept_id and
 e.job_code=j.job_code and
 substr(e.emp_no,1,2)=7 and
 substr(e.emp_no,8,1)=2 and
 emp_name like '전%'
   order by 4,3,2;

-- 05. 이름에 '형'자가 드어가는 직원의 사번, 사원명, 부서명 조회.

            
  select 
  e.emp_name 직원명,
  e.emp_id 사번,
  d.dept_title 부서명
  from employee e, department d
      where 
        d.dept_id=e.dept_code and
        emp_name like '%형%'
           order by 2,3;     


-- 06. 해외 영업부에 근무하는 사원명, 직급명, 부서명 조회
 

select 
  e.emp_name 사원명,
  j.job_name 직급명,
  d.dept_title 부서명
    from employee e, department d, job j
       where 
        d.dept_id=e.dept_code and
        j.job_code=e.job_code and
        e.dept_code in ('D5','D6','D7')
          order by 3,2;
              
              
 --  07. 보너스 포인트를 받은 직원들의 사원명, 직급, 부서명, 근무지역명, 보너스 출력
              
select
e.emp_name 사원명,
j.job_name 직급,
d.dept_title 부서,
l.local_name 지역,
nvl(e.bonus, 0) 보너스

        from employee e, job j, department d, location l
            where 
             (e.job_code=j. job_code) and
            (d.LOCATION_ID=l.LOCAL_CODE) and
            (e.dept_code=d.dept_id);
              
              
 -- 08. 부서 코드가 D2인, 사원명, 직급명, 부서명, 근무지역명 조회.

              
  select 
    e.emp_name 직원명,
    j.job_name 직급,
    d.dept_title 부서,
    l.local_name
       from employee e, department d, job j, location l
          where 
             e.dept_code=d.dept_id and
             e.job_code=j.job_code and
             d.location_id=l.local_code and
             e.dept_code='D2'
               ORDER BY 3;

-- 09. 급여 등급 테이블의 최대 급여(MAX_SALARY)에서 -500000보다 많이 받는 직원들의
--     사원명, 직급명, 급여, 연봉 조회(사원테이블과 급여등급 테이블을 SAL_LEVEL 컬럼으로 조인)

select
 e.emp_name 직원,
 j.job_name 직급,
 salary 급여,
 salary*12 연봉
 from employee e, job j, sal_grade s
     where 
       j.job_code=e.job_code and
       s.sal_level=e.sal_level and       
       (s.max_sal)-500000>salary;

              



-- 10. 한국과 일본에 근무하는 직원들의 사원명 부서명 지역명 국가명을 조회하시오.
              
 select
   emp_name,
   dept_title,
   local_name,
   national_code
      from employee e, department, location
         where                       -- 같은 값을 가진 서로 다른 두 테이블의 매칭시켜, 두 테이블 내에서 원하는 값만을 출력. 
           dept_id=dept_code and       -- 매칭된 후에는 원하는 coulmn을 출력 가능.
           location_id=local_code and
           national_code in ('KO', 'JP');   
              
 
              
              
-- 11. 같은 부서에 근무하는 직원들의 사원명, 부서명, 동료이름을 조회
-- 필요값 : 사원명(E1. EMP_NAME), 부서명(DEPT_TITLE), 동료이름(E2.EMP_NAME)
-- 필요 COLUMN : EMPLOYEE, DEPARTMENT, 
-- SELF JOIN
-- 키워드는 같은 부서.
-- 일반적으로는 부서를 기준으로 일하는 EMP_NAME을 출력하라면, 단일 쿼리문으로 가능.
-- 하지만 같은 같은 부서라는 말이 들어가고, 비교 대상이 나와 다른 누군가이기에.
-- E1, E2, DEPARTMENT가 필요.             

 SELECT
    E1.EMP_NAME,
    D.DEPT_TITLE,
    E2.EMP_NAME
FROM EMPLOYEE E1, DEPARTMENT D, EMPLOYEE E2  --DEPT_TITLE을 기준으로
    WHERE                                    -- 동료 1(E1) 동료2(E2)가 함께 일하고 있는가? 
      E1.DEPT_CODE=D.DEPT_ID AND             -- E1에서 부서와 매핑시킨 후 이름을  부서명 출력
      E1.DEPT_CODE=D.DEPT_ID;  
     
              
                  
    
            
              
              
-- 12.  보너스 포인트가 없는 직원중에서 
--       직급이 차장과 사원의 사원명 직급명, 급여 조회. 
-- Output : emp_name, job_name, salary                
-- table :  employee, department
-- 1. from(table) : employee, department 
-- 2. where(if)   : dept_id=dept_code
--                  bonus is null
--                  job_name in ('사원', '차장')  
 --3. group by    :  
 --4. having      :  
 --5. order by    :            
              
           
   select * from job; ---
   select 
   emp_id,
   job_name,
   salary
   
   from employee e, job j
    where 
        bonus is null and
        e.job_code=j.job_code and -- 중복된 값을 가진 컬럼이 있다면, 일치된 각각의 컬럼내에서 
        j.job_name in ('차장', '사원')                  -- select에서 지정된 값들을 출력한다.
        order by 1;
              



-- 13.  재직중인 직원 수와 퇴사한 직원의 수
              
-- Output : count(*)             
-- table :  ent_yn
-- 1. from(table) : employee 
-- 2. where(if)   : decode, case when
--                    
 --3. group by    :  -- 컬럼을 기준으로 그룹핑을 하여 범위를 좁힌다. 컬럼명을 기입하면 된다.
 --4. having      :  
 --5. order by    :                    
              
 select DECODE(SUBSTR(ENT_YN,1,1),'Y','퇴사','N','재직') 재직여부, 
count(*) "인원 수" from employee 
group by ent_yn;
  
                
         DECODE(ENT_YN, 'Y','퇴사', 'N', '재직')      
                
                
                    
              
              
