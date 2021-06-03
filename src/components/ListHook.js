import { useEffect } from "react";

const List = (props) => {
  let { personInfo } = props; // 구조 분해 할당
  let { learning } = props; // 구조 분해 할당
  let { inputData } = props;

  const personsInfo = Object.entries(personInfo).map((info) => {
    return <li>{info}</li>;
  });
  const toLearning = learning.map((toDo) => {
    return <li>{toDo}</li>;
  });

  const input = inputData.map((datas) => {
    console.log(datas);
    return <li>{datas}</li>;
  });

  useEffect(() => {
    console.log("useEffect");
  });

  return (
    <ul>
      {personsInfo}
      {toLearning}
      {input}
    </ul>
  );
};
export default List;
