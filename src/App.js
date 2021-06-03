import React, { useState } from "react";
import List from "./components/ListHook";

const App = () => {
  const [personInfo, setPersonInfo] = useState({ name: "요한", age: 20 });
  const [learning, setLearning] = useState(["node.js"]);

  const [inputData, setInputData] = useState([]); // useState도 초기값을 주어야 한다.
  const changeInputData = (e) => {
    e.preventDefault();
    setInputData([e.target.text.value]);
  };

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
