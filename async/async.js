'use strict';
// async & await;
// clear style of using promise;

// 1. async;

async function fetchUser() {
    return 'ellie';
};
//async 키워드를 사용하면, 자동으로 promise를 return 한다.


const user = fetchUser();
user.then(console.log);


// 2. await
//    promise 밑 aysnc에 를 사용하는 함수에 대하여
//    비동기통신이 끝날 때까지 다음 line을 실행시켜 주지 않게 하는 await 키워드이다.

function delay(ms) {
    //여기서 promise를 거는 이유는, 비동기 처리를 시작하기 위함.
    return new Promise((resolve) => setTimeout(() => {
        resolve();
        console.log('실행');
    }, ms));
    // return new Promise(resolve => resolve, ms));
}

async function getApples() {
    await delay(3000);
    console.log('언제실행?');
    console.log(delay(3000));
    return '사과'; //return이 promise이기에 then api로 넘어가게 된다.resolve 함수로 간다.
}

async function getBanana() {
    await delay(3000);
    console.log('언제실행?');
    console.log(delay(3000));
    return '바나나';
}

function pickFruits() {
    return getApples()
        .then(apples => {
            return getBanana()
                .then(pick => `${apples}+${pick}`)
        })
}

pickFruits().then(console.log);