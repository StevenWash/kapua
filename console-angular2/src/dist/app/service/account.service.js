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
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var host_info_modeule_1 = require("../module/host.info.modeule");
/**
 * Created by StevenWash on 2017/6/28.
 */
var AccountService = (function () {
    function AccountService(http) {
        this.http = http;
    }
    /**
     * 获取当前scopeId下的所有的账号信息
     * @returns {Observable<R>}
     */
    AccountService.prototype.getAccountList = function (accountName) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        if (accountName != null) {
            this.accountUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/accounts?name=' + accountName + '&offset=0&limit=50';
        }
        else
            this.accountUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + "/accounts?offset=0&limit=50";
        return this.http.get(this.accountUrl, { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 添加一个账号信息
     * @param account
     * @returns {Observable<R>}
     */
    AccountService.prototype.addAccount = function (account) {
        console.log(account);
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.accountUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/accounts';
        return this.http.post(this.accountUrl, JSON.stringify(account), { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 通过accountId更新账号信息
     * @param accountId
     * @param account
     * @returns {Observable<R>}
     */
    AccountService.prototype.updateAccountById = function (accountId, account) {
        console.log(account.name);
        console.log("accountId:" + accountId);
        console.log(account.optlock);
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.accountUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/accounts/' + accountId;
        return this.http.put(this.accountUrl, JSON.stringify(account), { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 根据accountId来删除账号信息
     * @param accountId
     * @returns {Observable<Response>}
     */
    AccountService.prototype.deleteAccountByAccountId = function (accountId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.accountUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/accounts/' + accountId;
        return this.http.delete(this.accountUrl, { headers: headers });
    };
    /**
     * 获得在某个accounId下的所有的用户信息
     * @param accountId
     * @returns {Observable<R>}
     */
    AccountService.prototype.getAccountUsers = function (accountId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = accountId;
        this.accountUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/users';
        return this.http.get(this.accountUrl, { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 在accountId账号下添加用户信息
     * @param accountId
     * @param accUser
     * @returns {Observable<R>}
     */
    AccountService.prototype.addAccountUser = function (accountId, accUser) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = accountId;
        accUser.scopeId = scopeId;
        console.log(accUser);
        this.accountUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/users';
        return this.http.post(this.accountUrl, JSON.stringify(accUser), { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 在accountId账号添加密码信息
     * @param accountId
     * @param accCredential
     * @returns {Observable<R>}
     */
    AccountService.prototype.addAccountCredential = function (accountId, accCredential) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = accountId;
        accCredential.scopeId = scopeId;
        this.accountUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/credentials';
        return this.http.post(this.accountUrl, JSON.stringify(accCredential), { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 删除用户信息
     * @param userId
     * @returns {Observable<R>}
     */
    AccountService.prototype.deleteAccountUser = function (accountId, userId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = accountId;
        this.accountUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/users/' + userId;
        return this.http.delete(this.accountUrl, { headers: headers });
    };
    AccountService = __decorate([
        core_1.Injectable(),
        __metadata("design:paramtypes", [http_1.Http])
    ], AccountService);
    return AccountService;
}());
exports.AccountService = AccountService;
//# sourceMappingURL=account.service.js.map