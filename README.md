# Google-Java-Style-Guide


## made by 요한 김 <br>

---

## 1. 개요

이 문서는 Java 프로그래밍 소스 코드에 대한 Google Style로 규약된 코딩 스타일이다. <br>
Java 소스 파일은 여기에 있는 규칙을 준수하는 경우에만 Google style로 프로그래밍 된 것이라고 규정할 수 있다.<br>

이외에도 코딩 규약은 여러 종류가 있지만, Java의 경우에는 Google의 코딩 규약을 많이 사용한다. 
<br>
<br>

## 2. 소스 파일 기본 사항

 - 소스 파일 이름(확장자는 .java로 지정.)이 포함된 최상위 클래스를 대소문자 구별을 통해 작성하며, 첫글자는 대문자로 한다.
 - 파일의 인코딩은 UTF-8로 인코딩 한다. 
   ex) IJ
 
   ![image](https://user-images.githubusercontent.com/68539491/125384746-57c48180-e3d4-11eb-9d9a-931d501751e7.png)

 
 
 - 공백 문자는 소스 파일에서 아무 곳에서나 사용 가능한 유일한 문자입니다. <br>
   ex) 0x20(32) 스페이스 키(공백) -> ASCII CODE 에서 유일한 공백 문자이다.
       0x00(0) NUL (NULL Character) -> 널 문자는 0으로 대입이 된다. 
       
 - 코드 내에서 특수 문자를 사용할 때, 이스케이프 시퀀스를 사용한다. <br> 
 
   <img width="670" alt="스크린샷 2021-07-14 오후 4 10 39" src="https://user-images.githubusercontent.com/68539491/125578960-c1d6f62b-8d5a-420d-8286-de98879719d7.png"> 
<br>
<br>

## 3. 소스 파일의 구조

