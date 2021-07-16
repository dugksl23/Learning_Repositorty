# Google-Java-Style-Guide


## made by 김 요한 <br>

# 목차
#### 1. 개요
#### 2. 소스 파일 기본 사항
#### 3. 소스 파일 구조  
#### 4. 포멧팅
#### 5. 네이밍
#### 6. 프로그래밍 관례
#### 7. JavaDoc


---


## 1. 개요

이 문서는 Java 프로그래밍 소스 코드에 대한 Google Style로 규정한 코딩 컨벤션이다. <br>
Java 소스 파일은 여기에 있는 규칙을 준수하는 경우에만 Google style로 프로그래밍 된 것이라고 규정할 수 있다.<br>

이외에도 코딩 규약은 여러 종류가 있지만, Java의 경우에는 Google의 코딩 규약을 많이 사용한다. 
<br>
<br>

## 2. 소스 파일 기본 사항

 - 소스 파일 이름(확장자는 .java로 지정.)이 포함된 최상위 클래스를 대소문자 구별을 통해 작성하며, 첫글자는 대문자로 한다.
 - 파일의 인코딩 방식은 UTF-8로 인코딩 한다. 
   ex) IJ
 
   ![image](https://user-images.githubusercontent.com/68539491/125384746-57c48180-e3d4-11eb-9d9a-931d501751e7.png)

 
 
 - 공백 문자는 소스 파일에서 아무 곳에서나 사용 가능한 유일한 문자입니다. <br>
   ex) 0x20(32) 스페이스 키(공백) -> ASCII CODE 에서 유일한 공백 문자이다. <br>
       0x00(0) NUL (NULL Character) -> 널 문자는 0으로 대입이 된다. 
       
 - 코드 내에서 특수 문자를 사용할 때, 이스케이프 시퀀스를 사용한다. <br> 
 
   <img width="670" alt="스크린샷 2021-07-14 오후 4 10 39" src="https://user-images.githubusercontent.com/68539491/125578960-c1d6f62b-8d5a-420d-8286-de98879719d7.png"> 
<br>
<br>

## 3. 소스 파일의 구조

소스 파일 구조 또한 코딩 규칙(convention)이라고 볼 수 있다. <br>
   ![스크린샷 2021-07-13 오전 11 04 54](https://user-images.githubusercontent.com/68539491/125378687-2b0b6c80-e3ca-11eb-8c25-8ee1f7a3fb34.png)


 ```
 /*
  * Copyright (c) 1997, 2017, Oracle and/or its affiliates. All rights reserved.
  * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
  *
  */

 package java.util;

 import java.util.function.Consumer;
 import java.util.function.Predicate;
 import java.util.function.UnaryOperator;
 import sun.misc.SharedSecrets;

 public class ArrayList<E> extends AbstractList<E>
         implements List<E>, RandomAccess, Cloneable, java.io.Serializable
 {
     private static final long serialVersionUID = 8683452581122892189L;

     ...
 }

 ```

 - Package 문의 경우에는 되도록 한문장으로 써야 한다.
 - import 문에서 와일드 카드 ( * ) 는 사용하지 않는다.
   
   ```
   java.util.*;
   ```
   

 <br>
 
 - import 문은 그룹핑을 통해 순서에 맞춰 작성한다.<br>
   ex) static import -> google 내부 package import -> google 외부 package import -> java -> javax
 
   ![스크린샷 2021-07-13 오전 11 11 19](https://user-images.githubusercontent.com/68539491/125379194-0f549600-e3cb-11eb-8107-41af82a0a27b.png)
   
<br>

 - 하나의 소스 파일(.java)에는 하나의 class만 존재해야 하며, nested class는 존재해선 안 된다.<br>(*class 내의 또 다른 class 선언)
 - 클래스 내용의 순서 
    * 클래스 멤버의 순서는 절대적인 것이 없다. 하지만, 순서가 논리적이여야 한다. <br>
      ex) 새 메소드가 추가되었다고 하여, class의 가장 마지막에 구현되는 것은 논리적이지 않다.
    * class 생성자의 오버로딩을 통해 여러 개가 존재할 경우, 이들은 순차적으로 작성되어야만 한다. 중간에 다른 멤버를 작성할 순 없다.
    <br>
    
    ```


    public class JoinMemberDto {

        int idx;
        String memberName;
        String password;
        int address;
        int tel;

        public JoinMemberDto() {
        }

        public JoinMemberDto(int idx, String memberName, String password, int address, int tel) {
            this.idx = idx;
            this.memberName = memberName;
            this.password = password;
            this.address = address;
            this.tel = tel;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getAddress() {
            return address;
        }

        public void setAddress(int address) {
            this.address = address;
        }

        public int getTel() {
            return tel;
        }

        public void setTel(int tel) {
            this.tel = tel;
        }
    }

    
    ```
    
