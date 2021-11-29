//1. object ����

const person1 = {}; //object riteral
const person = new Object(); // object constructor syntax
const person3 = { name: 'ee', age: 2 };


person3.name = 'eeeeee';
console.log(person3.name);

// 2. object�� ���� �߰�

person3.hasJob = true;
console.log(person3.hasJob);
person3['hasJob1'] = true;
console.log(person3.hasJob1);


// 3. object delete

delete person3.hasJob;
console.log(person3);


// 4. comuted properties = ���� ����
// * key should be always string
console.log(person3.name); // dot���� ����
console.log(person3['name']); //[]�� �ε��� �������� key ��(������)�� �ѱ�� �ȴ�.


// �� �̷� ���ٹ��� ���̰� ������?
// ==> dot(.)�� ���� ���� ���� key ���� ���� value ���� �˾Ƴ����� �� ���̴�.
//      []�� ���� keyString�� ���ٹ��� key ���� �ִ��� ������ �˱� ���� ���̱⵵ �ϴ�.
//      ������ dot�� ���� ������ ������, �ǽð� key ���� �ο��� ������ []�� ���� keyString�� �Ǵ�.


// 4-1 tokeny key�� �޾Ƽ� login�� �õ��Ϸ��� �� ����, keyString properties�� ����ϸ� �ȴ�.
function printVlue(obj, key) {
    console.log(obj[key]);//keyString������ ���� ����.
    console.log(obj.key); //dot������ ����
    // ==> obj.key�� �������� �ʴ� ��ü�� dot�� ���� key ������ �����߱⿡ undefined�� ���.
}
printVlue(person3, 'name');


// 5. Property value shorthand
// *Funtion�� ���� class ���ø�
//  class or object or ��ü�� ���� ������ ù���ڴ� �빮�ڷ� ��ӵǾ��� �ִ�.

class Person {
    constructor(name, age) {
        //���⼭ ������ ���� constructor�� ����
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
// propertioes�� �����ϴ��� ���� Ȯ��
// (key in obj)
// ture or false�� return �Ѵ�.

console.log('name' in person4);

// 7. for in  and for of
//    object���� ��� key�� ������.
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

// 9. �޸� ����
//  heap - ��ü�� ������/ �Ҵ� �Ǵ� �����Ǵ� ����
//  stack - ��������, �Ű�����
//  data - ���������� static
//  code - �츮�� ���� code



// 10. �迭���� �� ����
// old way

const person5 = {};
for (key in person3) {
    person5[key] = person3[key];
    console.log(person5);
}


// 11. fucntion closing
//  ù���� ���ڴ� ����迭�� return ���� ��ü��
//  return ���� ��ü�� �ִٸ�, 1���� optinal 
//  �ι�°�� ������ ���.
const person6 = Object.assign(person5);
console.log(person6);



// 12. Object.assign �Լ��� �̿��Ͽ�, ���� ��ü�� �����ϱ�.

const person7 = Object.assign(person5, {name : 'dugksl23', sex:'man'})
console.log(person7);

// ==> �����ϴ� ��ü���� property�� �� �����Ѵ�.
//     �ٸ� ������ properties�� ������ ������ ���������� return ���� �����.