/**
 * Created by StevenWash on 2017/6/15.
 */
import {UserInfo} from "../module/user-info.module";
import {Http,Headers} from "@angular/http";
import {Injectable} from "@angular/core";

@Injectable()
export class UserListService {
  private userListUrl:string;
  private loginUser:UserInfo;

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

    let scopeId = localStorage.getItem('scopeId');

    this.userListUrl='https://dev.izhiju.cn/api/v1/'+scopeId+'/users';

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
    let scopeId = localStorage.getItem('scopeId');

    this.userListUrl='https://dev.izhiju.cn/api/v1/'+scopeId+'/users/'+userId;

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

    let scopeId = localStorage.getItem('scopeId');

    this.userListUrl='https://dev.izhiju.cn/api/v1/'+scopeId+'/users/'+userId;

    return this.http.put(this.userListUrl,JSON.stringify(user),{ headers: headers }).map(res => res.json());
  }

  /**
   * 添加用户信息：更新User信息，同时还要更新密码信息
   * @param user
   * @returns {Observable<R>}
   */
  addUser(user:UserInfo){

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');
    let userType = localStorage.getItem('userType');
    user.scopeId=scopeId;
    user.userType=userType;
    this.userListUrl='https://dev.izhiju.cn/api/v1/'+scopeId+'/users';

    return this.http.post(this.userListUrl,JSON.stringify(user),{ headers: headers }).map(res => res.json());

  }

  /**
   * 添加密码信息
   * @param user
   */
  addCredentials(user:UserInfo){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');
    user.scopeId=scopeId;

    console.log(user.scopeId);

    this.userListUrl='https://dev.izhiju.cn/api/v1/'+user.scopeId+'/credentials';
    return this.http.post(this.userListUrl,JSON.stringify(user),{ headers: headers }).map(res => res.json());

  }

  /**
   * 删除用户信息
   * @param userId
   * @returns {Observable<R>}
   */
  deleteUser(userId:string){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');
    console.log(headers);
    console.log(userId);

    this.userListUrl='https://dev.izhiju.cn/api/v1/'+scopeId+'/users/'+userId;
    return this.http.delete(this.userListUrl,{ headers: headers });
  }
}
