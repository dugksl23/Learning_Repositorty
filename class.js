'use strict';


// 1. class 생성
class Person {

    // 2. constructor 생성
    //  object를 만들 때 생성자를 통해서 data를 전달한다.
    constructor(name, age) {
        //3. 멤버 fiels
        this.name = name;
        this.age = age;
    }

    // 4. getter and setter 생성
    get age() {
        return this._age;
        // this.age를 호출하는 순간, 메모리에 올라가 있는 data를 읽어오는 것이 아니다.
        // 바로 본인 변수명의 getter를 읽어오는 것이다. 
        // 즉 this.age라고 하면, getter의 함수 호출을 의미한다.
        // 본인 자신을 가리키는 this이기에 본인을 호출하게 되는 재귀 함수의 역할을 한다.
    }

    set age(age) {
        this._age = age;
        // 마찬가지로 setter 또한, 인자로 받은 값을 age = age; 라고 할당하는 순간, 
        // 바로 메모리의 값을 할당하는 것이 아니다.
        // age라는 변수는 setter 자신을 호출을 의미하게 된다.
        // 즉 본인 함수를 호출해서, 해당 함수에 호출하는 재귀이다.
        // 결코 stack 메모리에 올라간 멤버변수의 value를 update 하는 것이 아니다.
        //==> getter와 setter에서 this.변수명을 달리 해준다.
        //ex) this._age

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
ellie.speak();


class User {
    constructor(firstName, lastName, age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    get age() {
        return this._age;
    }

    set age(age) {
        console.log(age);
        this._age = age > 0 ? age : new Error('0살 이상 입력해주세요.');
    }

    get firstName() {
        return this._firstName;
    }

    set firstName(firstName) {
        this._firstName = firstName;
    }

    get lastName() {
        return this._lastName;
    }

    set lastName(lastName) {
        this._lastName = this.lastName;
    }

}

const user1 = new User('steve', 'Job', -1);
console.log(user1.age);

// ===> 생성자 + getter 및 setter
//=============================

// 2. 접근 제한자
// public private은 java와 똑같이 사용 가능, 다만 typeScript문법으로만 사용 가능하다.

class Experiment {

    privateName; // 변수 선언시, default는 public이지만,
    privateAge; // #를 붙이면 private이 된다.

    constructor(name, age) {
        this.privateAge = age;
        this.privateName = name;
    }

    get privateAge() {
        return this.privateAge;
    }

    set privateAge(age) {
        this.privateAge = age;
    }
}

const experiment = new Experiment('yohan', 1);
//console.log(experiment.privateAge);


// 3. static
//    공통적으로 자주 쓰이는 함수의 경우에는 static을 사용한다.
//    static은 object마다 값이 할당되는 것이 아니라,
//    이미, class 자체에 static이 붙어있기 때문이다.
//    값이 할당되지도 않고, 즉시실행을 의미한다.
//    따라서 class의 이름을 통해 해당 변수에 접근 및 출력 가능하다.
// ===> object에 상관없이 들어오는 data와 상관없이
//      공통적으로 자주 클래스라면, static 변수와 함수를 작성하면 된다.

class Article {

    static publisher = 'Dream Coding';

    constructor(articleNumber) {
        this.articleNumber = arguments;
    }

    static printPublisher() {
        console.log(this.publisher);
    }
}


const publisher = new Article(0);
console.log(publisher.publisher);
Article.printPublisher();


// 4. 다형성과 상속

class shape {

    constructor(width, height, color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    draw() {
        console.log(`drawing ${this.color} color!`);
    }

    getArea() {
        return this.width & this.height;
    }

}

class Rectangle extends shape {};
class Triangle extends shape {
    // 4-1 다형성의 overriding(재정의)
    getArea() {
        return (this.height * this.width) / 2;
    }

    draw() {
        // 4-2 ovveridng 한 부모 메소드를 호출   
        console.log('세모');
        super.draw(); //부모의 draw를 호출
    }
};

const rectangle = new Rectangle(100, 50, 'red');
console.log(rectangle.getArea());

const tryangle = new Triangle(100, 50, 'BLUE');
console.log(tryangle.getArea());
console.log(tryangle.draw());


// 5. Class checking : instanceof
//    선언된 객체(instance)가 해당 class의 object인지 본다.
//    return은 true와 false를 return한다.

console.log(`${rectangle instanceof Rectangle}`); // true
console.log(`${rectangle instanceof shape}`); // true
console.log(`${rectangle instanceof Triangle}`); // true
console.log(`${rectangle instanceof Object}`); // true
// * 모든 class는 object를 상속받아서 구현되었기에, 최상위 부모 클래스라고 하고 있다.


// 참조사이트 - javascript reference
//  object 및 자료구조와 알고리즘을 확인할 수 있다.




class calculator {

    constructor(command, a, b) {
        this.command = command;
        this.a = a;
        this.b = b;
    }

    calculator() {
        switch (this.command) {
            case 'add':
                return this.a + this.b;
            case 'multifly':
                return this.a * this.b;
            default:
                throw Error('연산이 아닙니다.');
        }
    }
}

const calculator1 = new calculator('--', 1, 2);
console.log(calculator1.calculator());