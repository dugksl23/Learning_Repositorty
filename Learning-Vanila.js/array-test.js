'use strict';

// 1. Q1. make a string out of an array.
{
    const fruits = [' apple', 'banana', 'orange'];
    // A = join()
console.log(fruits.join()); 
}

// 2. make this array look like this [5,4,3,2,1]
{
    const array = [1, 2, 3, 4, 5]
    console.log(Array.from(array).reverse());
    console.log("원본" + array);
    // a - reverse()
    //     reverse() 는 배열의 원본데이터를 변경하기에 immutable function을 이용해야한다.

}

// 3. make an array out of a string.
{
    const fruits = 'apple, banana';
    console.log(fruits.split(','));
    // a - split은 return을 []로 한다.
}

// 4. make new array without the first two elements.
{
    const arr = new Array();
    arr.push('first');
    arr.push('two');
    console.log(arr);

    const array = [1, 2, 3, 4, 5];
    //console.log(array.splice(0, 2));
    // splice는 원본 데이를 제거한다. return 값은 삭제된 값들이 return 된다.
    let test = array.slice(2, array.length+1)
    console.log(test);
    // slice는 원본데이터가 아닌, 유사 배열을 만들어서
    // 포를 떠내듯이 축출할 부분을 선택할 수있따.
    // 첫번쨰 인자 : start
    // 두번쨰 인자 : end// 그러나 마지막 자릿수가는 배제되기에 length+1을 해야한다.
    console.log(array);
}

//=========================================================

class Student{
    constructor(name, age, enrolled, score) {
        this.name = name;
        this.age = age;
        this.enrolled = enrolled;
        this.score = score;
    }
};

const student = [
    new Student('A', 20, true, 45),
    new Student('B', 30, false, 55),
    new Student('C', 40, true, 65),
    new Student('D', 50, false, 75),
    new Student('E', 60, true, 90)
    
];


// 5. find a student with the score 90
// find 함수는 조건식이 성립하면 true를 return 한다.
//  false면 다음 구문으로 넘어간다.
//  또한 첫번째 조건식이 일치할 때도 바로 true하고 종료된다.
//  ** arrow 함수는 조건식이 true면 return한다.

const std1 = {};
student.forEach(std => Object.assign(std1, std))
console.log(std1);

const std2 = student.find((std, index) => std.score === 90)
console.log(std2);

// 6. make an array of enrolled students;
// map은 각 element들에 대한 callback function을 호출한다.
// 그리고 해당 eleement의 조건식에 해당하는 것만 return 한다.
// 따라서 해당 배열에 전체를 return 하려면, 따로 RETURN을 정의해줘야 한다.
const std3 = student.map(std => {
    if (std.enrolled === true) {
        return std;
    }
    return;
});
console.log(std3);


// ==> 그에 반해 FILTER를 조건문에 해당하는 배열만을 RETURN 해서 더 정확성이 높다.
const std5 = student.filter(std => std.enrolled === true);
console.log(std5);


// 07. make an array containing only the student scores
// return should be [45,80,90, 66, 88]

//* forEach 는 각각의 배열에 대한 callback 함수를 수행하지만, return은 하지 않는다. 
const score = [];
student.forEach(std => {
    score.push(std.score);
})

console.log(score);


// *map
// map은 조건식에 해당하는 element를 return 하는 것이 아닌,
// 모두 접근해서 return 한다. 따라서 점수만 return 하는 것이기에
// std.scrore로 해야 한다.
const score1 = student.map(std => std.score);
console.log(score1);

// 08. check if there is a student with the score lower than 50;
// *some - 배열에 요수중에서 callback 함수 중에서 return이 true인지 아닌지를 확인하는 함수이다.

console.clear();
const result = student.some(std => std.score < 50);
console.log(result);

// 09. 50점을 모두 넘는다면 true, 아니라면 false를 return 해라.

const allTrue = student.every(std => std.score > 50);
console.log(result);

// 09 compute students average score

