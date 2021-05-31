import "./App.css";
import React, { Component } from "react";
import TOC from "./components/TOC";
import Content from "./components/content";
import Subject from "./components/Subject";

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

class App extends Component {
  render() {
    return (
      // div className>태그 안에 태그를 생성한다. => SPA
      <div className="App">
        <Subject title="HTML" sub="hellow wrold"></Subject>
        <Subject title="React" sub="hellow react"></Subject>
        <TOC></TOC>
        <Content title="HTML" desc="HTML is HyperText MarkUp"></Content>
      </div>
    );
  }
}

export default App;
