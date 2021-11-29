// 1. Increment and Decrement Operator


// PreIncrement Operator
// *������ ���� �̷������ �� �Ŀ� ���� �Ҵ��Ѵ�.
//  (count ������ ���� ������Ʈ �ϰ� ���Ŀ� �����Ѵ�.)

let counter = 2;
const preIncrement = ++counter;
// ++counter => counter = counter+1 // => 3;
// preIncrement = 3; // 3
// ���������� 3 ���

// PostIncrement Operator
// *������ ���� postIncrement ������ �Ҵ��� �ϰ�, count�� ���� ������Ų��.

let counter = 3;
const postIncrement = counter++;
//counter++ => postIncrement = counter;
// counter = counter + 1;
// 4 ���.


// 2. Comparison Operator

// *A�� B����(*�� ��������) �۰ų�, ũ�ų�, ����
console.log(10<6); //less than
console.log(10<=6) // less than or equal
console.log(10 > 6) // greater than
console.log(10 >= 6) // greater than or equal

// 3. Logical Operator
//  * || (or), && (and), ! (not)
const value1 = false;
const value2 = 4 < 2; // less than

function check() {
 for(let i = 0; i<10; i++) {
   console.log('waiting time~');
 }
 return true;
}


// 1. or ������
//    or �����ڴ� true�� ������ �������� ���߰�, true�� return �Ѵ�.
//    ���� or�� �� �� �ϳ��� true�� ������, true�� �ν��ϰ� ������ �����Ѵ�.
//    ���� ���, value 1 = true ��� �����Ѵٸ�, �Ʒ��� or ������ value 1���� 
//    true�� ����� ������ ����ȴ�.
 
console.log(`or : ${value1 || value2 || check()}`);  
// => check() �Լ����� true �̱⿡ true�� return�Ѵ�.

// * ���� or�� ����� ����, ������ ���� ���� �ҿ�Ǵ� expression�� 
//   ���� �ڿ� ��ġ�ؾ� �Ѵ�.


// 2. and ������
//    and�� ��� ������ true�� �Ǿ�� return�� �ȴ�.
//    ���� and �������� ù��° ���ǽ��� false�� ������, false�� return�ϰ�
//    ������ �����Ѵ�. ���� ������ �ҿ�Ǵ� expression�� �ڿ� ��ġ�ؾ� �Ѵ�.

const value1 = true;
const value2 = true;
function check() {
  return true;
}

console.log(`and : ${value1 && value2 && check()}`);


//3. !(not) ������
console.log(!value1) // false�� return


// 4. Equality
// * == (loose equality), === (strict equality)

// 1. == (loose equality)
// data type�� �ƴ�, value�� �������� ���Ͽ� ���� ���ٶ�� �Ѵٸ�,
// js engine�� ���� ���ٰ� �ν��� �ϰ�, ����(==)�� ������Ų��.

const stringFive = '5';
const numberFive = 5;

console.log(stringFive == numberFive);
// => true �� return
console.log(stringFive != numberFive);

// 2. === (strict equality)
// data type �� ���� value ���� �������� ��쿡 ����(===)�� return �Ѵ�.
// data type�� �ٸ� ��쿡��, false�� return �Ѵ�.

const numberFive = 5;
const number5 = 5;

console.log(`${numberFive === number5}`);
console.log(`${numberFive !== number5}`);
 


// 3. object equality by reference;
//    �Ʒ��� ������ ��ü�� ������ or �ּҰ��� ������.
//    ���� �ش� ��ü�� ������(�ּҰ�)�� ���� ���� stack memory�� ����� ������ �Ҵ�� ���� ã�Ƽ� �д´�.
//    ����������, ���� ���� ��ü�� ������(�ּҰ�)�� �������� ������ �Ѵ�.

const ellie1 = {name : 'ellie'};
const ellie2 = {name : 'ellie'};
const ellie3 = ellie1;

console.log(`${ellie1 == ellie2}`); // false
console.log(`${ellie1 === ellie2}`); // false
console.log(`${ellie1 === ellie3}`); // true


// => ù ��°, ���� �ٸ� reference(��ü ������)�� ���� ���� ���ϱ⿡ false �̴�.
// => �� ��°, ���� �ٸ� ��ü�� �������� ������ �ֱ⿡ false
// => �� ��°, ellie3�� ellie1�� �ּҰ��� �Ҵ�޾ұ⿡ ���� ��ü �ּҰ��� ������ �ֱ⿡ true.


// 5. Ternary Operator (3�� ������)?

console.log(name === 'ellie'? 'Yes':'No');
console.log(name === 'ellie' ? 'Yes' : name === 'Ellie' ? 'Yes' : 'No'); // nesting Ternary Opeartion
//*nesting Ternary Opeartion �� ��쿡�� �������� ���Ƽ�, if�� or switch ���� ����ϴ� ���� ����.

// 6. Switch
const browser = 'IE';
switch (browser){
  case 'IE':
        console.log('wow~');
        break;
  case 'Chrome':
  case 'Firefox':
        console.log('love you!');
        break;
  default : 
        console.log('bye~');
        break;

}

// => ���� ������ ������ ��쿡�� 'Chrome'�� 'Firefox' ó�� �ѵ� ���
//    ���� ������ case�� ������ �ۼ��ϸ� �ȴ�.


// 7. While loop

// 1. while loop

let i = 3;
while(i>0){
  console.log(`while: ${i}`);
  i--;
}

// => 3,2,1�� ���
//    while loop�� ���ǹ��� ��ġ�� ����, �Ʒ� block�� ������ �����ϰڴٴ� ���̰� �ִ�.


//2. do ~ while loop
do {
  console.log(`while : ${i}`);
  i--;
} while(i > 0);

// => 0�� ���
//    �̹� while loop�� ���� i�� ���� 0�� �Ǿ���,
//    do while loop�� block �ȿ� ������ ���� ������, while loop�� ���ǽ��� ��ġ�Ѵٸ�
//    �ٽ� do {}�� �����Ѵٴ� ���̰� �ִ�.


// 8. for loop
// 1. for loop
//    for(begin; condition; (next)step)
//    begin�� ó������ ����Ǹ�, ���Ŀ� condition(���ǽ�)�� ��������, 
//    next step����  step�� ��ġ�� �ȴ�.
 
for(let i = 3; i>0; i--){
  console.log(`for : ${i}`);
}

//================================

// quiz 1

console.log(0 == false); // true 
console.log(0 === false); // false
console.log('' == false); // true
console.log('' === false); // false

// => 0, null, ''(emptyString)�� loose equality������ false�� ����.
//    �ٸ�, strict equality������ data type�� �����⿡ �񱳰� ���� �ʱ⿡ false�̴�. 

console.log(null == undefined); // true 
console.log(null === undefined); // false;

// => null�� undeifned�� Ư���ϰ� ����(loose equality)�� ��Ÿ���⿡ true�̴�.
//    �׷���, strict equality������ �翬�� data type�� �ٸ��Ƿ� false�̴�.

// quiz 2
// 1. 1���� 10�������� ¦���� ����ϼ���.

for(let i=0; i<=10; i++){
  if(i%2 != 0){
    continue;
  }
  console.log(`${i}`);
}


// 2. iterate from 0 to 10 and print out numbers until reaching 8 (use break)

let j = 0;
while (j<=10){
  j++;
  if (j > 8) { 
     break;
  } 
console.log(`${j}`);
}