<br>
    
    
## 4. 포멧팅

 - **중괄호**

   Google Java Style은 K&R Style을 따른다.<br>
   K&R Style이란? Kernight and Ritchie Style의 약자로서 아래와 같은 규칙을 가진다.
   
   - 여는 괄호 앞에는 줄 바꿈이 없다.
   - 여는 괄호 뒤에는 줄 바꿈이 있다.
   - 닫는 괄호 앞뒤로 줄 바꿈이 있다.
   - 중괄호 제거가 가능해도 사용하는 것을 권장한다.

   <br>
   
     ```
      public class Calculation {

        public staic void main(String[] args) {

          int result = calcurator("+", 3, 4);

        }

        public static int calculator(String operator, int a, int b) {

           if (operator == "+") {
             return sum(a, b);
           }

        }

        public static int sum (int a, int b) {

           return a + b;

         }

      }

     ```
 
 - **줄 바꿈 없는 방식 (권장하지 않음)**
   중괄호 사이에 문자가 없다면 문제가 되지 않난다.
   ```
     public static void main(String[] args) {sum(3,4)} // 불가능
     public static void main(String[] args){}         // 가능
     
   ```
   
 - **괄호 생략한 방식**
 
   ```
    public static int calculator(String operator, int left, int right) {
       
       if(operator == "+") return sum(left, right);
       else return -1;
       
    } 
    
    public static int sum (int left, int right) {
       return left + right;
    }
    
    public static void main(String[] args) {
            
         int num = calculator("+", 3, 4);
    }
    
   ```
   <br>
     
   
 - **수평 공백** 
    
    - if, for catch 등의 키워드 다음에 오는 여는 "(" 사이에 공백문자.
    - 닫히는 ")" 뒤에 오는 {} 사이에도 공백문자.
    - else, try 다음에 오는 {} 사이에도 공백문자.
    - "," / ":" / ";" or 타입 캐스트 시의 ")" 다음에도 공백 문자. 
    - 연산자의 앞뒤로도 공백문자
    <br>
    
     ``` 
       if (true) {
          // ...         
       }

       float num = a + i;

       if (booleanTest) { 
          // ...
       } else {
          // ...
       }

       for (int i = 0; i < 10; i++) {
         // ...
       }


       List<Car> cars = Stream.of(new Car(1), new Car(2)).collect(Collectors.toList());
       for (Car car : cars) {
           System.out.println(car.getNumber())
       }

       (Integer) num; 

       try {
          calculator("+", 3, 4);
       } catch (Error e) {
          e.printStackTrace();
       }

     ```
