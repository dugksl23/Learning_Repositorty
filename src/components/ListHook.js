const List = (props) => {
  let { personInfo } = props; // 구조 분해 할당
  let { learning } = props; // 구조 분해 할당
  let { inputData } = props;
  let { loading } = props;

  const personsInfo = Object.entries(personInfo).map((info) => {
    return <li key={info.id}>{info}</li>;
  });
  const toLearning = learning.map((toDo) => {
    return <li key={toDo.id}>{toDo}</li>;
  });
  let load = "loading...";
  let abc;
  if (!loading) {
    abc = inputData.map((datas) => <li key={datas.id}>{datas}</li>);
  }

  const data1 = inputData.map((data) => {
    <li key={data.id}>{data}</li>;
  });
  return (
    <ul>
      {personsInfo}
      {toLearning}
      {load}
      {abc}
      {data1}
    </ul>
  );
};
export default List;
