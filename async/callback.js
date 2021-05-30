'use strict';


// javaScript is synchronous (동기적 언어)
// Execute the code block by order after hoisting.
// hoisting : var, function declartion 함수들이 호출즉시 최상단에 위치해서 실행되는 것.

// 1. 비동기적 실행, ansynchronous 실행
console.log(1);
setTimeout(() => console.log(2), 0.1);
// 나중에 실행하는 함수라고 해서 callback함수라고 한다.
// 이는 브라우저에 실행을 비동기로 요청하고, 그 뒤에 callback 함수가 실행된다.
console.log(3);



// 2. callback의 동기와 비동기

// 2-1. Synchronous callback
// 인자로 print()를 받고 출력한다.
function printImmediately(print1) {
    print1(); //wow출력
}

printImmediately(() => console.log('wow~'));
// 자바스크립트는 type이 아니라서, 어떤 타입의 콜백함수를 전달 받는지를
// 에측할 수는 없지만, 이번엔 인자 전달하지 않고, console.log() 함수를 전달.


//==> 출력결과
//    모든 var 또는 (named) function 선언은 hoisting에 의해서 최상단으로 
//    올라가서 제일먼저 실행이 된다.

// 2-2. Asynchronous
function printWithDelay(print, timeout) {
    setTimeout(print, timeout)
}

printWithDelay(() => console.log('콜백 함수를 먼저 인자로 보냈고, 보낸후의 결과값을 다시금 인자로 보냅니다, console.log()와 milliSecond'), 1000);
// callback 함수를 전달하고, 콜백함수 실행 후의 결과값을
// 인자로 다시 printWithDelay 함수에 전달한다.
