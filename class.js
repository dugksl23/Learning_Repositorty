'use strict';


// 1. class ����
class Person {

    // 2. constructor
    //  object�� ���� �� �����ڸ� ���ؼ� data�� �����Ѵ�.
    constructor(name, age) {
        //3. ��� fiels
        this.name = name;
        this.age = age;
    }
    
    get age() {
        return this._age;
        // this.age�� ȣ���ϴ� ����, �޸𸮿� �ö� �ִ� data�� �о���� ���� �ƴϴ�.
        // �ٷ� getter�� �о���� ���̴�. �� this.age��� �ϸ�, getter�� �Լ� ȣ���� �ǹ��Ѵ�.
        // ���� �ڽ��� ����Ű�� this�̱⿡ ������ ȣ���ϰ� �Ǵ� ��� �Լ��� ������ �Ѵ�.
    }

    set age(age) {
        this.age = _age;
        // ���������� setter ����, ���ڷ� ���� ���� 
        // age = age; ��� �Ҵ��ϴ� ����, �ٷ� �޸��� ���� �Ҵ��ϴ� ���� �ƴϴ�.
        // age��� ������ setter ȣ���� �ǹ��ϰ� �ȴ�.
        // �� ���� �Լ��� ȣ���ؼ�, �ش� �Լ��� ȣ���ϴ� ����̴�.
        // ���� stack �޸𸮿� �ö� ��������� value�� update �ϴ� ���� �ƴϴ�.
        //==> getter�� setter�� �������� �޸� ���ش�

    }


    // getter and setter



    // 3. ��� �޼ҵ�
    speak() {
        console.log(`this name is ${this.name}`);
    } 
}

// object ���� (��ü ����)
const ellie = new Person('ellie', 20);
console.log(ellie.name);
console.log(ellie.age);
ellie.speak;