<br>

 - **수평 공백이 허용되는 예외**
    
    - @PostMapping({"/api/register/userInfo"})
    - 람다식 객체 및 메소드 참조 표현식 "::" <br>

      ```
      var arr = new int[](1, 2, 3, 4, 5);
      arr.stream()
             .forEach(System.out::println);

      Car[] cars = Sttream.of(car1, car2).toArray(Car[]::new);
      List<Integer> carsIdx =  cars.stream()
                                      .mapToObj(Car::number)
                                      .collect(Collectors.toList());
      ```
    
    <br>
    
    - 배열 내의 배열

      ```
      var arr3 = new String[][]{{"1","2"}};
 
      ```

    <br>
    
   
 - **수직 공백 (줄 바꿈 공백)**

    - 수직 공백은 한 줄 공백이라고 한다.
    - 필드, 생성자, 중첩 메소드. 클래스 사이
    - 여러 줄의 빈 줄은 허용하지만, 추천하지 않는다.
  
  <br>
 
 - **변수 수평 정렬**
    - 변수의 수평 정렬은, 변수를 수평으로 정렬하는 것을 가리킨다.
    - 변수 수평 정렬은 Google Java Style에서는 권장하지 않다.

      ```
       private int a; int b; int c; int d; // 옳지 않다.
       private int  a; // 허용되나, 권장하지 않음. 2번의 문자 공백.

      ```
 
 <br>

 - **그룹핑 (추천)**
 
    - 코드 작성자와 리뷰어가 상호 간의 동의가 있을 때 사용한다.
    - 선택적 그룹핑을 통해서 가독성을 높힐 수 있다.    
    
      ```
        private class MyClass {

          // 그룹화
          {
            a += 5;
            a -= 5;
          }

          {
            b += 62;
            b -= 3;
          }

        }
      ```
      
     <br>
     
  - **특수한 구조**

    - **Enum class**
     
       - 상수들의 집합체 or 상수들의 열거형.
       - 각 Enum 상수는 "," 이후에 열거 또는 줄 바꿈을 허용한다.
       - 상수를 나타날 때에는 대문자를 사용한다.
       - Enum은 final static 예약어가 생략되어져 있다. same as interface
       - 고정된 상수들의 집합이기에 외부 접근을 제한하기 위해서 생성자는 private 속성이 default 이다. 
       - enum의 열거형 상수는 특정 값을 명시할 수 있다. 다만, 이 경우에는 private Enum filed와 생성자를 작성해주어야 한다. public getter 함수를 통해 해당 속성을 사용할 수 있다.
       
       <br>

         ```

         public enum DayTest {

             MONDAY("월요일"), TUESDAY("화요일"), WEDNESDAY("수요일"), THURSDAY("목요일"), FRIDAY("금요일"), SATURDAY("목요일"), SUNDAY("일요일");
             SOMETHING{
                @Override
                public String toString() {
                   return "SOMETHING";
                }
             }

             private String krName;

             DayTest(String days) {
                 krName = days;
             }

             public String getKrName(){
                 return this.krName;
             }

         }

         private class EnumExample {

           private final static String MONDAY = "월요일";
           private final static String TUSEDAY = "화요일";
           private final static String WENDSDAY = "수요일";
           private final static String THURSDAY = "목요일";
           private final static String FRIDAY = "금요일";
           private final static String SATURDAY = "토요일";
           private final static String SUNDAY = "일요일";

         }



          class enumExample {

              public static void main(String[] args) {

                  DayTest day = DayTest.SOMETHING;
                  switch (day) {
                      case SOMETHING:
                          System.out.println("SOMETHING");

                  }

                  DayTest day2 = DayTest.MONDAY;
                  System.out.println(day.getKrName());
              }

          }

          ```
      
     
     <br>

       
    - **배열**
       <br>

     
       - 열거 및 줄 바꿈 가능.
       - 쉼표 이후에 공백 문자.
       
       <br>


          ```
          new int[] {
              1, 2, 3, 4, 5, 6
          }

          new int[] {
               1,
               2,
               3,
               4,
               5
          }

          new int[]{
            0, 1,
            2, 3
          }

          new int[]
               {0, 1, 2, 3, 4}

          ``` 

       - 대괄호는 타입 에 붙인다.


         ```
          String[] arg; // 허용
          String arg[]; // 불허

         ```
    <br>  

 - **Swtich 구문**
  
   - Google Java Style guide에서는 스페이스 +2의 공백 문자를 들여쓰기의 기준으로 설정하고 있다.
   - switch 구문의 default 구문은 해당 코드가 없더라도 넣어야 한다.
   
       <br>
  
     ```
       int num = 1;
       switch (1) {
         case 1:
           System.out.println(1+"입니다.");
         default:   
       }

     ```
   
    <br>
    
 - **Annotation 어노테이션**
 
   - 어노테이션은 Documentation block 이후의 클래스, 메소드, 생성자에 바로 적용이 된다.
   - 각각의 어노테이션은 한 줄에 하나씩 쓴다.

     ```
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long idx;
     ```
     <br>

     - 예외적으로, 매개변수가 없는 어노테이션은 열거형으로 작성 가능.

     ```
     @Nullable public String getName(){ 
         //... 
     }
     ```
   
   
   <br>


   - Documentation block 바로 다음 필드에도 어노테이션이 적용될 수 있다.<br>
     이 경우에는 어노테이션을 매개변수에 한줄로 사용 가능하다.
     
     ```
     @PostMapping({value = '/api/register/Member'})
     public void registerMember(@valid @ReqeustBody MemberDto memberDto) {

        memberService.registerMember(memberDto.toEntity());

        return new ResponseEntity<>(HttpStatus.OK);

     }
     ```
    <br>
    
    - **블럭 주석**
  
      - 블럭 주석은 컴파일 시 해당 코드 라인들을 무시한다.
      - 주석의 종류
        1) 한 줄 주석 : //
        2) 여러줄 주석 : /* ~~~ */
        3) 문서 주석 : /** 문서 내용 **/


  <br>  
   
  - **수정자(접근제한자)**
  
    - 클래스와 메소드 및 필드의 제한자는 자바 언어 명세서에 권장되는 포멧팅(예약어)를 따른다.
    - 한 줄에 열거형으로 사용할 경우에는 아래와 같은 순서를 따른다.
    
      ```
       public protected private abstract default final static stransient volatile synchronized natice strictfp
      ```
    <br>
    
  - **숫자형 리터럴**

    - long 형과 float형 변수는 대문자 L과 F를 접미어로 사용하여, 숫자의 자릿수를 혼동되지 않게끔 한다.
    - 접미어로 소문자는 사용하지 않는다.


      ```
        long num = 300000000l (x)
        long num2 = 300000000L (o)

      ```      
    
 <br>  
 

 ## 5. 네이밍


 - **패키지 이름**
   
   패키지 이름은 모두 소문자로 이루어진다.
   snake case를 사용하지 않으며, 단순히 단어들이 연속으로 쓰여진 형태이다.
   
   ```
   com.example.googlestyle
   
   ```
 <br>
  
 - **클래스 및 인터페이스 이름**
   
   클래스 이름은 UpperCamelCase로 작성이 되며, 명사구로 명명되어져야 한다.
   
   ```
   Character, DataList, Controller
   ```
 
 
   그러나 간혹 형용사 또는 형용사구로 대신할 수 있다.
   
   ```
   Actable, Immutable
   ```
   
  
   JUnit을 통한 클래스(Entity)를 테스트를 하는 경우에는 클래스의 이름이 앞에 오고, 뒤에는 Test로 마무리를 한다.
   
   ```
   RegistrationTest
   SignOutTest
   
   ```

