/**
 * Created by StevenWash on 2017/6/15.
 */
"use strict";
var UserInfo = (function () {
    function UserInfo(name, createdOn, createdBy, status, displayName, email, phoneNumber) {
        this.name = name;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.status = status;
        this.displayName = displayName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    return UserInfo;
}());
exports.UserInfo = UserInfo;
var UserResponse = (function () {
    function UserResponse() {
    }
    return UserResponse;
}());
exports.UserResponse = UserResponse;
//# sourceMappingURL=user-info.module.js.map