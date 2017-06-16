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
 * Created by StevenWash on 2017/6/14.
 */
var core_1 = require("@angular/core");
var user_list_service_1 = require("../service/user-list.service");
var role_service_1 = require("../service/role.service");
var device_connection_service_1 = require("../service/device-connection.service");
var group_service_1 = require("../service/group.service");
var MainViewComponent = (function () {
    /**
     * 构造函数，在构造器里面进行依赖注入
     * @param userListService
     * @param roleService
     * @param deviceConnectionService
     */
    function MainViewComponent(userListService, roleService, deviceConnectionService, groupService) {
        this.userListService = userListService;
        this.roleService = roleService;
        this.deviceConnectionService = deviceConnectionService;
        this.groupService = groupService;
        this.getUserList();
        this.getRoleList();
        this.getDeviceConnection();
        this.getDevices();
        this.getGroupList();
    }
    /**
     * 得到所有用户的所有信息
     */
    MainViewComponent.prototype.getUserList = function () {
        var _this = this;
        this.userListService.getUserList().subscribe(function (result) {
            _this.userInfos = result.items.item;
        });
    };
    /**
     * 通过用户名获取用户信息
     * @param name
     */
    MainViewComponent.prototype.getUserByName = function (name) {
        var _this = this;
        console.log("update:" + name);
        this.userListService.getUserByName(name).subscribe(function (result) {
            console.log("result:" + result.items.item);
            _this.user = result.items.item;
            //console.log(this.user.getUserName());
        });
    };
    /**
     * 得到所有的角色信息
     */
    MainViewComponent.prototype.getRoleList = function () {
        var _this = this;
        this.roleService.getRoles().subscribe(function (result) {
            console.log(result);
            _this.roleInfos = result.items.item;
        });
    };
    /**
     * 得到所有的设备连接信息
     */
    MainViewComponent.prototype.getDeviceConnection = function () {
        var _this = this;
        this.deviceConnectionService.getDeviceConnection().subscribe(function (result) {
            console.log(result);
            _this.deviceConnections = result.items.item;
        });
    };
    /**
     * 得到所有的设备信息
     */
    MainViewComponent.prototype.getDevices = function () {
        var _this = this;
        this.deviceConnectionService.getDeviceList().subscribe(function (result) {
            console.log(result);
            _this.deviceInfos = result.items.item;
        });
    };
    /**
     * 得到所有的组信息
     */
    MainViewComponent.prototype.getGroupList = function () {
        var _this = this;
        this.groupService.getGroupList().subscribe(function (result) {
            console.log(result);
            _this.groupInfos = result.items.item;
        });
    };
    return MainViewComponent;
}());
MainViewComponent = __decorate([
    core_1.Component({
        selector: 'main-view',
        templateUrl: 'app/static/main-view.component.html',
        styleUrls: ['app/css/static/main.view.css']
    }),
    __metadata("design:paramtypes", [user_list_service_1.UserListService,
        role_service_1.RoleService,
        device_connection_service_1.DeviceConnectionService,
        group_service_1.GroupService])
], MainViewComponent);
exports.MainViewComponent = MainViewComponent;
//# sourceMappingURL=main-view.component.js.map