소스 파일 구조 또한 코딩 규칙(convention)이라고 볼 수 있다. 개발 뿐만 아니라, 유지보수에서도 통일성 있는 규격을 가진다면 가독성을 높일 수 있고, 동일성을 보장받을 수 있게 된다.
Google에서는 이러한 점을 고려하여, 다른 소스 코드들과의 동일성을 보장하기 위해서 패키지(package)로 구성하였다.

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
 <br>
 
 - import 문은 그룹핑을 통해 순서에 맞춰 작성한다.<br>
   ex) static import -> google 내부 package import -> google 외부 package import -> java -> javax
 
   ![스크린샷 2021-07-13 오전 11 11 19](https://user-images.githubusercontent.com/68539491/125379194-0f549600-e3cb-11eb-8107-41af82a0a27b.png)
   
   ![image](https://user-images.githubusercontent.com/68539491/125582901-f6ad9826-6d79-4405-b0b7-3635d90d2ac6.png)

<br>

 - 하나의 소스 파일(.java)에는 하나의 class만 존재해야 하며, nested class는 존재해선 안 된다.(*class 내의 또 다른 class 선언)
 - 클래스 내용의 순서 
    * 클래스 멤버의 순서는 절대적인 것이 없다. 하지만, 순서가 논리적이여야 한다. <br>
      ex) 새 메소드가 추가되었다고 하여, class의 가장 마지막에 구현되는 것은 논리적이지 않다.
    * class 생성자의 오버로딩을 통해 여러개가 존재할 경우, 이들은 순차적으로 작성되어야만 한다. 중간에 다른 멤버를 작성할 순 없다.
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
<br>
    
    
## 4. 포멧팅

 - **중괄호**

   Google Java coding convention은 K&R Style을 따르며, 중괄호 제거가 가능해도 사용하는 것을 권장한다.<br>
   K&R Style이란? Kernight and Ritchie Style의 약자로서 아래와 같은 규칙을 가진다.
   
   - 여는 괄호 앞에는 줄 바꿈이 없다.
   - 여는 괄호 뒤에는 줄 바꿈이 있다.
   - 닫는 괄호 앞에서 줄 바꿈이 있다.
   - 닫는 괄호 뒤에서 줄 바꿈이 있다.
   - 닫는 괄호 뒤에는 줄 바꿈이 있다.
   <br>
   
   ```
    public class GoogleJavaConvention {
    
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
 
 - **줄 바꿈 없는 방식**

   ```
     public static void main(String[] args){sum(3,4)} 
   ```
   
 - **괄호 생략한 방식**
 
   ```
    public static void main(String[] args) {
            
         int num = calculator("+", 3, 4);
    }
    
    
    public static int sum (int a, int b) {
       return a + b;
    }
    
    public static int calculator(String operator, int a, int b){
       
       if(operator == "+") return sum(a, b);
       else return -1;
       
    } 
    
   ```
   <br>
  
 - **Line - Wrapping**
  
   - 각 구문은 하나의 줄 바꿈을 가진다. 
   - 일반 연산자 앞에서는 줄 바꿈이 없다.
   - 대입 연산자에서는 뒤에서 줄 바꿈이 일어난다. ex) Tab을 통한 들여쓰기 +4 공간 사용.
    <br>
    
     ```
     public static void main(String[] args) {

         int num = 
               result();
     }
     ```
    
    <br>
   
   - 메소드 및 생성자의 이름에는 괄호"("를 부착한 상태로 유지한다.
   - 한 줄로 표현해야 하는 줄을 여러개의 줄로 나누었다고 했을 경우에는 줄바꿈을 해야 한다. <br>
     하지만, 줄 바꿈을 모든 상황에서 적용할 수 있는 포괄적인 공식은 없으며 하나의 구문에서 줄 바꿈을 하는 여러가지 방식이 존재한다.<br>
     ex) 메소드 또는 지역변수를 활용하여 코드의 라인을 줄일 수 있다.
     <br>

     ```
      public static int result() {

          Scanner sc = new Scanner(System.in);
          System.out.println("연산할 숫자를 입력해주세요. : a ");
          int a = sc.nextInt();

          System.out.println("연산할 숫자를 입력해주세요. : b ");
          int b = sc.nextInt();

          System.out.println("연산할 부호를 입력해주세요. : +, -, *, / ");
          String operator = sc.next();

          return calculator(operator, a, b);

      }

      public static int calculator(String operator, int a, int b) {
          if (operator == "+") return sum(3, 4);
          else return -1;
      }

      public static int sum(int a, int b) {
          return a + b;
      }

     ```
   
   
 - **수평 공백**
    
    - if, for catch와 같은  다음에 오는 여는 "(" 사이에 공백문자.
    - 닫히는 ")" 뒤에 오는 {} 사이에도 공백문자.
    - else, try, catch 다음에 오는 {} 사이에도 공백문자.
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
      
      (Integer) num; 

      try {
         calculator("+", 3, 4);
      } catch (Error e) {
         e.printStackTrace();
      }


    ```
    
 - **수평 공백이 허용되는 예외**
    
    - @Anotation({a, b});
    - 람다식 객체 및 메소드 참조 표현식 "::" <br>
      (Integer::intValue)<br>
      (Car::new)<br>
    - 배열 내의 배열
      String[][] arr = {{"1", "2,", "3"}}  
   
 - **수직 공백**

    - 수직 공백은 한 줄 공백이라고 한다.
    - 필드, 생성자, 중첩 메소드. 클래스 사이
    - 여러 줄의 빈 줄은 허용하지만, 추천하지 않는다.
      
 
 - **변수 수평 정렬**
    - 변수의 수평 정렬은, 변수를 수평으로 정렬하는 것을 가리킨다.
    - 변수 수평 정렬은 Google Java Coding Convention에서는 필요로 하지 않다.
   
   ```
    private int a; int b; int c; int d; // 옳지 않다.
    private int  a; // 허용되나, 권장하지 않음. 2번의 문자 공백.
    
   ```
  
 - **변수 수평 정렬**
    - 변수의 수평 정렬은, 변수를 수평으로 정렬하는 것을 가리킨다.
    - 변수 수평 정렬은 Google Java Coding Convention에서는 필요로 하지 않다.
   

 - **그룹핑**
 
    - 선택적 그룹핑을 통해서 코드 작성자와 리뷰어가 괄호를 생략해도 잘못 해설될 가능성이 없다.
    - 쉽게 읽을 수 있으며, 가독성이 있다.
    
    ```
      private class MyClass {
        // 수직 공백
        int a = 0;
        int b = 0;
        
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
    
  - **특수한 구조**

     ## Enum class
     
     - 상수들의 집합체 / 상수들의 열거형.
     - 각 Enum 상수는 "," 이후에 열거 또는 줄 바꿈을 허용한다.
     - 상수를 나타날 때에는 대문자를 사용한다.
     - Enum은 final static 예약어가 생략되어져 있다. same as interface형
     - enum의 열거형 상수 뿐 아니라, 추가적으로 생성자의 파라미터를 통해 enum class의 필드에 추가 속성값을 초기값으로 설정을 해주고, getter 함수를 통해 해당 속성을 사용할 수 있다.
     
      
     ```

     public enum DayTest {

         MONDAY("월요일"), TUESDAY("화요일"), WEDNESDAY("수요일"), THURSDAY("목요일"), FRIDAY("금요일"), SATURDAY("목요일"), SUNDAY("일요일");
         SOMETHING{
            @Override
            public String toString() {
            
            }
         }

         private String krName;

         DayTest(String days) {
             krName = days;
         }

         public String getKrName(){
             return this.krName;
             return "monday~~";
         }

     }

     private class EnumExample {
     
       private final static int MONDAY = 1;
       private final static int TUSEDAY = 2;
       private final static int WENDSDAY = 3;
       private final static int THURSDAY = 4;
       private final static int FRIDAY = 5;
       private final static int SATURDAY = 6;
       private final static int SUNDAY = 7;
        
     }
     
     
   
      class enumExample {

          public static void main(String[] args) {

              DayTest day = DayTest.SOMETHING;
              switch (day) {
                  case SOMETHING:
                      System.out.println("DayTest.SOMETHING);
                      
              }
              
              
              DayTest day = DayTest.MONDAY;
              System.out.println(day.getKrName());
          }
          
      }
   
     ```
 
   
     ## 배열 선언 (배열 초기화)

     - 열거 및 줄 바꿈 가능.
     - 쉼표 이후에 공백 문자.

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

     - 대괄호는 변형에 붙인다.

     ```
      String[] arg; // 허용
      String arg[]; // 불허

     ```
     
 - **특수한 구조**
  
   - Google Java Style guid에서는 스페이스 +2의 공백 문자를 들여쓰기의 기준으로 설정하고 있다.
   - switch 구문의 default 구문은 해당 코드가 없더라도 넣어야 한다.
     
   ```
     int num = 1;
     switch (1) {
       case 1:
         System.out.println(1+"입니다.");
       default:   
     }
     
   ```
 - **Anodation 어노테이션**
 
   - 어노테이션은 documentation block 이후의 클래스, 메소드, 생성자에 바로 적용이 된다.
   - 각각의 어노테이션은 한 줄에 하나씩 쓴다.
   
   ```
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
   ```
 
   - 예외적으로, 매개변수가 없는 어노테이션은 열거형으로 작성 가능.
   
   ```
   @Nullable public String getName(){ 
       //... 
   }
   ```

   - documentation block 바로 다음 필드에도 어노테이션이 적용될 수 있다.<br>
     이 경우에는 어노테이션을 매개변수에 한줄로 사용 가능하다.
     
   ```
   @PostMapping({value = 'signUpMember'})
   public void signUpMember(@valid @ReqeustBody MemberDto memberDto) {
      
      Optional<MemberEntity> signUpMember = Optional.of(memberDto.toEntity());
      memberService.signUpMember(signUpEntity.get());
      
      return new ResponseEntity<>(HttpStatus.OK);

   }
   ``
   
  - **블럭 주석**
  
    - 블럭 주석은 컴파일 시 해당 코드 라인들을 무시한다.
    - 주석의 종류
      1) 한 줄 주석 : //
      2) 여러줄 주석 : /* ~~~ */
      3) 문서 주석 : /** 문서 내용 **/


   
  - **수정자(접근제한자)**
  
    - 클래스와 메소드 및 필드의 제한자는 자바 언어 명세서에 권장되는 포멧팅(예약어)를 따른다.
    - 한 줄에 열거형으로 사용할 경우에는 아래와 같은 순서를 따른다.
    
    ```
     public protected private abstract default final static stransient volatile synchronized natice strictfp
    ```
    
  - **숫자형 리터럴**

    - long 형과 float형 변수는 대문자 L과 F를 접미어로 사용하여, 숫자의 자릿수를 혼동되지 않게끔 한다.
    - 접미어로 소문자는 사용하지 않는다.
    
    
    ```
      long num = 300000000l (x)
      long num2 = 300000000L (x)
          
    ```      
 
 ---
 
 ## 5. 네이밍

 - **모든 식별자에 적용되는 규칙**

   모든 식별자들은 ASCII 와 숫자 값만을 사용해야 한다. 식별자에 prefix 또는 suffixes는 사용하지 않는다.<br>
   아래와 같은 형태의 식별자는 사용되지 않는다.
 
   ex) name_  snake case
       mName camel case

 - **패키지 이름**
   
   패키지 이름은 모두 소문자로 이루어진다.
   snake case를 사용하지 않으며, 단순히 단어들이 연속으로 쓰여진 형태이다.
   
   ```
   com.example.googlestyle
   
   ```
   
 - **클래스 및 인터페이스 이름**
   
   클래스 이름은 UpperCamelCase로 작성이 되며, 명사구로 명명되어져야 한다.
   
   ```
   Character, DataList
   ```
   
   그러나 간혹 형용사 또는 형용사구로 대신할 수 있다.
   
   ```
   actable, immutable
   ```
   
   JUnit을 통한 테스트 클래스는 테스트 하는 클래스의 이름이 앞에 오고, 뒤에는 Test로 마무리를 한다.
   
   ```
   RegistrationTest
   SignOutTest
   
   ```
   
 - **메소드 이름**
   
   메소드의 이름은 lowerCase로 작성이 되며, 동사 또는 동사구로 명명 되어져야 한다.
   
   ```
   signUpMember
   sendMessage
   ```
   
   JUnit을 통한 메소드 테스트의 명명 규칙을 lowerCase로 작성이 되며, 동사구 또는 동사로 명명 되어져야 한다.
   다만, 전형적인 하나의 패턴을 가지고 있다. ex) <methodUnderTest>_<state> -> pop_emptyStack
   
   ```
   signUpMemberTest
   sendMessageTest
   signOutMemberTest
   signOutMember_fail
   ```
   
 - **상수**
   
   상수는 Constant_case를 사용한다. 모두 대문자를 작성되며, 단어 사이에 밑줄을 표시하여 명사구 또는 명사로 명명 되어져야 한다.
   또한 상수는 static final 키워드로 정의된 필드이며, 이 필드에 정의된 내용은 불변해야 한다.
 
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
 
 - **class의 멤버변수 field**
  
   상수가 아닌 class의 field는 loswerCamel case로 작성된다.
 
   ```
   int idx;
   String userName;
   int userPassword;
   int address;
 
   ```
 
 - **매개변수 Parameter**
  
   매개변수의 이름은 lowerCase로 작성이 된다.<br>
   public 메소드에서 한 문자로 된 파라미터 네이밍은 피해야 한다.(?)
 
   ```
   public static void main(String[] args){
     // ...
   }
 
   ```
 
 - **지역변수 local variable**
  
   지역변수의 이름은 lowerCase로 작성이 된다. 또한 final처럼 constant_case로서 상수처럼 사용해선 안 되기에 상수 스타일로 네이밍이 되어져선 안 된다.
  
   ```
   public static void main(String[] args){
     
      int idx;
      // ...
 
   }
 
   ```
 
 
 - **타입 변수 primitive variable**
  
   각각의 기본형 타입 변수는 아래 둘 중 하나의 방법으로 작성된다.
   1) 하나의 대문자, 선택적으로 하나의 숫자 추가 기능.
   2) 클래스 명명 규칙 + 대문자 
 
   ```
   E, T, T2
   RequestT
   ```
 
 - **타입 변수 primitive variable**
  
   각각의 기본형 타입 변수는 아래 둘 중 하나의 방법으로 작성된다.
   1) 하나의 대문자, 선택적으로 하나의 숫자 추가 기능.
   2) 클래스 명명 규칙 + 대문자 
 
   ```
   E, T, T2
   RequestT
   ```
 
 - **Camel case**
   
   Java에서 대체적으로 사용되는 coding style 또는 convention은 Camel case이다.
   Camel case는 첫글자는 소문자로 시작하며, 합성어의 첫글자만 대문자로 시작하는 규칙을 가지고 있다.
   이러한 모양새가 낙타와 비슷하다고 하여 카멜 케이스라고 불리고 있다. 단, 패키지, 클래스, 인터페이스, enum과 같은 명명 규칙에는 예외가 된다.
 
 
   ```
   int memberName;
 
   public void fetchUserDetail(){
     //...
   }

   ```
 
 ---
 
 ## 6. 프로그래밍 관례
 
 
 - **@override 항상 명시** (?)
 
   @Override 어노테이션을 사용할 수 있는 메소드는 반드시 붙인다. 이는 Super class와 상속관계를 갖는 childClass에서 Super class의 메소드를 오버라이딩 하고 있음을 나타내는 것이다.
  (*abstract class포함)
   또는 class의 메소드가 interface의 메소드를 구현하고 있음을 나타내고 있거나, interface 메소드가 Super interface 메소드를 재 구현하고 있음을 표현하는 것이기도 하다.
 
  
 - **throws와 try-catch** 
 
   throws(예외)는 내부적으로 소스 코드를 컴파일 하는 과정에서는 발견하지 못하며, 외부 접근에 의해 프로그램 실행 중에 예상치 못하게 발생하는 오류 사항들을 예외라고 부른다.
   이러한 예외들을 throws를 사용해서 해당 메소드를 사용하는 사람들에게 예외를 전가시키고, try-cath(예외 처리)를 통해서 Exception의 종류를 보여줌으로서 예상되는 오류를 명시해 주는 것이다.
   ex) NullPointException
 
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
 
 - **static 정적 변수 및 정적 메소드**
   
   일반적으로 변수 또는 메소드는 객체를 생성하지 않는다면 사용할 수 없다. 즉, 보통 클래스는 설계도일 뿐이며 객체를 생성(동적 생성)했을 때, 메모리에 적재되고 변수에 값이 저장되기 때문이다.
   하지만, static으로 선언된 변수나 메소드는 클래스 변수이기 때문에 JVM이 클래스를 읽어 들일 때 자동적으로 메모리에 함께 적재가 된다. 따라서 객체를 따로 생성하지 않고도 사용할 수 있게 된다.
   
   
   ```
   class Car {
     
     static int carNumber;
 
   }
   
   piublic class CarTest {
     
    @Test
    public void CarInstanceTest() {
      
       Car car = new Car();
       Car.carNumber = 123; // class로 접근 
       car.carNumber = 0 // 객체로 접근(x) 
 
    }
 
   }
 
   ```
 
 - **Finalizers**
 
   - 언제 실행이 될 지 알 수가 없으며, 가비지 컬렉터에 의해서 호출이 되기 때문에 임의의 시점에 실행이 된다. 실행 시간이 중요한 코드는 finalizer에 두지 않는다.
   - finalizer의 실행 주기는 전적으로 JVM 구현에 달려있기에 JVM 마다 다른 양상은 띈다.
   - 자바 언어 명세에는 finalizer의 실행 시기를 보장하지는 않는다.
   - finalizer 수행 중에 예외가 발생하면 예외가 무시된다.
   - 엄청난 성능 저하를 일으킨다. 객체 소멸이 약 430배 느려진다.
   - 해결책? 각각 인스턴스에 대해서 자신의 종료 여부를 유지 관리해야 한다.<br>
           ex) InputStream 과 OputStream은 각각 close() 함수를 별도로 제공 -> 현재는 쓰지 않는 것을 권장.
 
 
 ---
 
 ## 7. Javadoc
  
  * javadoc이란? JDK 와 함께 패키지로 제공되는 특수 도구이며, HTML 형식으로 Java 소스 코드의 코드 문서를 생성하는 데 사용된다. <br>
    즉, API 문서를 HTML 형식을 생성해준다는 것이다. HTML 형식이기에 하이퍼링크를 통해 접근이 가능하다는 장점이 있다.
 
  * javadoc의 주석의 종류
 
    1) 한 줄 주석 : //
    2) 여러줄 주석 : /* ~~~ */
    3) 문서 주석 : /** 문서 내용 **/

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
   
   errrr//...
   
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
  
  * **한줄 포멧팅**
  
  ```
    /**
     * A version of rangeCheck used by add and addAll.
     */
  ``` 
 
  * /** 다음은 공백이며, 줄바꿈을 해야 한다.
  * 문단과 문단 사이에는 공백 문자가 들어가며, @ 시작하기 전에도 공백 문자가 들어간다.
  * @param, @return, @throws, @deprecated 순으로 사용된다. 
    설명은 반드시 기술해야 하며, 한 문장을 넘어가면 4개 이상의 공백 문자(스페이스)로 들여쓰기를 한다.
  
 
           
 
  
 
  
 

 
  
 
 
 
  
 
 
 
 
 
 
 
      
    


    
   
   



   
   
     
   
     
   
   

   




