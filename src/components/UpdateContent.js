import React, { Component } from "react";

class UpdateContent extends Component {
  render() {
    console.log("updateComponent render");
    console.log(this.props.data.title);
    return (
      <article>
        <h2>Create</h2>
        <form
          action="/updateProc"
          method="post"
          onSubmit={(e) => {
            e.preventDefault();
            this.props.onSubmit(
              this.props.idx,
              e.target.title.value,
              e.target.desc.value
            );
          }}
        >
          <p>
            <input
              type="text"
              name="title"
              placeholder={this.props.data.title}
            ></input>
          </p>
          <p>
            <textarea
              id="desc"
              name="desc"
              placeholder={this.props.data.desc}
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
