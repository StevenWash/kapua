"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var http_1 = require("@angular/http");
var core_1 = require("@angular/core");
var host_info_modeule_1 = require("../module/host.info.modeule");
var UserListService = (function () {
    function UserListService(http) {
        this.http = http;
    }
    /**
     * 得到当前所有的用户信息
     * @returns {Observable<R>}
     */
    UserListService.prototype.getUserList = function (selectUsername) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        console.log(selectUsername);
        if (selectUsername != null) {
            this.userListUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/users?name=' + selectUsername + '&offset=0&limit=50';
        }
        else
            this.userListUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/users';
        console.log(this.userListUrl);
        return this.http.get(this.userListUrl, { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 通过userId得到用户信息
     * @param userId
     * @returns {Observable<R>}
     */
    UserListService.prototype.getLoginUser = function () {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var userId = localStorage.getItem('userId');
        var scopeId = localStorage.getItem('scopeId');
        this.userListUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/users/' + userId;
        return this.http.get(this.userListUrl, { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 更具用户的userId来更新用户数据
     * @param userId
     * @param user
     * @returns {Observable<R>}
     */
    UserListService.prototype.updateUserById = function (userId, user) {
        console.log(user.name);
        console.log("userId:" + userId);
        console.log(user.optlock);
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = user.scopeId;
        this.userListUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/users/' + userId;
        return this.http.put(this.userListUrl, JSON.stringify(user), { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 添加用户信息：更新User信息，同时还要更新密码信息
     * @param user
     * @returns {Observable<R>}
     */
    UserListService.prototype.addUser = function (user) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        var userType = localStorage.getItem('userType');
        user.scopeId = scopeId;
        user.userType = userType;
        this.userListUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/users';
        return this.http.post(this.userListUrl, JSON.stringify(user), { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 添加密码信息
     * @param credential
     */
    UserListService.prototype.addCredential = function (credential) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        credential.scopeId = scopeId;
        console.log(credential.scopeId);
        this.userListUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/credentials';
        return this.http.post(this.userListUrl, JSON.stringify(credential), { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 删除用户信息
     * @param userId
     * @returns {Observable<R>}
     */
    UserListService.prototype.deleteUser = function (userId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        console.log(headers);
        console.log(userId);
        this.userListUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/users/' + userId;
        return this.http.delete(this.userListUrl, { headers: headers });
    };
    /**
     * 通过用户的id获取当前用户的所有的角色信息
     * @param userId
     * @returns {Observable<Response>}
     */
    UserListService.prototype.getRolesByUserId = function (userId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.userListUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/roles/query/' + scopeId + '/' + userId;
        return this.http.get(this.userListUrl, { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 通过userId获取该userId下的所有的密码信息
     * @param userId
     * @returns {Observable<Response>}
     */
    UserListService.prototype.getCredentialsByUserId = function (userId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.userListUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/credentials?userId=' + userId + '&offset=0&limit=50';
        return this.http.get(this.userListUrl, { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 更新用户的密码信息
     * @param userCredential
     * @returns {Observable<R>}
     */
    UserListService.prototype.updateCredential = function (userCredential) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.userListUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/credentials/' + userCredential.id;
        return this.http.put(this.userListUrl, JSON.stringify(userCredential), { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 根据用户的密码的id来删除该条密码信息
     * @param userCredentialId
     * @returns {Observable<Response>}
     */
    UserListService.prototype.deleteUserCredential = function (userCredentialId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        console.log(headers);
        console.log(userCredentialId);
        this.userListUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/credentials/' + userCredentialId;
        return this.http.delete(this.userListUrl, { headers: headers });
    };
    /**
     * 根据userId添加一条accessInfo信息
     * @returns {Observable<R>}
     */
    UserListService.prototype.addAccessInfo = function (accessInfo) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        var userId = localStorage.getItem('userId');
        accessInfo.scopeId = scopeId;
        this.userListUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/accessinfos';
        return this.http.post(this.userListUrl, JSON.stringify(accessInfo), { headers: headers }).map(function (res) { return res.json(); });
    };
    UserListService = __decorate([
        core_1.Injectable(),
        __metadata("design:paramtypes", [http_1.Http])
    ], UserListService);
    return UserListService;
}());
exports.UserListService = UserListService;
//# sourceMappingURL=user.service.js.map