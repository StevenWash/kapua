/**
 * Created by StevenWash on 2017/6/15.
 */
import {UserInfo} from "../module/user-info.module";
import {Http,Headers} from "@angular/http";
import {Injectable} from "@angular/core";

@Injectable()
export class UserListService {
  private userListUrl='https://dev.izhiju.cn/api/v1/_/users';

  constructor(
    private http: Http
  ){ }

  /**
   * 得到当前所有的用户信息
   * @returns {Observable<R>}
   */
  getUserList(){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    return this.http.get(this.userListUrl,{ headers: headers }).map(res => res.json());
  }

  /**
   * 通过userId得到用户信息
   * @param userId
   * @returns {Observable<R>}
   */
  getLoginUser() {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let userId = localStorage.getItem('userId');

    this.userListUrl='https://dev.izhiju.cn/api/v1/_/users/'+userId;

    return this.http.get(this.userListUrl,{ headers: headers }).map(res => res.json());
  }


  /**
   * 更具用户的userId来更新用户数据
   * @param userId
   * @param user
   * @returns {Observable<R>}
   */
  updateUserById(userId:string,user:UserInfo){
    console.log(user.name);
    console.log("userId:"+userId);
    console.log(user.optlock);

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    this.userListUrl='https://dev.izhiju.cn/api/v1/_/users/'+userId;

    return this.http.put(this.userListUrl,JSON.stringify(user),{ headers: headers }).map(res => res.json());
  }
}
