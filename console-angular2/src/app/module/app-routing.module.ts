/**
 * Created by 鑫 on 2017/6/10.
 */
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../component/login.component';
import { MainViewComponent } from '../component/main-view.component'
import {DeviceViewComponent} from "../component/device-view.component";
import {SSOLoginComponent} from "../component/sso.login.Component";
import {CanActivateViaOAuthGuard} from "../service/oAuth.canActivateGuard";

const routes: Routes = [
  {path: '', redirectTo : '/login', pathMatch : 'full'},
  {path: 'login',component: LoginComponent },
  {path: 'home' ,component: MainViewComponent },
  {path: 'home/device',component: MainViewComponent},
  {path: 'home/role',component: MainViewComponent},
  {path: 'home/user',component: MainViewComponent},
  {path: 'home/group',component: MainViewComponent},
  {path: 'home/account',component: MainViewComponent},
  {path: 'ssologin',component: SSOLoginComponent,canActivate:[CanActivateViaOAuthGuard]},
  {path: 'sso/callback',component: SSOLoginComponent}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