<br>
  
 - **메소드 이름**
   
   메소드의 이름은 lowerCarmelCase로 작성이 되며, 동사 또는 동사구로 명명 되어져야 한다.
   
   ```
   signupMember
   sendMessage
   ```
   
   <br>
   
   JUnit을 통해 메소드를 테스트하는 경우에는 lowerCase로 작성이 되며, 동사구 또는 동사로 명명 되어져야 한다.
   또한 테스트의 결과값을 예측하고 테스트를 하는 패턴을 가지고 있다.<br>
   ex) <methodTest>_<state> -> signupMember_empty
   
   "테스트할 메소드 이름 + Test" + "_" + "예상 결과"로 작성하면 된다.
   이는 해당 테스트 코드의 결과값이 empty임을 예상하고 진행하는 것이기에 문제가 되지 않는다.
 
   ```
   signupMemberTest
   sendMessageTest
   signoutMemberTest
   signoutMember_empty
   ```
 
 <br>
   
 - **상수 Constant**
   
   상수는 Constant_case를 사용한다. 모두 대문자를 작성되며, 단어 사이에 밑줄을 표시하여 명사구 또는 명사로 명명 되어져야 한다.
   상수는 static final 키워드로 정의된 필드이며, 이 필드에 정의된 내용은 불변해야 한다.

   ```
   static final int MONDAY = 1;
   static final int TUESDAY = 2;
   static final int WEDESDAY = 3;
   static final int THURSDAY = 4;
   static final int FRIDAY = 5;
   static final int SATURDAY = 6;
   static final int SUNDAY = 7;
 
   static final int SUBTRACT_INT_VALUE = 1;
   static final int FIRST_INT_VALUE = 2;
   static final int SECOND_INT_VALUE = 3;

   ```
 
