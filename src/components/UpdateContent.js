import React, { Component } from "react";

class UpdateContent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      idx: this.props.data.idx,
      title: this.props.data.title,
      desc: this.props.data.desc,
    };
  }

  inputFormHandler(e) {
    this.setState({
      [e.target.name]: e.target.value,
    });
  }

  render() {
    console.log("updateComponent render");
    return (
      <article>
        <h2>Create</h2>
        <form
          action="/updateProc"
          method="post"
          onSubmit={(e) => {
            e.preventDefault();
            this.props.onSubmit(
              this.props.data.idx,
              e.target.title.value,
              e.target.desc.value
            );
          }}
        >
          <input type="hidden" name="idx" value={this.state.idx}></input>
          <p>
            <input
              type="text"
              name="title"
              //placeholder={this.props.data.title}
              value={this.state.title}
              onChange={(e) => this.inputFormHandler(e)}
            ></input>
          </p>
          <p>
            <textarea
              id="desc"
              name="desc"
              value={this.state.desc}
              onChange={(e) => this.inputFormHandler(e)}
            ></textarea>
          </p>
          <p>
            <input type="submit" value="submit"></input>
          </p>
        </form>
      </article>
    );
  }
}

export default UpdateContent;
