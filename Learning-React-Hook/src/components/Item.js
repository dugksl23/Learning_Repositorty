const Item = (props) => {
  const { item } = props;

  return <li data-id={item.id}>{item}</li>;
};

export default Item;
