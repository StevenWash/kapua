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
var DeviceConnectionService = (function () {
    function DeviceConnectionService(http) {
        this.http = http;
    }
    /**
     * 得到设备的所有连接属性
     * @returns {Observable<R>}
     */
    DeviceConnectionService.prototype.getDeviceConnection = function (inputDeviceConnClientId, inputDeviceConnClientStatus) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        if (inputDeviceConnClientId != null && inputDeviceConnClientId.length > 0 && inputDeviceConnClientStatus != null && inputDeviceConnClientStatus.length > 0) {
            this.deviceConnectionUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/deviceconnections?clientId=' + inputDeviceConnClientId + '&status=' + inputDeviceConnClientStatus + '&offset=0&limit=50';
        }
        else if (inputDeviceConnClientId != null && inputDeviceConnClientId.length > 0 && (inputDeviceConnClientStatus == null || inputDeviceConnClientStatus.length <= 0)) {
            this.deviceConnectionUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/deviceconnections?clientId=' + inputDeviceConnClientId + '&offset=0&limit=50';
        }
        else if ((inputDeviceConnClientId == null || inputDeviceConnClientId.length <= 0) && inputDeviceConnClientStatus != null && inputDeviceConnClientStatus.length > 0) {
            this.deviceConnectionUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/deviceconnections?status=' + inputDeviceConnClientStatus + '&offset=0&limit=50';
        }
        else
            this.deviceConnectionUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/deviceconnections?offset=0&limit=50';
        console.log(this.deviceConnectionUrl);
        return this.http.get(this.deviceConnectionUrl, { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 得到所有的设备信息
     * @returns {Observable<R>}
     */
    DeviceConnectionService.prototype.getDeviceList = function (inputDeviceClientId, inputDeviceConStatus) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        if (inputDeviceClientId != null && inputDeviceClientId.length > 0 && inputDeviceConStatus != null && inputDeviceConStatus.length > 0) {
            this.deviceConnectionUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/devices?clientId=' + inputDeviceClientId + '&status=' + inputDeviceConStatus + '&offset=0&limit=50';
        }
        else if (inputDeviceClientId != null && inputDeviceClientId.length > 0 && (inputDeviceConStatus == null || inputDeviceConStatus.length <= 0)) {
            this.deviceConnectionUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/devices?clientId=' + inputDeviceClientId + '&offset=0&limit=50';
        }
        else if ((inputDeviceClientId == null || inputDeviceClientId.length <= 0) && inputDeviceConStatus != null && inputDeviceConStatus.length > 0) {
            this.deviceConnectionUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/devices?status=' + inputDeviceConStatus + '&offset=0&limit=50';
        }
        else
            this.deviceConnectionUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/devices?offset=0&limit=50';
        console.log(this.deviceConnectionUrl);
        return this.http.get(this.deviceConnectionUrl, { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 添加一个设备信息
     * @param deviceInfo
     * @returns {Observable<R>}
     */
    DeviceConnectionService.prototype.addDevice = function (deviceInfo) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.deviceUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/devices';
        return this.http.post(this.deviceUrl, JSON.stringify(deviceInfo), { headers: headers }).map(function (res) { return res.json(); });
    };
    /**
     * 通过设备的id来删除该设备
     * @param deviceId
     * @returns {Observable<Response>}
     */
    DeviceConnectionService.prototype.deleteDeviceByDeviceId = function (deviceId) {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        console.log(headers);
        console.log(deviceId);
        this.deviceUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/devices/' + deviceId;
        return this.http.delete(this.deviceUrl, { headers: headers });
    };
    /**
     * 通过==设备的id来更新设备的信息
     * @param deviceId
     * @param device
     * @returns {Observable<R>}
     */
    DeviceConnectionService.prototype.updateDeviceById = function (deviceId, device) {
        console.log(device.clientId);
        console.log("deviceId:" + deviceId);
        console.log(device.optlock);
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        var authToken = localStorage.getItem('tokenId');
        headers.append('Authorization', "Bearer " + authToken);
        var scopeId = localStorage.getItem('scopeId');
        this.deviceUrl = host_info_modeule_1.HostInfo.ip + '/api/v1/' + scopeId + '/devices/' + deviceId;
        return this.http.put(this.deviceUrl, JSON.stringify(device), { headers: headers }).map(function (res) { return res.json(); });
    };
    DeviceConnectionService = __decorate([
        core_1.Injectable(),
        __metadata("design:paramtypes", [http_1.Http])
    ], DeviceConnectionService);
    return DeviceConnectionService;
}());
exports.DeviceConnectionService = DeviceConnectionService;
//# sourceMappingURL=device-connection.service.js.map