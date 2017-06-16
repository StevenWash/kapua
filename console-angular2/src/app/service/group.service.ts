import {Http,Headers} from "@angular/http";
import {Injectable} from "@angular/core";
/**
 * Created by StevenWash on 2017/6/16.
 */

@Injectable()
export class GroupService {
  private groupUrl:string;

  constructor(
    private http: Http
  ){}

  /**
   * 获取当前所有的组别信息
   * @returns {Observable<R>}
   */
  getGroupList(){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    this.groupUrl="https://dev.izhiju.cn/api/v1/_/groups?offset=0&limit=50";

    return this.http.get(this.groupUrl,{ headers: headers }).map(res => res.json());
  }
}
