'use strict';


// 1. class 생성
class Person {

    // 2. constructor
    //  object를 만들 때 생성자를 통해서 data를 전달한다.
    constructor(name, age) {
        //3. 멤버 fiels
        this.name = name;
        this.age = age;
    }
    
    get age() {
        return this._age;
        // this.age를 호출하는 순간, 메모리에 올라가 있는 data를 읽어오는 것이 아니다.
        // 바로 getter를 읽어오는 것이다. 즉 this.age라고 하면, getter의 함수 호출을 의미한다.
        // 본인 자신을 가리키는 this이기에 본인을 호출하게 되는 재귀 함수의 역할을 한다.
    }

    set age(age) {
        this.age = _age;
        // 마찬가지로 setter 또한, 인자로 받은 값을 
        // age = age; 라고 할당하는 순간, 바로 메모리의 값을 할당하는 것이 아니다.
        // age라느 변수는 setter 호출을 의미하게 된다.
        // 즉 본인 함수를 호출해서, 해당 함수에 호출하는 재귀이다.
        // 결코 stack 메모리에 올라간 멤버변수의 value를 update 하는 것이 아니다.
        //==> getter와 setter의 변수명을 달리 해준다

    }


    // getter and setter



    // 3. 멤버 메소드
    speak() {
        console.log(`this name is ${this.name}`);
    } 
}

// object 생성 (객체 생성)
const ellie = new Person('ellie', 20);
console.log(ellie.name);
console.log(ellie.age);
ellie.speak;
