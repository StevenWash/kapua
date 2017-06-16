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
var creditial_module_1 = require("../module/creditial.module");
var login_service_1 = require("../service/login.service");
var router_1 = require("@angular/router");
var LoginComponent = (function () {
    function LoginComponent(loginService, router) {
        this.loginService = loginService;
        this.router = router;
        this.creditial = new creditial_module_1.Creditial('kapua-sys', 'kapua-password');
    }
    /**
     * 实现登录操作（进行授权验证）
     */
    LoginComponent.prototype.login = function () {
        var _this = this;
        console.log(this.creditial.getUsername() + "  " + this.creditial.getPassword());
        this.loginService.login(this.creditial.getUsername(), this.creditial.getPassword()).subscribe(function (result) {
            if (result) {
                // console.log('来自app.component:'+localStorage.getItem('tokenId'));
                console.log("login success");
                _this.router.navigate(['/home']);
            }
        });
    };
    /**
     * 点击重置按钮后将输入框那个的信息进行重置
     */
    LoginComponent.prototype.reset = function () {
        this.creditial.setUsername('');
        this.creditial.setPassword('');
    };
    return LoginComponent;
}());
LoginComponent = __decorate([
    core_1.Component({
        selector: 'my-app',
        templateUrl: 'app/static/login.html',
        styleUrls: ['app/css/static/login.css']
    }),
    __metadata("design:paramtypes", [login_service_1.LoginService,
        router_1.Router])
], LoginComponent);
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.component.js.map