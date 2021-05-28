// 1. use strict
// added in ES 5
// use this for Vanila Javascript
'use strict';
//console.log(age); // var hositing을 통해 최상단에 호출되어 값을 출력하며,
// 값이 없기에 undefined가 출력된다.

// 2. Variable = mutable type : let vs immutable type const
// let (added in ES6)

//console.log(text);
// *Global Scope
let gloableName = 'global name//전역변수';
// 글로벌 scope는 app의 생명주기와 함께 하며, 항상 메모리에 탑재되어 있다.
// (전역변수의 생명주기는 app의 시작과 끝과 같다.)
// 효율적인 메모리 사용을 위해서 최소한으로 사용

// *Local Scope
{
    let globalName = 'ellie';
    console.log(globalName);
    globalName = 'hello';
    console.log(globalName);
    console.log(`전역변`, gloableName);
}

console.log(gloableName)


//3. var (don't ever use this!)
//   js에서 var는 타입추론형이다.
//    value가 할당되었을 때, js 엔진이 자동으로 
//    입력된 값에 따라 타입을 추론한다.
//    따라서 'use strict' 모드에서 let or var를 쓰지 않는다면,
//    타입추론이 불가능하다.(EC5에서는 없어도 가능...)

//   *var hoisting - (move declaration from bottom to top);
//    한번 선언된 함수 및 변수는 선언된 위치와 상관없이 
//    호출 즉시 최상단으로 재배치 되며, 실행인된다.

age = 4;
console.log("나이는", age);


// *VAR를 쓰면 안되는 이유
{
    age = 4;
    var age;
}

//console.log(age);

//4. let
// let은 새로운 스택변수에 값을 할당하겠다는 의미이다.
{
    let age = 4;
    console.log(age);
}


// 5. constant 
// favor immutable data type always for a few reason"
// -- security
// -- thread safety
// -- reduce human mistakes
// const의 경우 최초 선언 이후, stack memoty 연결된 pointer가
// 잠기게 된다.


const number = 5;
//number = 6;
console.log(number);




// 6. variable types

// primitive type (single item) : boolean, String, number, null, undefined, symbol
// object (single item들의 box container)
// function (first class function) 함수도 변수로 할당된다. 즉 매개변수 및 return 변수로도 사용 가능.

const count = 17;
const size = 17.1;

console.log(`value : ${count}, type: ${typeof count}`)
console.log(`value: ${size} type : ${typeof size}`)


const infinity = 1 / 0;
const negativeInfinity = -1 / 0;
const nAn = 'not a number' / 2;

console.log(infinity)
console.log(negativeInfinity)
console.log(nAn)

// bitInt(fairy new, don't use it yet)
const bigInt = 123456789012345678901234567890;
console.log(`value:${bigInt}, type:${typeof bigInt}`);

// String
// `~~` template literals(String) 빽태그

const char = 'c';
const brendan = 'brendan';
const greeting = 'hi' + brendan;
console.log(`hi ${brendan}`);
console.log(`value : ${greeting}, type : ${typeof brendan}`)

// boolean
// false : 0, null, undefined, NaN, ''
// true : any other value

const canRead = true;
const test = 3 < 1; // false; evaluation을 통해서 자동으로 false로 할당.
console.log(`value : ${test}, type: ${typeof test}`)

// null = empty 값
const emptyValue = null; //null과 ''(빈문자열)은 다르다 == false;
console.log(`value : ${emptyValue}, type: ${typeof emptyValue}`);


// undefined
let x = undefined;
console.log(x);


// symnol
//  나중에 자료구조에서 map, set, list를 사용할 때
//  고유 식별자로서 동시다발적으로 발생할 수 있는 코드에서
//  우선권을 주고 싶을 때 고유식별자로서 쓰는 타입.

const symbol1 = Symbol('id');
const symbol2 = Symbol('id');
console.log(symbol1 === symbol2)
// 동일한 String의 value로 갖고 있더라도, 각각의 symbol로 만들어진다.
// *동일한 symbol을 만들고 싶을 경우에는?
const gSymbol1 = Symbol.for('id'); // symbol key 값
const gSymbol2 = Symbol.for('id'); // symbol key 값
console.log(gSymbol1 === gSymbol2)
console.log(`${gSymbol2.description}`)
// symbol의 value는 symbol.description 변수를 통해 호출해야 한다.



// 7. Dynamic Typing : dynamically typed language
let text = 'hello';
console.log(text.charAt(0)); //h 출력

text = 1;
text = '8' / '2';
console.log(`text value : ${text}, type : ${typeof text}`);



// 8. typescript 
// js의 dynamic Typing 때문에 파생된 것이 TS이다.
// 다만 TS는 브라우저서 바로 컴파일이 되지 않고
// 브라우저가 이해할 수 있는 JAVASCRIPT로
// TRANSfer Comfile을 해주어야 한다.
// babe;