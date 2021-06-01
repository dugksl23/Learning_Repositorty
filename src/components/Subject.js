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
          <a
            href="/"
            onClick={(e) => {
              console.log(e);
              e.preventDefault();
              //debugger;
              // 이벤트가 주입이 되고, e 변수에 할당된 이벤트를 핸들링할 수 있도록 주입된 정보이다.
              // e.preventDefault(); // 이벤트가 할당된 태그의 원래 기능을 하지 못하도록 하는 함수이다.
            }}
          >
            {this.props.title}
          </a>
        </h1>
        {this.props.sub}
      </header>
    );
  }
}

export default Subject;
