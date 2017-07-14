/**
 * Created by StevenWash on 2017/7/14.
 */
import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {SSOLoginService} from "../service/sso.login.service";

@Component({
  selector:'ssoLogin',
  template:`<p>Hello</p>`,
  providers:[SSOLoginService]
})
export class SSOLoginComponent{
  constructor(
    public router: Router ,
    private ssologinService: SSOLoginService
  ){
    let loginUrl:string=ssologinService.getLoginUri();
    console.log(loginUrl);
    router.navigate([loginUrl]);
  }



  login(event, username, password) {
    event.preventDefault();
    this.ssologinService.login(username, password)
      .subscribe(
        response => {
          localStorage.setItem('token', response.access_token);
          this.router.navigateByUrl('/home');
        },
        error => {
          alert(error);
        }
      );
  }


}
