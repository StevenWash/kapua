import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpModule}  from '@angular/http';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap'

import { AppComponent }  from './component/app.component';
import { LoginService } from './service/login.service';
import { AppRoutingModule } from './module/app-routing.module';
import { LoginComponent } from './component/login.component';
import { MainViewComponent} from './component/main-view.component'
import { UserListService } from "./service/user-list.service"
import { RoleService } from "./service/role.service";
import {DeviceConnectionService} from "./service/device-connection.service";
import {GroupService} from "./service/group.service";



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
    MainViewComponent
  ],
  providers: [
    LoginService,
    UserListService,
    RoleService,
    DeviceConnectionService,
    GroupService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
