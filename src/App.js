import React, { useEffect, useState } from "react";
import List from "./components/ListHook";

const App = () => {
  const [personInfo, setPersonInfo] = useState({ name: "요한", age: 20 });
  const [learning, setLearning] = useState(["node.js"]);

  const [inputData, setInputData] = useState([]); // useState도 초기값을 주어야 한다.

  const changeInputData = (e) => {
    e.preventDefault();
    setInputData([...inputData, e.target.text.value]); // ... 이것은 값을 배열로 받겠다는 의미를 가진다.
  };

  useEffect(() => {
    // ex) 로그인 후의 후속작업이 필요할 때, 추가 로직을 작성할 떄 사용 가능하다.
    console.log("새로운 내용이 렌더링 됐어요." + inputData);
  }, [inputData]);

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
      ></List>
    </>
  );
};
export default App;
