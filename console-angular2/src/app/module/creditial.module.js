"use strict";
/**
 * Created by StevenWash on 2017/6/14.
 */
var Creditial = (function () {
    function Creditial(username, password) {
        this.username = username;
        this.password = password;
    }
    Creditial.prototype.getUsername = function () {
        return this.username;
    };
    Creditial.prototype.setUsername = function (username) {
        this.username = username;
    };
    Creditial.prototype.getPassword = function () {
        return this.password;
    };
    Creditial.prototype.setPassword = function (password) {
        this.password = password;
    };
    return Creditial;
}());
exports.Creditial = Creditial;
//# sourceMappingURL=creditial.module.js.map