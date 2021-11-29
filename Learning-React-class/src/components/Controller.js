import React, { Component } from "react";

class Controller extends Component {
  render() {
    return (
      <ul>
        <li>
          <a
            href="/create"
            onClick={(e) => {
              e.preventDefault();
              this.props.onChangeMode("create");
            }}
          >
            create
          </a>
        </li>
        <li>
          <a
            href="/read"
            onClick={(e) => {
              e.preventDefault();
              this.props.onChangeMode("read");
            }}
          >
            read
          </a>
        </li>
        <li>
          <input
            type="button"
            value="delete"
            onClick={(e) => {
              e.preventDefault();
              this.props.onChangeMode("delete");
            }}
          ></input>
        </li>
        <li>
          <a
            href="/update"
            onClick={(e) => {
              e.preventDefault();
              this.props.onChangeMode("update");
            }}
          >
            update
          </a>
        </li>
      </ul>
    );
  }
}

export default Controller;
