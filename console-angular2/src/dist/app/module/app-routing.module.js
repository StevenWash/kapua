"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * Created by 鑫 on 2017/6/10.
 */
var core_1 = require("@angular/core");
var router_1 = require("@angular/router");
var login_component_1 = require("../component/login.component");
var main_view_component_1 = require("../component/main-view.component");
var routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: login_component_1.LoginComponent },
    { path: 'home', component: main_view_component_1.MainViewComponent },
    { path: 'home/device', component: main_view_component_1.MainViewComponent },
    { path: 'home/role', component: main_view_component_1.MainViewComponent },
    { path: 'home/user', component: main_view_component_1.MainViewComponent },
    { path: 'home/group', component: main_view_component_1.MainViewComponent },
    { path: 'home/account', component: main_view_component_1.MainViewComponent }
];
var AppRoutingModule = (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = __decorate([
        core_1.NgModule({
            imports: [router_1.RouterModule.forRoot(routes)],
            exports: [router_1.RouterModule]
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());
exports.AppRoutingModule = AppRoutingModule;
//# sourceMappingURL=app-routing.module.js.map