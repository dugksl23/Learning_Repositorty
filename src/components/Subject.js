import React, { Component } from "react";

// 1. 서브젝트 component 생성
class Subject extends Component {
  // js는 function 선언시, function keyword가 필요하다.
  // 다만, class의 멤버메소드에서는 ec6부터 생략 가능.
  render() {
    return (
      // react의 컴포넌트의 특징 : 하나의 최상위 태그로 시작한다.
      <header>
        <h1>
          <a href="/">{this.props.title}</a>
        </h1>
        {this.props.sub}
      </header>
    );
  }
}

export default Subject;
