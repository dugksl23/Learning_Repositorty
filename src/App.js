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
      mode: "welcome",
      subject: { title: "안녕하세요", sub: "world Wid Web!" },
      content: [
        {
          idx: 1,
          title: "나는 content 입니다. 3번",
          desc: "desc라... HTML is HyperText MarkUp",
        },
        {
          idx: 2,
          title: "나는 content 입니다. 2번",
          desc: "desc라... HTML is HyperText MarkUp",
        },
        {
          idx: 3,
          title: "나는 content 입니다. 4번",
          desc: "desc라... HTML is HyperText MarkUp",
        },
      ],
    };
  }

  render() {
    console.log("APP Render");

    let _title, _desc;

    if (this.state.mode === "welcome") {
      _title = this.state.content[0].title;
      _desc = this.state.content[0].desc;
    } else if (this.state.mode === "read") {
      _title = this.state.content[0].title;
      _desc = this.state.content[0].desc;
    }

    return (
      // div className>태그 안에 태그를 생성한다. => SPA
      <div className="App">
        <a
          href="/"
          onClick={function (e) {
            console.log(e);
            this.setState({
              mode: "read",
            });

            e.preventDefault();
          }.bind(this)}
        >
          read
        </a>

        <Subject
          title={this.state.subject.title}
          sub={this.state.subject.sub}
          onChangePage={function () {
            //e.preventDefault();
            this.setState({
              mode: "read",
            });
          }.bind(this)}
        ></Subject>

        <Subject title="React" sub="hellow react"></Subject>
        <TOC data={this.state.content}></TOC>
        <Content title={_title} desc={_desc}></Content>
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
