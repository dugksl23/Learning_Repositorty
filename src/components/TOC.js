import React, { Component } from "react";
// 1. Component 별로 분리하기 위해서 위의 import는 필수!!

class TOC extends Component {
  render() {
    var lists = [];
    var data = this.props.data;
    //app.js(class)의 생성자를 통해, 자식 component의 props에 값을 주입했다.
    // 다만 배열로 이루어진 값들은 자식 component 내부에서 처리해줘야 한다.
    for (let i = 0; i < data.length; i++) {
      lists.push(
        <li key={data[i].idx}>
          <a href={"/content/" + data[i].idx} />
          {data[i].title}
        </li>
      );
    }

    return (
      <nav>
        <ul>{lists}</ul>
      </nav>
    );
  }
}

//2. 외부에서 사용할 수 있게금 export해준다.
export default TOC;
