import Item from "./Item";
const List = (props) => {
  let { personInfo } = props; // 구조 분해 할당
  let { learning } = props; // 구조 분해 할당
  let { inputData } = props;
  let { loading } = props;
  let load = <li>loading...</li>;

  const personsInfo = Object.entries(personInfo).map((info) => {
    return <li key={info.id}>{info}</li>;
  });
  const toLearning = learning.map((toDo) => {
    return <li key={toDo.id}>{toDo}</li>;
  });

  if (!loading) {
    load = inputData.map((datas) => <Item key={datas.id} item={datas}></Item>);
  }

  const data1 = inputData.map((data) => {
    <li key={data.id}>{data}</li>;
  });

  /// servive 영역이다.
  // 아래의 return은 말그대로 html을 만들어줌과 동시에 데이터를 뿌려준다.(data에는 component도 포함이 되어있을 수도 있고, data가 있을 수도 있다.
  // ==> servlet --> jsp 으로 바꾸는 형식이다.
  return (
    <ul>
      {personsInfo}
      {toLearning}
      {load}
      {data1}
    </ul>
  );
};
export default List;
