import React, { useState } from "react";
import List from "./components/ListHook";

const App = () => {
  const [personInfo, setPersonInfo] = useState({ name: "요한", age: 20 });
  const [learning, setLearning] = useState(["node.js"]);

  return (
    <>
      <h1> toDo Application 향상</h1>
      <form action="/">
        <input type="text" name="text" placeholder="text"></input>
        <button type="submit">할일 추가</button>
      </form>

      <List personInfo={personInfo} learning={learning}></List>
    </>
  );
};
export default App;
