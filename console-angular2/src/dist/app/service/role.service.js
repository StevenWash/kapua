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
 * Created by StevenWash on 2017/6/16.
 */
var http_1 = require("@angular/http");
var core_1 = require("@angular/core");
var host_info_modeule_1 = require("../module/host.info.modeule");
require("rxjs/add/operator/toPromise");
var RoleService = (function () {
    function RoleService(http) {
        this.http = http;
    }
    /**
     * 获取当前scopeId下的所有的角色信息
     * @returns {Observable<R>}
     */
    RoleService.prototype.getRoles = function (inputRoleName) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        if (inputRoleName != null) {
            this.roleUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/roles?name=' + inputRoleName + '&offset=0&limit=50';
        }
        else
            this.roleUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/roles?offset=0&limit=50';
        return this.http.get(this.roleUrl, { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 根据roleId来更新role的信息
     * @param roleId
     * @param role
     * @returns {Observable<R>}
     */
    RoleService.prototype.updateROleById = function (roleId, role) {
        console.log(role.name);
        console.log("roleId:" + roleId);
        console.log(role.optlock);
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.roleUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/roles/' + roleId;
        return this.http.put(this.roleUrl, JSON.stringify(role), { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 添加角色信息
     * @param role
     * @returns {Observable<R>}
     */
    RoleService.prototype.addRole = function (role) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        role.scopeId = scopeId;
        this.roleUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/roles';
        return this.http.post(this.roleUrl, JSON.stringify(role), { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 通过roleId删除当前角色信息
     * @param roleId
     * @returns {Observable<Response>}
     */
    RoleService.prototype.deleteRoleByRoleId = function (roleId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.roleUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/roles/' + roleId;
        return this.http.delete(this.roleUrl, { headers: headers });
    };
    /**
     * 通过roleId来获取当前角色下的所有权限信息
     * @param roleId
     * @returns {Observable<R>}
     */
    RoleService.prototype.getRolePermissionByRole = function (roleId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.roleUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + "/roles/" + roleId + "/permissions?offset=0&limit=50";
        return this.http.get(this.roleUrl, { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 通过userId查询AccessInfo的信息
     * @param userId
     * @returns
     */
    RoleService.prototype.getAccessInfosByUserId = function (userId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.roleUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/accessinfos?userId=' + userId + '&offset=0&limit=50';
        //return this.http.get(this.roleUrl,{ headers: headers }).map(res => res.json());
        return this.http.get(this.roleUrl, { headers: headers }).toPromise()
            .then(function (response) { return response.json().items.item; })
            .catch(this.handleError);
    };
    /**
     * 通过accessInfoId查询出accessRole的信息
     * @param accessInfoId
     * @returns {Observable<R>}
     */
    RoleService.prototype.getAccessRolesByAccessInfoId = function (accessInfoId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.roleUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/accessinfos/' + accessInfoId + '/roles?offset=0&limit=50';
        return this.http.get(this.roleUrl, { headers: headers }).toPromise()
            .then(function (response) { return response.json().items.item; })
            .catch(this.handleError);
    };
    /**
     * 通过roleId获取role相关的信息
     * @param roleId
     * @returns {Observable<R>}
     */
    RoleService.prototype.getRoleByRoleId = function (roleId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.roleUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/roles/' + roleId;
        return this.http.get(this.roleUrl, { headers: headers }).toPromise()
            .then(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    /**
     * 通过roleId来获取该角色对应的权限信息
     * @param roleId
     * @returns {Observable<R>}
     */
    RoleService.prototype.getPermissionByRoleId = function (roleId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.roleUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/roles/' + roleId + '/permissions?offset=0&limit=50';
        return this.http.get(this.roleUrl, { headers: headers }).toPromise()
            .then(function (response) { return response.json().items.item; })
            .catch(this.handleError);
    };
    /**
     * 添加accessRole
     * @param accessRole
     * @returns {Observable<R>}
     */
    RoleService.prototype.addAccessRole = function (accessRole) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        accessRole.scopeId = scopeId;
        this.roleUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/accessinfos/' + accessRole.accessInfoId + '/roles';
        return this.http.post(this.roleUrl, JSON.stringify(accessRole), { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 异常处理函数
     * @param error
     * @returns {Promise<never>}
     */
    RoleService.prototype.handleError = function (error) {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    };
    RoleService = __decorate([
        core_1.Injectable(),
        __metadata("design:paramtypes", [http_1.Http])
    ], RoleService);
    return RoleService;
}());
exports.RoleService = RoleService;
//# sourceMappingURL=role.service.js.map