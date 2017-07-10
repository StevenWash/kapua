"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * Created by StevenWash on 2017/6/16.
 */
var permissions_module_1 = require("./permissions.module");
var RoleInfo = (function () {
    function RoleInfo() {
        this.permissions = new permissions_module_1.Permission();
    }
    return RoleInfo;
}());
exports.RoleInfo = RoleInfo;
var RolePermissionInfo = (function () {
    function RolePermissionInfo() {
        this.permission = new permissions_module_1.Permission();
    }
    return RolePermissionInfo;
}());
exports.RolePermissionInfo = RolePermissionInfo;
//# sourceMappingURL=role-info.module.js.map