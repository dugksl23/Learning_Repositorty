# Google-Java-Style-Guide


## made by 요한 김 <br>

---

## 1. 개요

이 문서는 Java 프로그래밍 언어의 소스 코드에 대한 Google Style로 규약된 코딩 스타일이다. <br>
Java 소스 파일은 여기에 있는 규칙을 준수하는 경우에만 Google style로 프로그래밍 된 것이라고 규정할 수 있다. <br>

이외에도 코딩 규약은 여러 종류가 있습니다만, Java의 경우에는 Google의 코딩 규약을 많이 사용한다. 
<br>
<br>

## 2. 소스 파일 기본 사항

 - 소스 파일 이름(확장자는 .java로 지정.)이 포함된 최상위 클래스의 <span style="color:red">대소문자 구별</span>을 통해 명명하며, 첫글자는 대문자로 한다.
 - 파일의 인코딩은 UTF-8로 인코딩 한다. ex) IJ
 
   ![image](https://user-images.githubusercontent.com/68539491/125384746-57c48180-e3d4-11eb-9d9a-931d501751e7.png)

 
 
 - 공백 문자는 소스 파일에서 아무 곳에서나 나타나는 유일한 공백 문자입니다. ex) 0x20(32): 스페이스 키(공백), 그러나 문자의 진수 0x00(0), NULL: 널 문자는 사용하지 않는다.
 - 코드 내에서 특수 문자를 사용할 때, 이스케이프 시퀀스를 사용한다. ex) \b, \t, \n, \f, \r, \", \', \ or \\ 등을 사용한다. 
<br>
<br>

## 3. 소스 파일의 구조

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
 - import 문은 그룹핑을 통해 순서에 맞춰 작성한다. ex) static import -> google 내부 package import -> google 외부 package import -> java -> javax
 
   ![스크린샷 2021-07-13 오전 11 11 19](https://user-images.githubusercontent.com/68539491/125379194-0f549600-e3cb-11eb-8107-41af82a0a27b.png)
   
 - 하나의 소스 파일(.java)에는 하나의 class만 존재해야 하며, nested class는 존재해선 안 된다.
 - 클래스 내용의 순서 
    * 클래스 멤버의 순서는 절대적인 것이 없다. 다만, 이들의 순서가 논리적이여야 한다. ex) 새 메소드가 추가되었다고 하여, class의 가장 마지막에 구현되는 것은 논리적이지 않다.
    * class 생성자의 오버로딩을 통해 여러개가 존재할 경우, 이들은 순차적으로 작성되어야만 한다. 중간에 다른 멤버를 작성할 순 없다.
<br>
<br>
    
    
## 4. 포멧팅

 - **중괄호**

   Google Java coding convention은 K&R Style을 따르며, 제거가 가능해도 사용한다.<br>
   K&R Style이란? Kernight and Ritchie Style의 역자로서 아래와 같은 규칙을 가진다.
   
   - 여는 괄호 앞에는 줄 바꿈이 없다.
   - 여는 괄호 뒤에는 줄 바꿈이 있다.
   - 닫는 괄호 앞에서 줄 바꿈이 있다.
   - 닫는 괄호 뒤에서 줄 바꿈이 있다.
   - 닫는 괄호 뒤에는 줄 바꿈이 있다.
   
   ```
    public class GoogleJavaConvention {
    
      public staic void main(String[] args) {
        
        int result = calcurator("+", 3, 4);
        
      }
    
    
       public static int calculator(String operator, int a, int b){
         
         if (operator == "+") {
           return sum(a, b);
         }
       
       }
    
       public static int sum (int a, int b){
       
         return a + b;
       
       }
    
    
    }
   
   ```

   ## 불가능한 예시

   ```
    public static void main(String[] args)
    
    
    public static int sum (int a, int b) {
      return a + b;
    }
    
    public static void main(String[] args){sum(3,4)} 
    
    
   ```
   
   ## 자주 사용하는 방식
   
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
   
  
 - **Line - Wrapping**
  
   - 각 구문은 하나의 줄 바꿈을 가진다. 
   - 일반 연산자 앞에서는 줄 바꿈이 없다.
   - 대입 연산자에서는 뒤에서 줄 바꿈이 일어난다. ex) Tab을 통한 들여쓰기 +4 공간 사용.
   - 메소드 및 생성자의 이름에는 괄호"("를 부착한 상태로 유지한다.
   
   - 한 줄로 표현해야 하는 줄을 여러개의 줄로 나누었다고 했을 경우에는 줄바꿈을 해야 한다. 
     줄 바꿈을 모든 상황에서 적용할 수 있는 포괄적인 공식은 없다. 따라서 하나의 구문에는 줄 바꿈을 하는 여러가지 방식이 존재한다.
   - 메소드 또는 지역변수를 활용하여 코드의 라인을 줄일 수 있다.


   ```
   
 
    public static void main(String[] args) {

        int num = 
              result();
    }


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
    
    
    ## 수평 공백이 허용되지 않는 예외
    
    - @Anotation({a, b});
    - 람다식 객체 및 메소드 참조 표현식 "::"
      (Integer::intValue)
      (Car::new)
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
     - enum
      
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
 
   
   ## 배열 선언
   
   - 열거 및 줄 바꿈 가능.
   - 쉼표 이후에 공백 문자.
   
   ```
   new int[] {1, 2, 3, 4, 5, 6,}
   new int[] {
        1,
        2,
        3,
        4,
        5
   }
   
     
   
   

   




