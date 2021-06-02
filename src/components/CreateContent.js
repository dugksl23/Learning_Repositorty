import React, { Component } from "react";

class CreateContent extends Component {
  render() {
    return (
      <article>
        <h2>Create</h2>
        <form
          action="/createProc"
          method="post"
          onSubmit={(e) => {
            e.preventDefault();
            this.props.onSubmit(e.target.title.value, e.target.desc.value);
          }}
        >
          <p>
            <input type="text" name="title" placeholder="title"></input>
          </p>
          <p>
            <textarea id="desc" name="desc" placeholder="desc"></textarea>
          </p>
          <p>
            <input type="submit" value="submit"></input>
          </p>
        </form>
      </article>
    );
  }
}

export default CreateContent;
