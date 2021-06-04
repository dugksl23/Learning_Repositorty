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

  let load = <div>loading...</div>;

  if (loading !== false) {
    load = inputData.map((datas) => {
      return <li key={datas.id}>{datas}</li>;
    });
  }

  return (
    <ul>
      {load}
      {personsInfo}
      {toLearning}

      {/* {fetchData} */}
    </ul>
  );
};
export default List;
