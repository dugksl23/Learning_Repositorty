const List = (props) => {
  let { personInfo } = props; // 구조 분해 할당
  let { learning } = props; // 구조 분해 할당

  const personsInfo = Object.entries(personInfo);
  const toLearning = learning.map((toDo) => {
    return <li>{toDo}</li>;
  });

  return (
    <ul>
      {personsInfo}
      {toLearning}
    </ul>
  );
};
export default List;
