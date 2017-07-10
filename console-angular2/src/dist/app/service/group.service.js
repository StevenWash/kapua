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
/**
 * Created by StevenWash on 2017/6/16.
 */
var GroupService = (function () {
    function GroupService(http) {
        this.http = http;
    }
    /**
     * 获取当前所有的组别信息
     * @returns {Observable<R>}
     */
    GroupService.prototype.getGroupList = function (inputGroupname) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        if (inputGroupname != null) {
            this.groupUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/groups?name=' + inputGroupname + '&offset=0&limit=50';
        }
        else
            this.groupUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/groups?offset=0&limit=50';
        return this.http.get(this.groupUrl, { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 添加一个分组信息
     * @param group
     * @returns {Observable<R>}
     */
    GroupService.prototype.addGroup = function (group) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.groupUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + "/groups";
        return this.http.post(this.groupUrl, JSON.stringify(group), { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 更新用户组通过小组id
     * @param groupId
     * @param group
     * @returns {Observable<R>}
     */
    GroupService.prototype.updateGroupById = function (groupId, group) {
        console.log(group.name);
        console.log("groupId:" + groupId);
        console.log(group.optlock);
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.groupUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/groups/' + groupId;
        return this.http.put(this.groupUrl, JSON.stringify(group), { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 通过groupId来删除小组的信息
     * @param groupId
     * @returns {Observable<Response>}
     */
    GroupService.prototype.deleteGroupByGroupId = function (groupId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.groupUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/groups/' + groupId;
        return this.http.delete(this.groupUrl, { headers: headers });
    };
    GroupService = __decorate([
        core_1.Injectable(),
        __metadata("design:paramtypes", [http_1.Http])
    ], GroupService);
    return GroupService;
}());
exports.GroupService = GroupService;
//# sourceMappingURL=group.service.js.map