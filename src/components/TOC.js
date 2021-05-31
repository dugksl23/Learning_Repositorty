import React, { Component } from "react";
// 1. Component 별로 분리하기 위해서 위의 import는 필수!!

class TOC extends Component {
  render() {
    return (
      <nav>
        <ul>
          <li>
            <a href="1.html">HTML</a>
          </li>
        </ul>
      </nav>
    );
  }
}

//2. 외부에서 사용할 수 있게금 export해준다.
export default TOC;
