/**
 * Created by StevenWash on 2017/6/15.
 */
import {UserInfo} from "../module/user-info.module";
import {Http,Headers} from "@angular/http";
import {Injectable} from "@angular/core";

@Injectable()
export class UserListService{
  private users:UserInfo;
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

  getUserByName(name:string) {

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    this.userListUrl='https://dev.izhiju.cn/api/v1/_/users?name='+name+"&offset=0&limit=50";

    console.log("url:"+this.userListUrl);

    return this.http.get(this.userListUrl,{ headers: headers }).map(res => res.json());
  }

}
