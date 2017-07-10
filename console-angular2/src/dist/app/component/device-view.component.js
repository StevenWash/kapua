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
 * Created by StevenWash on 2017/7/8.
 */
var core_1 = require("@angular/core");
var device_module_1 = require("../module/device.module");
var device_connection_service_1 = require("../service/device-connection.service");
var router_1 = require("@angular/router");
var DeviceViewComponent = (function () {
    /**
     * 构造函数，在构造器里面进行依赖注入
     * @param deviceConnectionService
     */
    function DeviceViewComponent(deviceConnectionService, router) {
        this.deviceConnectionService = deviceConnectionService;
        this.router = router;
        this.aDevice = new device_module_1.DeviceInfo();
        this.cliDevice = new device_module_1.DeviceInfo();
        this.getDevices();
    }
    //--------------------Device Action---------------------------//
    /**
     * 得到所有的设备信息
     */
    DeviceViewComponent.prototype.getDevices = function () {
        var _this = this;
        console.log(this.inputDeviceClientId + "  " + this.inputDeviceConStatus);
        this.deviceConnectionService.getDeviceList(this.inputDeviceClientId, this.inputDeviceConStatus).subscribe(function (result) {
            _this.deviceInfos = result.items.item;
        });
    };
    /**
     * 添加设备信息
     */
    DeviceViewComponent.prototype.addDevice = function () {
        var _this = this;
        this.aDevice.deviceCredentialsMode = "LOOSE"; //设置默认值
        console.log(this.aDevice);
        this.deviceConnectionService.addDevice(this.aDevice).subscribe(function (result) {
            console.log(result);
            _this.deviceInfos = null;
            _this.getDevices();
            _this.router.navigate(['/home/device']);
        });
    };
    /**
     * 得到当前用户点击的设备信息的id
     * @param deviceInfo
     */
    DeviceViewComponent.prototype.getDeviceId = function (deviceInfo) {
        console.log(deviceInfo);
        this.delDeviceId = deviceInfo.id;
        console.log(this.delDeviceId);
    };
    /**
     * 通过用户选择的设备的id删除该设备信息
     */
    DeviceViewComponent.prototype.deleteDevice = function () {
        var _this = this;
        console.log(this.delDeviceId);
        this.deviceConnectionService.deleteDeviceByDeviceId(this.delDeviceId).subscribe(function (result) {
            console.log(result);
            _this.getDevices();
            _this.router.navigate(['/home/device']);
        });
    };
    /**
     * 得到当前用户点击的设备信息
     * @param deviceInfo
     */
    DeviceViewComponent.prototype.getDeviceInfo = function (deviceInfo) {
        console.log(deviceInfo);
        this.device = deviceInfo;
    };
    /**
     * 根据的设备的id来更新设备的相关信息
     */
    DeviceViewComponent.prototype.updateDevice = function () {
        var _this = this;
        console.log("optlock1:" + this.device.optlock);
        if (this.deviceOptlock == this.device.optlock + 1) {
            this.device.optlock += 1;
        }
        console.log(this.device);
        this.deviceConnectionService.updateDeviceById(this.device.id, this.device).subscribe(function (result) {
            _this.device = result;
            _this.deviceOptlock = _this.device.optlock;
            console.log("optlock2:" + _this.device.optlock);
            console.log(result);
            _this.getDevices();
            _this.router.navigate(['/home/device']);
        });
    };
    /**
     * 获得点击的设备信息
     * @param deviceInfo
     */
    DeviceViewComponent.prototype.clickDevice = function (deviceInfo) {
        this.cliDevice = deviceInfo;
        console.log(this.cliDevice);
    };
    /**
     * 点击充值按钮之后的处理过程
     */
    DeviceViewComponent.prototype.resetInputDevice = function () {
        this.inputDeviceConStatus = null;
        this.inputDeviceClientId = null;
        this.getDevices();
    };
    DeviceViewComponent = __decorate([
        core_1.Component({
            selector: 'device',
            templateUrl: 'app/static/device-view.html',
            styleUrls: ['app/css/static/main.view.css']
        }),
        __metadata("design:paramtypes", [device_connection_service_1.DeviceConnectionService,
            router_1.Router])
    ], DeviceViewComponent);
    return DeviceViewComponent;
}());
exports.DeviceViewComponent = DeviceViewComponent;
//# sourceMappingURL=device-view.component.js.map