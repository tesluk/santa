'use strict';

var Question = React.createClass({displayName: "Question",
  selectThisAnswer: function selectThisAnswer() {
    this.props.selectAnswer(IS_THIS);
  },
  selectThatAnswer: function selectThatAnswer() {
    this.props.selectAnswer(IS_THAT);
  },
  render: function() {
    return (
      React.createElement("div", {className: "question"}, 
        React.createElement("h1", {className: "question-name"}, this.props.question.name), 
        React.createElement("ul", {className: "question-buttons"}, 
          React.createElement("li", null, React.createElement("button", {className: "btn btn-lg btn-default question-button-that", onClick: this.selectThisAnswer}, Game.thisTitle)),
          React.createElement("li", null, React.createElement("button", {className: "btn btn-lg btn-default question-button-this", onClick: this.selectThatAnswer}, Game.thatTitle))
        )
      )
    )
  }
});

