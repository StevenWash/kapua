/**
 * Created by StevenWash on 2017/6/14.
 */
import {Component} from "@angular/core";
import {UserListService} from "../service/user.service";
import {UserInfo } from "../module/user-info.module";
import {RoleService} from "../service/role.service";
import {RoleInfo} from "../module/role-info.module";
import {DeviceConnectionService} from "../service/device-connection.service";
import {DeviceConnection} from "../module/device-connect.module";
import {DeviceInfo} from "../module/device.module";
import {GroupService} from "../service/group.service";
import {GroupInfo} from "../module/group-info.module";
import {Creditial} from "../module/creditial.module";
import {Router} from "@angular/router";
import {LoginService} from "../service/login.service";


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

  private aUser:UserInfo=new UserInfo();
  private aCredential=new Creditial();

  private displayName:string;
  private email:string;
  private optlock:number;

  private delUserId:string;

  //点击的用户信息
  private cliUser:UserInfo;

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
    private groupService:GroupService,
    private router: Router,
    private loginService:LoginService
  ){
    console.log("home..");
   // console.log(localStorage.getItem('userId').length);
   // console.log(localStorage.getItem('userId'))
    if(localStorage.getItem('userId')==null||localStorage.getItem('userId').length==0){
      console.log("home2..");
      this.router.navigate(['/login']);
    }
    this.getUserList();
    this.getLoginUser();
    this.getRoleList();
    this.getDeviceConnection();
    this.getDevices();
    this.getGroupList();
  }


  //-------------------User Action ------------------//
  /**
   * 用户进行登出操作
   */
  logout(){
    console.log("logout....");
    this.loginService.logout();
    this.router.navigate(['/login']);
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
    console.log(userInfo);
    this.user=userInfo;
  }

  /**
   * 更新用户的信息
   */
  updateUser(){
    console.log("optlock1:"+this.user.optlock);
    if(this.optlock==this.user.optlock+1){
      this.user.optlock+=1;
    }
    this.userListService.updateUserById(this.user.id,this.user).subscribe((result) => {
      this.user=result;
      this.optlock=this.user.optlock;
      console.log("optlock2:"+this.user.optlock)
      console.log(result);
    });
  }

  /**
   * 添加用户信息
   */
  addUser(){
    console.log(this.aUser);

    this.userListService.addUser(this.aUser).subscribe((result) => {
      console.log(result);
    });

    this.userListService.addCredentials(this.aUser).subscribe((result) => {
      console.log(result);
    });

  }

  /**
   * 点击删除之后将当前点击的用户的id传入
   * @param userInfo
   */
  getUserId(userInfo:UserInfo){
    console.log(userInfo);
    this.delUserId=userInfo.id;
    console.log(this.delUserId);
  }

  /**
   * 删除用户信息
   */
  deleteUser(){
    console.log(this.delUserId);
    this.userListService.deleteUser(this.delUserId).subscribe((result) => {
      if(result){
        this.router.navigate(['/home']);
      }else
        this.router.navigate(['/login']);
    });
  }

  clickUser(userInfo:UserInfo){
    this.cliUser=userInfo;
    console.log(this.cliUser);
    this.userListService.getRolesByUserId(userInfo.id).subscribe((result) => {
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
