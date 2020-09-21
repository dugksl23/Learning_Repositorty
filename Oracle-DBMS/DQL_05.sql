

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
 substr(e.emp_no,1,2)>=70 and
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
e.emp_name 직원,
j.job_name 직급,
d.dept_title 부서,
l.local_name 극무지역
     from employee e, job j, department d, location l
        where 
            e.job_code=j.job_code and
            e.dept_code=d.dept_id and
            d.location_id=l.local_code and
            dept_code ='D2';
             
              
              
        



           
              

              
              
              
              
              
