import "./App.css";
import React, { Component } from "react";

// 1. 서브젝트 component 생성
class Subject extends Component {
  // js는 function 선언시, function keyword가 필요하다.
  // 다만, class의 멤버메소드에서는 ec6부터 생략 가능.
  render() {
    return (
      // react의 컴포넌트의 특징 : 하나의 최상위 태그로 시작한다.
      <header>
        <h1>WEB</h1>
        hellow world
      </header>
    );
  }
}

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

class Content extends Component {
  render() {
    return (
      <article>
        <h2>HTML</h2>
        HTML is ~~
      </article>
    );
  }
}

class App extends Component {
  render() {
    return (
      // div className>태그 안에 태그를 생성한다. => SPA
      <div className="App">
        <Subject></Subject>
        <TOC></TOC>
        <Content></Content>
      </div>
    );
  }
}

// function App() {
//   return (
//     // div className>태그 안에 태그를 생성한다. spa
//     <div className="App">
//       <Subject></Subject>
//     </div>
//   );
// }

export default App;
