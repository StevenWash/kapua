/**
 * Created by StevenWash on 2017/6/14.
 */
import {Component, Input, Output} from "@angular/core";
import {UserListService} from "../service/user-list.service";
import {UserInfo, UserResponse} from "../module/user-info.module";
import {RoleService} from "../service/role.service";
import {RoleInfo} from "../module/role-info.module";
import {DeviceConnectionService} from "../service/device-connection.service";
import {DeviceConnection} from "../module/device-connect.module";
import {DeviceInfo} from "../module/device.module";
import {GroupService} from "../service/group.service";
import {GroupInfo} from "../module/group-info.module";


@Component({
  selector: 'main-view',
  templateUrl: 'app/static/main-view.component.html',
  styleUrls:['app/css/static/main.view.css']
})
export class MainViewComponent{
  //接收返回信息的实体类
  private userInfos:UserInfo[];
  private user:UserInfo;
  private loginUser:UserInfo;
  private roleInfos:RoleInfo[];
  private deviceConnections:DeviceConnection[];
  private deviceInfos:DeviceInfo[];
  private groupInfos:GroupInfo[];

  /**
   * 构造函数，在构造器里面进行依赖注入
   * @param userListService
   * @param roleService
   * @param deviceConnectionService
   */
  constructor(
    private userListService:UserListService,
    private roleService:RoleService,
    private deviceConnectionService:DeviceConnectionService,
    private groupService:GroupService
  ){
    this.getUserList();
    this.getRoleList();
    this.getDeviceConnection();
    this.getDevices();
    this.getGroupList();
  }

  /**
   * 得到所有用户的所有信息
   */
  getUserList(){
      this.userListService.getUserList().subscribe((result) => {
        this.userInfos=result.items.item;
    });
  }

  /**
   * 通过用户名获取用户信息
   * @param name
   */
  getUserByName(name:string){
    console.log("update:"+name);
    this.userListService.getUserByName(name).subscribe((result) => {
      console.log("result:"+result.items.item);
      this.user=result.items.item;
      //console.log(this.user.getUserName());
    });
  }

  /**
   * 得到所有的角色信息
   */
  getRoleList(){
    this.roleService.getRoles().subscribe((result) => {
      console.log(result);
      this.roleInfos=result.items.item;
    });
  }

  /**
   * 得到所有的设备连接信息
   */
  getDeviceConnection(){
    this.deviceConnectionService.getDeviceConnection().subscribe((result) => {
      console.log(result);
      this.deviceConnections=result.items.item;
    });
  }

  /**
   * 得到所有的设备信息
   */
  getDevices(){
    this.deviceConnectionService.getDeviceList().subscribe((result) => {
      console.log(result);
      this.deviceInfos=result.items.item;
    });
  }

  /**
   * 得到所有的组信息
   */
  getGroupList(){
    this.groupService.getGroupList().subscribe((result) => {
      console.log(result);
      this.groupInfos=result.items.item;
    });
  }

}
