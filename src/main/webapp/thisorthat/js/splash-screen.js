'use strict';

var SplashScreen = React.createClass({
    displayName: "SplashScreen",
    render: function () {
        return (
            React.createElement("div", {className: "splashscreen"}, "Is it " + Game.thatTitle + " or " + Game.thisTitle + " ?")
        )
    }
});

