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
var MainViewComponent = (function () {
    function MainViewComponent(userListService) {
        this.userListService = userListService;
        this.getUserList();
    }
    /**
     * 得到用户的所有信息
     */
    MainViewComponent.prototype.getUserList = function () {
        var _this = this;
        this.userListService.getUserList().subscribe(function (result) {
            _this.userInfos = result.items.item;
        });
    };
    MainViewComponent.prototype.getUserByName = function (name) {
        console.log(name);
    };
    return MainViewComponent;
}());
MainViewComponent = __decorate([
    core_1.Component({
        selector: 'main-view',
        templateUrl: 'app/static/main-view.component.html'
    }),
    __metadata("design:paramtypes", [user_list_service_1.UserListService])
], MainViewComponent);
exports.MainViewComponent = MainViewComponent;
//# sourceMappingURL=main-view.component.js.map