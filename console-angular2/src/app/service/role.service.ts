/**
 * Created by StevenWash on 2017/6/16.
 */
import {Http,Headers} from "@angular/http";
import {Injectable} from "@angular/core";
import {RoleInfo} from "../module/role-info.module";
import {HostInfo} from "../module/host.info.modeule";
import {AccessRole} from "../module/access-role.module";

@Injectable()
export class RoleService{
  private roleUrl:string;
  private accessInfoId:string;
  private accessRoles:AccessRole[];
  private roles:RoleInfo[];

  constructor(
    private http:Http
  ){}

  /**
   * 获取当前scopeId下的所有的角色信息
   * @returns {Observable<R>}
   */
  getRoles(inputRoleName:string){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');

    if(inputRoleName!=null){
      this.roleUrl=HostInfo.ip+'/api/v1/'+scopeId+'/roles?name='+inputRoleName+'&offset=0&limit=50';
    }else
      this.roleUrl=HostInfo.ip+'/api/v1/'+scopeId+'/roles?offset=0&limit=50';

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

    this.roleUrl=HostInfo.ip+'/api/v1/'+scopeId+'/roles/'+roleId;

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

    this.roleUrl=HostInfo.ip+'/api/v1/'+scopeId+'/roles';

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

    this.roleUrl=HostInfo.ip+'/api/v1/'+scopeId+'/roles/'+roleId;

    return this.http.delete(this.roleUrl,{ headers: headers });
  }

  /**
   * 通过roleId来获取当前角色下的所有权限信息
   * @param roleId
   * @returns {Observable<R>}
   */
  getRolePermissionByRole(roleId:string){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');

    this.roleUrl=HostInfo.ip+'/api/v1/'+scopeId+"/roles/"+roleId+"/permissions?offset=0&limit=50";

    return this.http.get(this.roleUrl,{ headers: headers }).map(res => res.json());
  }

  /**
   * 根据用户id获取用户的角色信息
   * @param userId
   */
  getRolesByUserId(userId:string){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');

    //根据userId获取accessInfoId
    this.roleUrl=HostInfo.ip+'/api/v1/'+scopeId+'/accessinfos?userId='+userId+'&offset=0&limit=50';
    this.http.get(this.roleUrl,{ headers: headers }).map(res => res.json()).subscribe((result) => {
      console.log(result);
      this.accessInfoId=result.items.item.accessInfoId;

      //根据accessInfoId获取accessRoles
      this.roleUrl=HostInfo.ip+'/api/v1/'+scopeId+'/accessinfos/'+this.accessInfoId+'/roles?offset=0&limit=50';
      this.http.get(this.roleUrl,{ headers: headers }).map(res => res.json()).subscribe((result) => {
        console.log(result);
        this.accessRoles=result.items.item;

        console.log(this.accessRoles);

        //根据accessRoleId来获取Role信息
        for(let i=0;i<1;i++){
          this.roleUrl=HostInfo.ip+'/api/v1/'+scopeId+'/roles/'+this.accessRoles[i].roleId;
          this.http.get(this.roleUrl,{ headers: headers }).map(res => res.json()).subscribe((result) => {
            console.log(result);
            this.roles[i]=result;

            console.log(this.roles);

            return JSON.stringify(this.roles);
          })
        }
      });
    });
  }


}