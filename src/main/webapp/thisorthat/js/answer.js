'use strict';

var Answer = React.createClass({
    displayName: "Answer",
    render: function () {
        var classes = classNames({
            'answer': true,
            'answer-correct bg-success': this.props.isAnswerCorrect,
            'answer-incorrect bg-danger': !this.props.isAnswerCorrect
        });
        var img = "./img/" + this.props.question.img;
        var name = this.props.question.name;
        var url = this.props.question.url;
        var text = this.props.question.text;
        var type = this.props.question.type === IS_THAT ? Game.thatTitle : Game.thisTitle;

        return (
            React.createElement("div", {className: classes},
                React.createElement("h1", {className: "answer-name"},
                    React.createElement("a", {href: url, target: "_blank"}, name), " is ", type, "!"
                ),
                React.createElement("div", {className: "answer-picture-wrap"}, React.createElement("img", {
                    className: "answer-picture",
                    src: img,
                    alt: name
                })),
                React.createElement("div", {className: "answer-text"}, text),
                React.createElement("div", {className: "answer-next"},
                    React.createElement("button", {
                        className: "btn btn-lg btn-primary answer-button-next",
                        onClick: this.props.nextQuestion
                    }, "Next question")
                )
            )
        )
    }
});

