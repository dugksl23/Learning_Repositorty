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
    // 여기서 promise를 거는 이유는, 비동기 처리를 시작하기 위함.
    return new Promise((resolve) => setTimeout(() => {
        resolve();
    }, ms));
    // return new Promise(resolve => resolve, ms));
}

async function getApples() {
    await delay(1000);
    //throw new Error('실패');
    return '사과'; //return이 promise이기에 then api로 넘어가게 된다.resolve 함수로 간다.
}

async function getBanana() {
    await delay(2000);
    return '바나나';
}

function pickFruits() {
    return getApples()
        .then(apples => {
            return getBanana()
                .then(pick => `${apples}+${pick}`)
        })
}

//pickFruits().then(console.log);

async function pickFruitsWithAsync() {

    try {
        const applePromise = getApples();
        const bananaPromise = getBanana();
        // promise object를 만드는 순간, 즉각실행이기에, apple과 banana의 promise를 만들고
        // 병렬처리된 promise들을 동기화를 위해서
        // await을 사용한다.

        const apple = await applePromise;
        const banana = await bananaPromise;

        return `${apple}+${banana}`;
    } catch (err) {
        console.log(err);
    }

}

//pickFruitsWithAsync().then(console.log);

// 3. useful Promise APIS(all api)
function pickAllFruits(arr) {
    // all api는 모든 promise들을 병렬적으로 배열로 받는다.
    return Promise.all([getApples(), getBanana()])
        .then(fruits =>
            fruits.join(' + ')
            // join 함수는 배열을 string으로 입력된 매개변수를 기준으로 serialize 한다.
        );

}

pickAllFruits().then(console.log);


// 병렬처리중 가장 먼저 처리된 것만 받아오려면?

function pickOnlyOne() {
    return Promise.race([getApples(), getBanana()]);
}


pickOnlyOne().then(console.log);