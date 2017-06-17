/**
 * Created by StevenWash on 2017/6/14.
 */
import { Injectable } from '@angular/core';
import { Http ,Headers}       from '@angular/http';

import 'rxjs/add/operator/map';

@Injectable()
export class LoginService {

  private loggedIn = false;

  constructor(private http: Http) {
    this.loggedIn=!!localStorage.getItem('tokenId');
  }

  /**
   * 进行登录授权验证
   * @param username
   * @param password
   * @returns {string}
   */
  login(username: string,password:string) {

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    return this.http
        .post(
          'https://dev.izhiju.cn/api/v1/authentication/user',
          JSON.stringify({ username, password }),
          { headers }
        ) .map(res => res.json()).map((res) => {
        if (res) {
          localStorage.setItem('tokenId', res.tokenId);
          localStorage.setItem('userId',res.userId);
          this.loggedIn = true;
        }
        return "success";
      });
  }

}
