import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { LoginService } from './service/login.service';
import { AppRoutingModule } from './module/app-routing.module';
import { LoginComponent } from './component/login.component';
import { MainViewComponent} from './component/main-view.component';
import { UserListService } from './service/user.service';
import { RoleService } from './service/role.service';
import {DeviceConnectionService} from './service/device-connection.service';
import {GroupService} from './service/group.service';
import {MobileValidator} from './validators/adduser.validator';
import {EqualValidator} from './validators/equal-validator.directive';
import {AccountService} from './service/account.service';
import {SSOLoginService} from "./service/sso.login.service";
import {SSOLoginComponent} from "./component/sso.login.Component";
import {CanActivateViaOAuthGuard} from "app/service/oAuth.canActivateGuard";


@NgModule({
  imports:      [
    BrowserModule,
    FormsModule,
    HttpModule,
    NgbModule.forRoot(),
    AppRoutingModule
  ],
  declarations: [
    AppComponent ,
    LoginComponent ,
    MainViewComponent,
    MobileValidator,
    EqualValidator,
    SSOLoginComponent
  ],
  providers: [
    LoginService,
    UserListService,
    RoleService,
    DeviceConnectionService,
    GroupService,
    AccountService,
    SSOLoginService,
    CanActivateViaOAuthGuard
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
