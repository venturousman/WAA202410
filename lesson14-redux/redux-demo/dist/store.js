"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const redux_1 = require("redux");
const pizza_reducer_1 = __importDefault(require("./pizza-reducer"));
const store = (0, redux_1.createStore)(pizza_reducer_1.default);
exports.default = store;
