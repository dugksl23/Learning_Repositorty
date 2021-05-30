'use strict';

// 1. promise

// promise is a Javascript Object for a asynchronous operation
// - 프로미스는 자바스크립트의 오브젝트이며, 비동기적인 작업을 수행하기 위해서 쓰인다.
// promise는 js의 object 이기에 new 키워드를 이용해서 오브젝트를 생성한다.
// Promise는 executor 라는 콜백함수를 전달해줘야 하며, execute 콜백함수는
// resolve와 reject라는 콜백함수를 받는다.

// resolve 함수 : 기능을 정상적으로 수행해서, 마지막에 최종 data를 전달한다.
// rejecte 함수 : 기능을 수행하다가, 문제가 생겨서 호출하게 된다.

// 1-1. index
// state - 작업이 수행 중인 결과물을 나타냄
//         pending (처리중)-> fulfiled (완료) or rejected(서버 통신이 되지 않거나 data가 없을 때이다.)
// producer - promise의 object이며, 받아온 data를 결과물로 만드는 것.
// consumer - 해당 data를 소비하는 것.



// 1-2. Producer
//    when new Promise is created, the Executor runs automatocally

//    promise object를 생성하는 순간, execute가 실행된다는 것이다.
//    즉 promise를 만들어지는 그 순간, 그 안에 network 통신을 코드가 있다면 즉각 실행된다.

const promise = new Promise((resolve, reject) => {
    // doing some heavy work (network, read files);
    // 네트워크에서 데이터를 받아오거나, file에서 무언가 큰 data를 읽어오는 과정은
    // 시간이 걸리기 때문이다.
    // => 이것을 동기로 수행하게 되면, 해당 line을 처리하느라
    // 다음 line을 실행하지 못하기에, 비동기로 처리하는 것이 옳다.
    console.log('doing something....');
    setTimeout(() => {
        //resolve('ellie') --> then();
        reject(new Error('networking error')) // -> catch();
    }, 2000);
});

// 2. Consumer : then, catch, finally;
// producer에서 정상으로 동작하여, resolve 콜백함수에 전달한 값은,
//  consumer에서 then() 함수의 인자로 이어진다.

//    2-1. then
promise.then(value => {
    // then은 같은 promise를 return한다.
    // 그 return 된 promise에 catch를 호출할 수 있게 된다.
        console.log(value);
    })
    // 2-2 catch
    .catch(value => {
        console.log(value);
    })
    // 2-3 finally
    // promise의 최종적으로 실행되는 api이다.
    .finally(() => {
        console.log('finally');
    });



    // 3. promise chainning 
    //    then api의 경우에는 return을 pormise로 한다.
    //    따라서 promise의 then() or catch()를 호출할 수 있게 된다.
    //    이렇게 계속해서 promise chain이 형성된다.