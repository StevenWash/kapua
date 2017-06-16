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
/**
 * Created by StevenWash on 2017/6/16.
 */
var http_1 = require("@angular/http");
var core_1 = require("@angular/core");
var DeviceConnectionService = (function () {
    function DeviceConnectionService(http) {
        this.http = http;
    }
    /**
     * 得到设备的所有连接属性
     * @returns {Observable<R>}
     */
    DeviceConnectionService.prototype.getDeviceConnection = function () {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        this.deviceConnectionUrl = "https://dev.izhiju.cn/api/v1/_/deviceconnections?offset=0&limit=50";
        return this.http.get(this.deviceConnectionUrl, { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 得到所有的设备信息
     * @returns {Observable<R>}
     */
    DeviceConnectionService.prototype.getDeviceList = function () {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        this.deviceConnectionUrl = "https://dev.izhiju.cn/api/v1/_/devices?offset=0&limit=50";
        return this.http.get(this.deviceConnectionUrl, { headers: headers }).map(function (res) { return res.json(); });
    };
    return DeviceConnectionService;
}());
DeviceConnectionService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], DeviceConnectionService);
exports.DeviceConnectionService = DeviceConnectionService;
//# sourceMappingURL=device-connection.service.js.map