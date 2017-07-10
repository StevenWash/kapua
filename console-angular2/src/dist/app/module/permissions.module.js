"use strict";
/**
 * Created by StevenWash on 2017/6/27.
 */
Object.defineProperty(exports, "__esModule", { value: true });
var Permission = (function () {
    function Permission() {
    }
    return Permission;
}());
exports.Permission = Permission;
var Actions;
(function (Actions) {
    Actions[Actions["read"] = 0] = "read";
    Actions[Actions["write"] = 1] = "write";
    Actions[Actions["delete"] = 2] = "delete";
    Actions[Actions["connect"] = 3] = "connect";
    Actions[Actions["execute"] = 4] = "execute";
})(Actions = exports.Actions || (exports.Actions = {}));
//# sourceMappingURL=permissions.module.js.map