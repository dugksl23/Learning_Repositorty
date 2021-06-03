import React, { Component } from "react";
// 1. Component 별로 분리하기 위해서 위의 import는 필수!!

class TOC extends Component {
  shouldComponentUpdate(newProps, newState) {
    // if (
    //   newProps.data.title !== this.props.data.title ||
    //   newProps.data.desc !== this.props.data.desc
    // ) {
    //   return true;
    // }
    // ==> 상기의 코드와 같은 예시로 fetch를 통해서 db로부터 data를 조회하여
    //     update 사실에 대한 것을 확인해야 한다.

    console.log("newPropsdata : ", newProps.data);
    console.log(`thisState data :`, this.props.data);
    if (newProps.data.length !== this.props.data.length) {
      return true;
    }

    return true; // 기본값은 false로...
  }

  render() {
    console.log("toc render");
    var lists = [];
    var data = this.props.data;
    //app.js(class)의 생성자를 통해, 자식 component의 props에 값을 주입했다.
    // 다만 배열로 이루어진 값들은 자식 component 내부에서 처리해줘야 한다.
    let i = 0;
    while (i < data.length) {
      lists.push(
        <li key={data[i].idx}>
          <a
            data-id={data[i].idx}
            href={"/content/" + data[i].idx}
            onClick={function (num1, num2, e) {
              this.props.onChangePage(e.target.dataset.id);
              e.preventDefault();
            }.bind(this, "1", "2")}
          >
            {data[i].title}
          </a>
        </li>
      );
      i++;
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
