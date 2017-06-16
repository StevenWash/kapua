/**
 * Created by 鑫 on 2017/6/10.
 */
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../component/login.component';
import { MainViewComponent } from '../component/main-view.component'

const routes: Routes = [
  {path: '', redirectTo : '/login', pathMatch : 'full'},
  {path: 'login',component: LoginComponent },
  {path: 'home' ,component: MainViewComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}