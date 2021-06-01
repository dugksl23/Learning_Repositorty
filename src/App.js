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
      selected_contents_id: 1,
      mode: "welcome",
      subject: { title: "안녕하세요", sub: "world Wide Web!" },
      content: [
        {
          idx: 1,
          title: "나는 HTML 입니다. 1번",
          desc: "HTML is HyperText MarkUp",
        },
        {
          idx: 2,
          title: "나는 CSS 입니다. 2번",
          desc: "desc라... csss is HyperText MarkUp",
        },
        {
          idx: 3,
          title: "나는 JavaScript 입니다. 3번",
          desc: "desc라... javascript is HyperText MarkUp",
        },
      ],
    };
  }

  render() {
    console.log("APP Render");

    let _title, _desc;

    if (this.state.mode === "welcome") {
      _title = "welcome";
      _desc = "welcome desc";
    } else if (this.state.mode === "read") {
      let i = 0;
      while (i < this.state.content.length) {
        let data = this.state.content[i];
        if (data.idx === this.state.selected_contents_id) {
          _title = data.title;
          _desc = data.desc;
          break;
        }
        i++;
      }
    }

    return (
      // div className>태그 안에 태그를 생성한다. => SPA
      <div className="App">
        <a
          href="/"
          onClick={(e) => {
            console.log(e);
            e.preventDefault();
            this.setState({
              mode: "read",
            });
          }}
        >
          this test
        </a>

        <Subject
          title={this.state.subject.title}
          sub={this.state.subject.sub}
          onChangePage={(e) => {
            this.setState({
              mode: "read",
            });
          }}
        ></Subject>

        <Subject
          onChangePage={(e) => {
            alert("hi");
          }}
          title="React"
          sub="hellow react"
        ></Subject>

        <TOC
          onChangePage={(id) => {
            this.setState({
              mode: "read",
              selected_contents_id: Number(id),
            });
          }}
          data={this.state.content}
        ></TOC>

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
