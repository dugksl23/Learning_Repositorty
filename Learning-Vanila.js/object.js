//1. object 생성

const person1 = {}; //object riteral
const person = new Object(); // object constructor syntax
const person3 = { name: 'ee', age: 2 };


person3.name = 'eeeeee';
console.log(person3.name);

// 2. object에 변수 추가

person3.hasJob = true;
console.log(person3.hasJob);
person3['hasJob1'] = true;
console.log(person3.hasJob1);


// 3. object delete

delete person3.hasJob;
console.log(person3);


// 4. comuted properties = 계산된 변수
// * key should be always string
console.log(person3.name); // dot으로 접근
console.log(person3['name']); //[]의 인덱스 형식으로 key 값(변수명)을 넘기면 된다.


// 왜 이런 접근법의 차이가 있을까?
// ==> dot(.)을 통한 접근 법은 key 값을 통해 value 값을 알아내고자 할 떄이다.
//      []을 통한 keyString의 접근법은 key 값이 있는지 없는지 알기 위할 때이기도 하다.
//      보통은 dot을 통한 접근이 맞지만, 실시간 key 값이 부여될 때에는 []을 통한 keyString이 옳다.


// 4-1 tokeny key를 받아서 login을 시도하려고 할 때는, keyString properties를 사용하면 된다.
function printVlue(obj, key) {
    console.log(obj[key]);//keyString을통한 변수 접근.
    console.log(obj.key); //dot을통한 접근
    // ==> obj.key는 존재하지 않는 객체에 dot을 통한 key 변수에 접근했기에 undefined가 뜬다.
}
printVlue(person3, 'name');


// 5. Property value shorthand
// *Funtion을 통한 class 템플릿
//  class or object or 객체로 만들 떄에는 첫글자는 대문자로 약속되어져 있다.

class Person {
    constructor(name, age) {
        //여기서 생략된 것은 constructor가 생략
        // this ={}
        this.name = name;
        this.age = age;
        //return this;
    }
}

const person4 = new Person('sdfsdf', 3);
console.log(person4);

// 6. in operator

// * key in obj
// propertioes가 존재하는지 여부 확인
// (key in obj)
// ture or false를 return 한다.

console.log('name' in person4);

// 7. for in  and for of
//    object안의 모든 key를 꺼낸다.
// for in key
for (key in person4) {
    console.log(key);
    console.log(person4[key]);
}


// 8. for (value of interable)
// for of value
const array = [1, 2, 3, 4];
for (value of array) {
    console.log(value);
}

// 9. 메모리 구조
//  heap - 객체의 참조값/ 할당 또는 해제되는 값들
//  stack - 지역변수, 매개변수
//  data - 전역변수와 static
//  code - 우리가 쓰는 code



// 10. 배열복제 및 생성
// old way

const person5 = {};
for (key in person3) {
    person5[key] = person3[key];
    console.log(person5);
}


// 11. fucntion closing
//  첫번쨰 인자는 유사배열로 return 받은 객체명
//  return 받을 객체가 있다면, 1번은 optinal 
//  두번째는 복사할 대상.
const person6 = Object.assign(person5);
console.log(person6);



// 12. Object.assign 함수를 이용하여, 여러 객체를 복사하기.

const person7 = Object.assign(person5, {name : 'dugksl23', sex:'man'})
console.log(person7);

// ==> 전달하는 객체들의 property를 다 복사한다.
//     다만 동일한 properties가 존재할 때에는 마지막으로 return 값을 덮어쓴다.