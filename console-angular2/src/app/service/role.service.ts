/**
 * Created by StevenWash on 2017/6/16.
 */
import {Http,Headers} from "@angular/http";
import {Injectable} from "@angular/core";

@Injectable()
export class RoleService{
  private roleUrl:string;

  constructor(
    private http:Http
  ){}

  getRoles(){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    this.roleUrl="https://dev.izhiju.cn/api/v1/_/roles";

    return this.http.get(this.roleUrl,{ headers: headers }).map(res => res.json());

  }

  /*getPermissionsByRole(roleID: string){
    return this.http.get("/api/roles/" + roleID + "/permission" );
  }*/
}
