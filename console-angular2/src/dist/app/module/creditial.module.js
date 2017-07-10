"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * Created by StevenWash on 2017/6/14.
 */
var Credential = (function () {
    function Credential() {
    }
    Credential.prototype.getUsername = function () {
        return this.username;
    };
    Credential.prototype.setUsername = function (username) {
        this.username = username;
    };
    Credential.prototype.getPassword = function () {
        return this.password;
    };
    Credential.prototype.setPassword = function (password) {
        this.password = password;
    };
    return Credential;
}());
exports.Credential = Credential;
//# sourceMappingURL=creditial.module.js.map