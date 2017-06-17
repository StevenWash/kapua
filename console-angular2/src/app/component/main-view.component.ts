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
import {NgForm} from "@angular/forms";


@Component({
  selector: 'main-view',
  templateUrl: 'app/static/main-view.component.html',
  styleUrls:['app/css/static/main.view.css']
})
export class MainViewComponent{
  //接收返回信息的实体类
  private userInfos:UserInfo[];
  private user:UserInfo;
  private roleInfos:RoleInfo[];
  private deviceConnections:DeviceConnection[];
  private deviceInfos:DeviceInfo[];
  private groupInfos:GroupInfo[];

  private displayName:string;
  private email:string;

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
    this.getLoginUser();
    this.getRoleList();
    this.getDeviceConnection();
    this.getDevices();
    this.getGroupList();
  }


  //-------------------User Action ------------------//
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
  getLoginUser(){
    this.userListService.getLoginUser().subscribe((result) => {
      this.displayName=result.displayName;
      this.email=result.email;
    });
  }

  /**
   * 在用户管理界面点击update之后将当前用户的信息显示在update的提示框中
   * @param userInfo
   */
  getUserInfo(userInfo:UserInfo){
    this.user=userInfo;
    console.log(userInfo)
  }

  updateUser(userForm: NgForm){
   // console.log("userForm:"+userForm.value);
    //this.user=userForm.value;
    //console.log("user:"+this.user.getName()+ "  phone:"+this.user.getPhone());
    //console.log("userId:"+this.user.getId())
    console.log(this.user);
    console.log(this.user.id);
    console.log(this.user.name);
    this.userListService.updateUserById(this.user.id,this.user).subscribe((result) => {
      console.log(result);
    });
  }





  //-------------------Role Action ------------------//
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
