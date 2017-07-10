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
/**
 * Created by StevenWash on 2017/6/14.
 */
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
require("rxjs/add/operator/map");
var host_info_modeule_1 = require("../module/host.info.modeule");
var LoginService = (function () {
    function LoginService(http) {
        this.http = http;
        this.loggedIn = false;
        this.loggedIn = !!localStorage.getItem('tokenId');
    }
    /**
     * 进行登录授权验证
     * @param username
     * @param password
     * @returns {string}
     */
    LoginService.prototype.login = function (username, password) {
        var _this = this;
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        console.log("username:" + username + "  password:" + password);
        return this.http
            .post(host_info_modeule_1.HostInfo.ip + '/api/v1/' + 'authentication/user', JSON.stringify({ username: username, password: password }), { headers: headers }).map(function (res) { return res.json(); }).map(function (res) {
            if (res) {
                localStorage.setItem('tokenId', res.tokenId);
                localStorage.setItem('userId', res.userId);
                localStorage.setItem('scopeId', res.scopeId);
                localStorage.setItem('userType', res.userType);
                localStorage.setItem('userType', res.expi);
                _this.loggedIn = true;
            }
            return "success";
        });
    };
    /**
     * 进行用户登出操作
     * @returns {Observable<R>}
     */
    LoginService.prototype.logout = function () {
        localStorage.clear();
        /*let headers = new Headers();
        headers.append('Content-Type', 'application/json');
    
        let authToken = localStorage.getItem('tokenId');
        console.log(authToken);
        headers.append('Authorization', `Bearer ${authToken}`);
    
        return this.http
          .post('https://dev.izhiju.cn/api/v1/authentication/logout', { headers:headers }).map(res => res.json()).map((res) => {
            if (res) {
              console.log(res);
              localStorage.clear();
            }
            return "success";
          });*/
    };
    LoginService = __decorate([
        core_1.Injectable(),
        __metadata("design:paramtypes", [http_1.Http])
    ], LoginService);
    return LoginService;
}());
exports.LoginService = LoginService;
//# sourceMappingURL=login.service.js.map