<br>

 
 - **class의 멤버변수 Field**
  
   상수가 아닌 class의 field는 lowerCamel case로 작성된다.
 
   ```
   int idx;
   String userName;
   int userPassword;
   int address;
 
   ```
 
 <br>
 
 
 - **매개변수 Parameter**
  
   매개변수의 이름은 lowerCase로 작성이 된다.<br>
   public 메소드에서 한 문자로 된 파라미터 네이밍은 피해야 한다.
 
   ```
   public static void method(int args){
     // 가능
   }
 
   public static void method(int a){
     // 불가능
   }
 
  
 
   ```
 
 <br>
 
 
 - **지역변수 Local variable**
  
   지역변수의 이름은 lowerCase로 작성이 된다.<vbr>
   상수처럼 사용해선 안 되기에 상수 스타일로 네이밍이 되어져선 안 된다.
  
   ```
   public static void main(String[] args){
     
      int idx;
      // ...
 
   }
 
   ```
 <br>
 
 
 - **Camel case**
   
   Java에서 대체적으로 사용되는 coding convention은 Camel case이다.
   Camel case는 첫글자를 소문자로 시작하며, 합성어의 첫글자만 대문자로 시작하는 규칙을 가지고 있다.
   이러한 모양이 마치 낙타와 비슷하다고 하여 카멜 케이스라고 불리고 있다. 단, 패키지, 클래스, 인터페이스, enum과 같은 명명 규칙에는 예외가 된다.
 
 
   ```
   int memberName;
 
   public void fetchUserDetail(){
     //...
   }

   ```

 <br>
 

 
 ## 6. 프로그래밍 관례
 
 
 - **@override 항상 명시**
 
   @Override 어노테이션을 사용할 수 있는 메소드는 반드시 붙인다. 이는 Super class와 상속관계를 갖는 ChildClass에서 Super class의 메소드를 오버라이딩 하고 있음을 나타내는 것이다.
  (*abstract class포함)<br>
   또는 class의 메소드가 interface의 메소드를 구현하고 있음을 나타내고 있거나, interface 메소드가 Super interface 메소드를 정의하고 있음을 표현하는 것이기도 하다.
  
  <br>
  
 - **throws와 try-catch** 
 
   throws(예외)는 내부적으로 소스 코드를 컴파일 하는 과정에서는 발견하지 못하며, 외부 접근에 의해 프로그램 실행 중에 예상치 못하게 발생하는 오류 사항들을 예외라고 부른다.
   이러한 예외들을 throws를 사용해서 해당 메소드를 사용하는 사용자들에게 예외를 전가시키고, try-cath(예외 처리)를 통해서 Exception의 종류를 보여줌으로서 예상되는 오류를 명시해 주는 것이다.<br>
   ex) NullPointException, IllegalArgumentException
 
   ```
   try {
     //... 예외가 발생할 가능성이 있는 코드 작성.
   } catch (Exception e) {
     e.printStackTrace();
     //... 예외 처리 코드 작성
   }
 
 
   int num1 = 100;
   int num12 = 0;
 
   try {
     int result = num1 / num2;
   } catch (Exception e) {
     e.printStackTrace();
     throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
   }
 
   ```
 <br>
 
 
 - **static 정적 변수 및 정적 메소드**
   
   일반적으로 변수 또는 메소드는 객체를 생성하지 않는다면 사용할 수 없다. 즉, 보통 클래스는 설계도일 뿐이며 객체를 생성(동적 생성)했을 때, 메모리에 적재되고 변수에 값이 저장되기 때문이다.
   하지만, static으로 선언된 변수나 메소드는 클래스 변수이기 때문에 JVM이 클래스를 읽어 들일 때 자동적으로 메모리에 함께 적재가 된다. 따라서 객체를 따로 생성하지 않고도 사용할 수 있게 된다.
   
   
   ```
   class Car {
     
     static int carNumber;
 
   }
   
   piublic class CarTest {
     
    @Test
    @DisplayName("Car 클래스의 Instance 테스트")
    public void CarInstanceTest() {
      
       Car car = new Car();
       Car.carNumber = 123; // class로 접근은 허용.
       car.carNumber = 0 // 객체로 접근은 권장하지 않는다. 
 
    }
 
   }
 
   ```
 <br>
 
 
 - **Finalizers**
   
   - finalizer는 객체가 소멸될 때 직접적으로 메모리를 반납할 수 있는 메소드이다.<br>
   - 가비지 컬렉션(garbage collection)에 의해서 호출되기 때문에 임의의 시점에 실행이 된다. 
   - 실행 시간이 중요한 코드에서는 finalizer에 두지 않는다.
   - finalizer의 실행 주기는 전적으로 JVM 구현에 달려있기에 JVM 마다 다른 양상은 띈다.
   - 자바 언어 명세에서는 finalizer의 실행 시기를 보장하지는 않는다.
   - finalizer 수행 중에 예외가 발생하면 예외가 무시된다.
   - 엄청난 성능 저하를 일으킨다. 객체 소멸이 약 430배 느려진다.
 
 <br>
 

 ## 7. Javadoc
  
  * Javadoc이란? <br>
    JDK 와 함께 패키지로 제공되는 특수 도구이며, HTML 형식으로 Java 소스 코드를 문서로 작성할 때 사용된다. ex) API 문서를 HTML 형식으로 작성 <br>
    HTML 형식이기에 하이퍼링크를 통해 접근이 가능하다는 장점이 있다.
 
  * javadoc의 주석의 종류
 
    1) 한 줄 주석 : //
    2) 여러줄 주석 : /* ~~~ */
    3) 문서 주석 : /** 문서 내용 **/

 <br>

   - **기본 포멧팅**

     ```
     /**
     * Resizable-array implementation of the <tt>List</tt> interface.  Implements
     * all optional list operations, and permits all elements, including
     * <tt>null</tt>.  In addition to implementing the <tt>List</tt> interface,
     * this class provides methods to manipulate the size of the array that is
     * used internally to store the list.  (This class is roughly equivalent to
     * <tt>Vector</tt>, except that it is unsynchronized.)
     * <a href="{@docRoot}/../technotes/guides/collections/index.html">
     * Java Collections Framework</a>.

     * @author  Josh Bloch
     * @author  Neal Gafter
     * @see     Collection
     * @see     List
     * @see     LinkedList
     * @see     Vector
     * @since   1.2
     */

     <!-- Java ArrayList의 Javadoc -->

     ```
 
  * /** 다음은 공백이며, 줄바꿈을 해야 한다.
  * 문단과 문단 사이에는 공백 문자가 들어가며, @ 시작하기 전에도 공백 문자가 들어간다.
  * @param, @return, @throws, @deprecated 순으로 사용된다.
  * 설명이 반드시 기술되어야 하며, 한 문장을 넘어가면 4개 이상의 공백 문자(스페이스)로 들여쓰기를 한다.
  
 
           
 
  
 
  
 

 
  
 
 
 
  
 
 
 
 
 
 
 
      
    


    
   
   



   
   
     
   
     
   
   

   




