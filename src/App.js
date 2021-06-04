import React, { useEffect, useState } from "react";
import List from "./components/ListHook";

const App = () => {
  const [personInfo, setPersonInfo] = useState({ name: "요한", age: 20 });
  const [learning, setLearning] = useState(["node.js"]);

  const [inputData, setInputData] = useState([]); // useState도 초기값을 주어야 한다.
  const [fetchData, setFetchData] = useState([]); // useState도 초기값을 주어야 한다.
  const [loading, setLoading] = useState(false); // useState도 초기값을 주어야 한다.

  const changeInputData = (e) => {
    e.preventDefault();
    setInputData([...inputData, e.target.text.value]); // ... 이것은 값을 배열로 받겠다는 의미를 가진다.
  };

  useEffect(() => {
    // ex) 로그인 후의 후속작업이 필요할 때, 추가 로직을 작성할 떄 사용 가능하다.
    console.log("새로운 내용이 렌더링 됐어요." + inputData);
  }, []);

  const fetchingData = async () => {
    setLoading(true);
    setTimeout(() => {
      setInputData(["우왕"]);
      setLoading(false);
    }, 2000);
    console.log(loading);
  };

  useEffect(() => {
    fetchingData();
  }, []); //rendering이 되면, useEffect가 불리고, useEffect 내의 함수인 setState 함수 때문에
  // 다시 rendering이 걸리나는 무한루프이다. 따라서 검사할 항목이 없음을 나타낼 [](null)을 지정해줘야한다.

  return (
    <>
      <h1> toDo Application 향상</h1>
      <form onSubmit={changeInputData} action="/">
        <input type="text" name="text" placeholder="text"></input>
        <button type="submit">할일 추가</button>
      </form>

      <List
        personInfo={personInfo}
        learning={learning}
        inputData={inputData}
        fetchData={fetchData}
        lodaing={loading}
      ></List>
    </>
  );
};
export default App;

// const request = {
//   headers: {
//     "Content-Type": "application/json",
//     Accept: "application/json",
//   },
// };
// fetch("http://localhost:80/fetchData", request)
//   .then((res) => {
//     res.json();
//   })
//   .then((data) => {
//     console.log(JSON.stringify(data));
//   });
//setFetchData(response.json()); // fetching data를 initinal state 값으로 설정한다. 여기서는 inputData에 넣어보자.
