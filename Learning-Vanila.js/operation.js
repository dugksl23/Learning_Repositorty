// 1. Increment and Decrement Operator


// PreIncrement Operator
// *연산이 먼저 이루어지고 난 후에 값을 할당한다.
//  (count 변수를 먼저 업데이트 하고 난후에 대입한다.)

let counter = 2;
const preIncrement = ++counter;
// ++counter => counter = counter+1 // => 3;
// preIncrement = 3; // 3
// 최종적으로 3 출력

// PostIncrement Operator
// *변수를 먼저 postIncrement 변수에 할당을 하고, count의 값을 증가시킨다.

let counter = 3;
const postIncrement = counter++;
//counter++ => postIncrement = counter;
// counter = counter + 1;
// 4 출력.


// 2. Comparison Operator

// *A는 B보다(*를 기준으로) 작거나, 크거나, 같다
console.log(10<6); //less than
console.log(10<=6) // less than or equal
console.log(10 > 6) // greater than
console.log(10 >= 6) // greater than or equal

// 3. Logical Operator
//  * || (or), && (and), ! (not)
const value1 = false;
const value2 = 4 < 2; // less than

function check() {
 for(let i = 0; i<10; i++) {
   console.log('waiting time~');
 }
 return true;
}


// 1. or 연산자
//    or 연산자는 true가 나오는 시점에서 멈추고, true를 return 한다.
//    또한 or은 그 중 하나가 true가 나오면, true로 인식하고 연산을 종료한다.
//    예를 들어, value 1 = true 라고 가정한다면, 아래의 or 연산은 value 1에서 
//    true를 내뱉고 연산은 종료된다.
 
console.log(`or : ${value1 || value2 || check()}`);  
// => check() 함수에서 true 이기에 true를 return한다.

// * 따라서 or을 사용할 때는, 연산이 가장 많이 소요되는 expression은 
//   가장 뒤에 배치해야 한다.


// 2. and 연산자
//    and는 모든 조건이 true가 되어야 return이 된다.
//    따라서 and 연산자의 첫번째 조건식이 false가 나오면, false를 return하고
//    연산을 종료한다. 따라서 연산이 소요되는 expression을 뒤에 배치해야 한다.

const value1 = true;
const value2 = true;
function check() {
  return true;
}

console.log(`and : ${value1 && value2 && check()}`);


//3. !(not) 연산자
console.log(!value1) // false를 return


// 4. Equality
// * == (loose equality), === (strict equality)

// 1. == (loose equality)
// data type이 아닌, value의 실제값을 비교하여 값이 같다라고 한다면,
// js engine에 의해 같다고 인식을 하고, 같음(==)을 성립시킨다.

const stringFive = '5';
const numberFive = 5;

console.log(stringFive == numberFive);
// => true 를 return
console.log(stringFive != numberFive);

// 2. === (strict equality)
// data type 및 실제 value 값이 동일했을 경우에 같음(===)을 return 한다.
// data type이 다를 경우에는, false를 return 한다.

const numberFive = 5;
const number5 = 5;

console.log(`${numberFive === number5}`);
console.log(`${numberFive !== number5}`);
 


// 3. object equality by reference;
//    아래의 변수는 객체의 참조값 or 주소값을 가진다.
//    따라서 해당 객체의 참조값(주소값)을 따라 실제 stack memory에 저장된 변수에 할당된 값을 찾아서 읽는다.
//    마찬가지로, 비교할 때도 객체의 참조값(주소값)을 기준으로 연산을 한다.

const ellie1 = {name : 'ellie'};
const ellie2 = {name : 'ellie'};
const ellie3 = ellie1;

console.log(`${ellie1 == ellie2}`); // false
console.log(`${ellie1 === ellie2}`); // false
console.log(`${ellie1 === ellie3}`); // true


// => 첫 번째, 서로 다른 reference(객체 참조값)를 통해 값을 비교하기에 false 이다.
// => 두 번째, 서로 다른 객체의 참조값을 가지고 있기에 false
// => 세 번째, ellie3은 ellie1의 주소값을 할당받았기에 동일 객체 주소값을 가지고 있기에 true.


// 5. Ternary Operator (3항 연산자)?

console.log(name === 'ellie'? 'Yes':'No');
console.log(name === 'ellie' ? 'Yes' : name === 'Ellie' ? 'Yes' : 'No'); // nesting Ternary Opeartion
//*nesting Ternary Opeartion 의 경우에는 가독성이 낮아서, if문 or switch 문을 사용하는 것이 좋다.

// 6. Switch
const browser = 'IE';
switch (browser){
  case 'IE':
        console.log('wow~');
        break;
  case 'Chrome':
  case 'Firefox':
        console.log('love you!');
        break;
  default : 
        console.log('bye~');
        break;

}

// => 동일 로직을 수행할 경우에는 'Chrome'과 'Firefox' 처럼 한데 묶어서
//    제일 마지막 case에 로직을 작성하면 된다.


// 7. While loop

// 1. while loop

let i = 3;
while(i>0){
  console.log(`while: ${i}`);
  i--;
}

// => 3,2,1이 출력
//    while loop는 조건문이 일치할 때만, 아래 block의 로직을 실행하겠다는 차이가 있다.


//2. do ~ while loop
do {
  console.log(`while : ${i}`);
  i--;
} while(i > 0);

// => 0을 출력
//    이미 while loop를 통해 i의 값은 0이 되었고,
//    do while loop는 block 안에 로직을 먼저 실행후, while loop의 조건식이 일치한다면
//    다시 do {}를 실행한다는 차이가 있다.


// 8. for loop
// 1. for loop
//    for(begin; condition; (next)step)
//    begin은 처음에만 실행되며, 이후에 condition(조건식)이 행해지고, 
//    next step으로  step을 거치게 된다.
 
for(let i = 3; i>0; i--){
  console.log(`for : ${i}`);
}

//================================

// quiz 1

console.log(0 == false); // true 
console.log(0 === false); // false
console.log('' == false); // true
console.log('' === false); // false

// => 0, null, ''(emptyString)은 loose equality에서는 false와 같다.
//    다만, strict equality에서는 data type을 따지기에 비교가 되지 않기에 false이다. 

console.log(null == undefined); // true 
console.log(null === undefined); // false;

// => null과 undeifned는 특이하게 같음(loose equality)을 나타내기에 true이다.
//    그러나, strict equality에서는 당연히 data type이 다르므로 false이다.

// quiz 2
// 1. 1부터 10까지에서 짝수만 출력하세요.

for(let i=0; i<=10; i++){
  if(i%2 != 0){
    continue;
  }
  console.log(`${i}`);
}


// 2. iterate from 0 to 10 and print out numbers until reaching 8 (use break)

let j = 0;
while (j<=10){
  j++;
  if (j > 8) { 
     break;
  } 
console.log(`${j}`);
}