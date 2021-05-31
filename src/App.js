import "./App.css";
import React, { Component } from "react";
import TOC from "./components/TOC";
import Content from "./components/content";
import Subject from "./components/Subject";

class App extends Component {
  // 1. class의 생성자를 통해, state로 값을 받아서 props에 전달하기
  //    component(class)가 실행이 되면, constructor가 실행이 되면서
  //    입력값/멤버변수(props)에 대해서 초기화가 이루어진다.
  constructor(props) {
    super(props);
    this.state = {
      Subject: { title: "안녕하세요", sub: "world Wid Web!" },
    };
  }

  render() {
    return (
      // div className>태그 안에 태그를 생성한다. => SPA
      <div className="App">
        <Subject
          title={this.state.Subject.title}
          sub={this.state.Subject.sub}
        ></Subject>
        <Subject title="React" sub="hellow react"></Subject>
        <TOC></TOC>
        <Content title="HTML" desc="HTML is HyperText MarkUp"></Content>
      </div>
    );
  }
}

export default App;

// // 1. 서브젝트 component 생성
// class Subject extends Component {
//   // js는 function 선언시, function keyword가 필요하다.
//   // 다만, class의 멤버메소드에서는 ec6부터 생략 가능.
//   render() {
//     return (
//       // react의 컴포넌트의 특징 : 하나의 최상위 태그로 시작한다.
//       <header>
//         <h1>{this.props.title}</h1>
//         {this.props.sub}
//       </header>
//     );
//   }
// }

// function App() {
//   return (
//     // div className>태그 안에 태그를 생성한다. spa
//     <div className="App">
//       <Subject></Subject>
//     </div>
//   );
// }
