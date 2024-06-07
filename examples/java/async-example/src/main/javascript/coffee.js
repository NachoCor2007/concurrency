"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var node_console_1 = require("node:console");
var coffeeNumber = 0;
function makeCoffeePromise() {
    return new Promise(function (resolve) {
        node_console_1.console.log("Making Coffee");
        setTimeout(function () {
            coffeeNumber += 1;
            resolve("coffee ".concat(coffeeNumber));
        }, 2000);
    });
}
function drink(coffee) {
    node_console_1.console.log("Drinking ".concat(coffee));
}
function chatWithColleagues() {
    node_console_1.console.log("Chatting with colleagues");
}
function coffeeBreak() {
    var f = makeCoffeePromise();
    f.then(function (coffee) {
        drink(coffee);
    }).catch(function (exception) {
        node_console_1.console.log("Failed with ".concat(exception));
    });
    chatWithColleagues();
}
for (var i = 0; i < 3; i++) {
    coffeeBreak();
}
