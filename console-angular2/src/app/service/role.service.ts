/**
 * Created by StevenWash on 2017/6/16.
 */
import {Http,Headers} from "@angular/http";
import {Injectable} from "@angular/core";
import {RoleInfo} from "../module/role-info.module";

@Injectable()
export class RoleService{
  private roleUrl:string;

  constructor(
    private http:Http
  ){}

  /**
   * 获取当前scopeId下的所有的角色信息
   * @returns {Observable<R>}
   */
  getRoles(){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');

    this.roleUrl="https://dev.izhiju.cn/api/v1/"+scopeId+"/roles";

    return this.http.get(this.roleUrl,{ headers: headers }).map(res => res.json());

  }

  /**
   * 根据roleId来更新role的信息
   * @param roleId
   * @param role
   * @returns {Observable<R>}
   */
  updateROleById(roleId:string,role:RoleInfo){
    console.log(role.name);
    console.log("roleId:"+roleId);
    console.log(role.optlock);

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');

    this.roleUrl='https://dev.izhiju.cn/api/v1/'+scopeId+'/roles/'+roleId;

    return this.http.put(this.roleUrl,JSON.stringify(role),{ headers: headers }).map(res => res.json());
  }


  /**
   * 添加角色信息
   * @param role
   * @returns {Observable<R>}
   */
  addRole(role:RoleInfo){

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');
    role.scopeId=scopeId;

    this.roleUrl='https://dev.izhiju.cn/api/v1/'+scopeId+'/roles';

    return this.http.post(this.roleUrl,JSON.stringify(role),{ headers: headers }).map(res => res.json());

  }

  /**
   * 通过roleId删除当前角色信息
   * @param roleId
   * @returns {Observable<Response>}
   */
  deleteRoleByRoleId(roleId:string){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');

    this.roleUrl='https://dev.izhiju.cn/api/v1/'+scopeId+'/roles/'+roleId;

    return this.http.delete(this.roleUrl,{ headers: headers });
  }

  /*getPermissionsByRole(roleID: string){
    return this.http.get("/api/roles/" + roleID + "/permission" );
  }*/

}
