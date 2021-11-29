'use strict';


// 0. object와 자료구조의 개념 정리
// * object 란? 하나의 물체 또는 정의된 사물이 있으며,
// 이 사물과 연관된 특징과 또는 서로 연관된 것들을 묶어 놓는 것.
//  ex) 토끼, 당근

// *. 자료구조란? 토끼와 당근처럼 비슷한 타입의 object들을 모아놓는 것 
//    또한 type이 있는 언어에는 동일한 타입의 object만 담을 수 있게 구성되어져 있다.


// 1. Array
// *Declaration
const arr1 = new Array(); //object로 만들기`
const arr2 = [1, 2, 3];   // 대괄호를 열고 값을 넣는 방식.


// 1-1. 첫번째 index와 마지막 index에 접근하기.
const first = arr2[0];
const last = arr2[arr2.length - 1];


// 2. looping
//  (1) for 문
const fruits = ['apple', 'banana'];
// for (let i = 0; i <= fruits.length; i++){
//     console.log(arr2[i]);
// }

// //  (2) for of == forEach
// for (let fruit of fruits) {
//     console.log(fruit);
// }


// (3) forEach
// 첫번쨰 인자는 array의 index의 값을 return 받을 value, 
// 두번쨰 for문이 도는 배열의index, 
// arr은 전체 배열을 return 받는다.

fruits.forEach((each, index, arr) => {
    console.log('forEach' + each+index);
    console.log('forEach' + arr); 
});

// (4) addtion, delete, copy
// push : add on item to the ned
fruits.push('orange', 'peach ');
console.log(fruits);


// pop : remove and item from the end
fruits.pop();
console.log(fruits);

// remove and item from the end
// splice는 해당 index만 삭제한다.
let which = fruits.splice(fruits.length - 1);
//===========

// unshift : add an item to the beginning
//           return 값은 array의 length
fruits.unshift('wow~~~');
console.log(""+fruits);
// shift : 앞에있는 데이터부터 데이터를 삭제한다.
//fruits.shift();
console.log("뒤에꺼 삭제"+fruits);

// ==> push/pop> unshift/shift 
//     같은 기능이지만, 성능차이가 난다.
//     push/pop은 list처럼 동적생성및 삭제를 한다.
//     unshift / shift는 배열처럼 기존 인덱스 내에서 데이터의 이동이 일어난 후에
//     index 추가 및 삭제를 한다.(더 느리다고 볼수 있다.)


// (5) splice : 꼬아서 잇다.
//     첫번째 index : 지정된 부분의 index부터 지울 위치 지정.
//     두번째 index : 몇개를 지울지. (optional ?)
//     세번쨰 인덱스부터는 추가할 value를 지정하여, 삭제된 index부터 넣을 수 있다.
fruits.splice(0, 1);
console.log(fruits)
fruits.splice(0,1, 'wow', 'omg')
console.log(fruits)

// (6) combine two arrays
//     원본 데이터의 복사 없이 새 데이터의 인자로 보내어진 array를
//     다른 배열에 추가한다.
const fruits2 = ['english', 'korean'];
const fruits3=fruits.concat(fruits2);
console.log(fruits3);




// (7) searching
// *indexOf 
// 찾고자 하는 value가 배열의 몇번쨰 인덱스에 있는지 확인하는 것.
// 제일 첫번째로 해당하는 값을 만났을 때, 그 값의 index를 return 한다.
console.log(fruits.indexOf('wow'));

// *includes : 있는지 없는지 확인하고 boolean을 return한다.
console.log(fruits.includes('wow'));

// *lastInfexOf : 
//   index와는 반대로 제일 마지막에 만나는 value의 index를 return한다.

console.clear();
fruits.push('wow');
console.log(fruits);
console.log(fruits.lastIndexOf('wow'));
