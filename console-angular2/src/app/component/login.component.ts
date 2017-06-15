/**
 * Created by StevenWash on 2017/6/14.
 */
import {Component} from '@angular/core';

import {Creditial} from '../module/creditial.module';
import {LoginService} from '../service/login.service';
import {Router} from "@angular/router";


@Component({
  selector: 'my-app',
  templateUrl:'app/static/login.html',
  styleUrls: ['app/css/static/login.css']
})
export class LoginComponent{
  private creditial=new Creditial('kapua-sys','kapua-password');

  constructor(
    private loginService:LoginService,
    private router: Router
  ){}

  /**
   * 实现登录操作（进行授权验证）
   */
  login() {
    console.log(this.creditial.getUsername() + "  " + this.creditial.getPassword());
    this.loginService.login(this.creditial.getUsername(), this.creditial.getPassword()).subscribe((result) => {
      if (result) {
        // console.log('来自app.component:'+localStorage.getItem('tokenId'));
        console.log("login success");
        this.router.navigate(['/home']);
      }
    });
  }

  /**
   * 点击重置按钮后将输入框那个的信息进行重置
   */
  reset() {
    this.creditial.setUsername('');
    this.creditial.setPassword('');
  }
}
