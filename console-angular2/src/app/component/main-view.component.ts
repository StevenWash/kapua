/**
 * Created by StevenWash on 2017/6/14.
 */
import {Component} from "@angular/core";
import {UserListService} from "../service/user.service";
import {UserInfo } from "../module/user-info.module";
import {RoleService} from "../service/role.service";
import {RoleInfo, RolePermissionInfo} from "../module/role-info.module";
import {DeviceConnectionService} from "../service/device-connection.service";
import {DeviceConnection} from "../module/device-connect.module";
import {DeviceInfo} from "../module/device.module";
import {GroupService} from "../service/group.service";
import {GroupInfo} from "../module/group-info.module";
import {Credential} from "../module/creditial.module";
import {Router} from "@angular/router";
import {LoginService} from "../service/login.service";
import {AccountService} from "../service/account.service";
import {AccountInfo} from "../module/account-info.module";


@Component({
  selector: 'main-view',
  templateUrl: 'app/static/main-view.component.html',
  styleUrls:['app/css/static/main.view.css']
})
export class MainViewComponent{
  //--------user相关的变量信息--------//
  private userInfos:UserInfo[];
  private user:UserInfo;
  private aUser:UserInfo=new UserInfo();
  private aCredential=new Credential();
  private displayName:string;
  private email:string;
  private optlock:number;
  private delUserId:string;
  private cliUser:UserInfo;//点击的用户信息
  private inputUsername:string;

  //--------role相关的变量信息--------//
  private roleInfos:RoleInfo[];
  private userrole:RoleInfo;//用户进行为当前用户添加角色时选择的角色
  private role:RoleInfo;
  private aRole:RoleInfo=new RoleInfo();
  private delRoleId:string;
  private cliRole:RoleInfo;

  private rolePermission:RolePermissionInfo[];

  //--------group相关的变量信息--------//
  private groupInfos:GroupInfo[];
  private aGroup:GroupInfo=new GroupInfo();
  private group:GroupInfo;
  private delGroupId:string;
  private cliGroup:GroupInfo;


  //--------device相关的变量信息--------//
  private deviceInfos:DeviceInfo[];
  private aDevice:DeviceInfo=new DeviceInfo();
  private devicegroup:GroupInfo;
  private delDeviceId:string;
  private device:DeviceInfo;
  private cliDevice:DeviceInfo=new DeviceInfo();


  //--------device connection相关的变量信息--------//
  private cliConnection:DeviceConnection;
  private deviceConnections:DeviceConnection[];


  //--------account相关的变量信息--------//
  private accountInfos:AccountInfo[];
  private aAccount:AccountInfo=new AccountInfo();
  private account:AccountInfo;
  private delAccountId:string;
  private cliAccount:AccountInfo=new AccountInfo();


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
    private loginService:LoginService,
    private accountService:AccountService
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
    this.getAccountList();
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
      this.userListService.getUserList(this.inputUsername).subscribe((result) => {
        console.log(result);
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
      this.getUserList();
      this.router.navigate(['/home/user']);
    });
  }

  /**
   * 添加用户信息
   */
  addUser(){
    console.log(this.aUser);

    this.userListService.addUser(this.aUser).subscribe((result) => {
      console.log(result);

      this.aCredential.userId=result.id;
      this.aCredential.credentialType="PASSWORD";
      this.aCredential.credentialKey=this.aCredential.password;

      console.log(this.aCredential);
      this.userListService.addCredentials(this.aCredential).subscribe((result) => {
        console.log(result);
      });

      this.getUserList();
      this.router.navigate(['/home/user']);
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
      console.log(result);
      this.getUserList();
      this.router.navigate(['/home/user']);
    });
  }

  /**
   * 用户信息被点击之后触发的函数
   * @param userInfo
   */
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
   * 得到当前的role信息
   * @param roleInfo
   */
  getRoleInfo(roleInfo:RoleInfo){
    console.log(roleInfo);
    this.role=roleInfo;
  }

  /**
   * 更新角色信息
   */
  updateRole(){
    console.log("optlock1:"+this.role.optlock);
    if(this.optlock==this.role.optlock+1){
      this.role.optlock+=1;
    }
    this.roleService.updateROleById(this.role.id,this.role).subscribe((result) => {
      this.role=result;
      this.optlock=this.role.optlock;
      console.log(result);
      this.getRoleList();
      this.router.navigate(['/home/role']);
    });
  }

  /**
   * 添加角色信息
   */
  addRole(){
    console.log(this.aRole);
    this.roleService.addRole(this.aRole).subscribe((result) => {
      console.log(result);
      this.getRoleList();
      this.router.navigate(['/home/role']);
    });

  }

  /**
   * 通过当前点击的角色的信息来获取该角色的id
   * @param roleInfo
   */
  getRoleId(roleInfo:RoleInfo){
    console.log(roleInfo);
    this.delRoleId=roleInfo.id;
    console.log(this.delRoleId);
  }

  /**
   * 通过点击角色的id信息来删除角色信息
   */
  deleteRole(){
    console.log(this.delRoleId);
    this.roleService.deleteRoleByRoleId(this.delRoleId).subscribe((result) => {
      console.log(result);
      this.getRoleList();
      this.router.navigate(['/home/role']);
    });
  }


  setRole(){
    this.userrole=this.role;
  }

  submitRole(){
    console.log(this.userrole);
  }

  /**
   * 获取用户点击的角色的信息
   * @param roleInfo
   */
  clickRole(roleInfo:RoleInfo){
    this.cliRole=roleInfo;
    console.log(this.cliRole);

    //获取当前角色的所有权限信息
    this.roleService.getRolePermissionByRole(this.cliRole.id).subscribe((result) => {
      console.log(result);
      this.rolePermission=result.items.item;
      console.log(this.rolePermission);
    });

    //获取当前所有的domain信息


  }





  //--------------------DeviceConnection Action---------------------------//
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
   * 获取用户点击的设备连接信息
   * @param deviceConnection
   */
  clickConnection(deviceConnection:DeviceConnection){
    this.cliConnection=deviceConnection;
    console.log(this.cliConnection);

  }

  //--------------------Device Action---------------------------//

  /**
   * 得到所有的设备信息
   */
  getDevices(){
    this.deviceConnectionService.getDeviceList().subscribe((result) => {
      this.deviceInfos=result.items.item;
    });
  }

  /**
   * 添加设备信息
   */
  addDevice(){
    this.aDevice.deviceCredentialsMode="LOOSE";//设置默认值
    console.log(this.aDevice);
    this.deviceConnectionService.addDevice(this.aDevice).subscribe((result) => {
      console.log(result);
      this.deviceInfos=null;
      this.getDevices();
      this.router.navigate(['/home/device']);
    });
  }

  /**
   * 得到当前用户点击的设备信息的id
   * @param deviceInfo
   */
  getDeviceId(deviceInfo:DeviceInfo){
    console.log(deviceInfo);
    this.delDeviceId=deviceInfo.id;
    console.log(this.delDeviceId);
  }

  /**
   * 通过用户选择的设备的id删除该设备信息
   */
  deleteDevice(){
    console.log(this.delDeviceId);
    this.deviceConnectionService.deleteDeviceByDeviceId(this.delDeviceId).subscribe((result) => {
      console.log(result);
      this.getDevices();
      this.router.navigate(['/home/device']);
    });
  }

  /**
   * 得到当前用户点击的设备信息
   * @param deviceInfo
   */
  getDeviceInfo(deviceInfo:DeviceInfo){
    console.log(deviceInfo);
    this.device=deviceInfo;
  }

  /**
   * 根据的设备的id来更新设备的相关信息
   */
  updateDevice(){
    console.log("optlock1:"+this.device.optlock);
    if(this.optlock==this.device.optlock+1){
      this.device.optlock+=1;
    }
    console.log(this.device);
    this.deviceConnectionService.updateDeviceById(this.device.id,this.device).subscribe((result) => {
      this.device=result;
      this.optlock=this.device.optlock;
      console.log("optlock2:"+this.device.optlock);
      console.log(result);
      this.getDevices();
      this.router.navigate(['/home/device']);
    });
  }

  /**
   * 获得点击的设备信息
   * @param deviceInfo
   */
  clickDevice(deviceInfo:DeviceInfo){
    this.cliDevice=deviceInfo;
    console.log(this.cliDevice);
  }






  //--------------------Group Action---------------------------//
  /**
   * 得到所有的组信息
   */
  getGroupList(){
    this.groupService.getGroupList().subscribe((result) => {
      console.log(result);
      this.groupInfos=result.items.item;
    });
  }

  /**
   * 添加组信息
   */
  addGroup(){
    console.log(this.aGroup);
    this.groupService.addGroup(this.aGroup).subscribe((result) => {
      console.log(result);
      this.getGroupList();
      this.router.navigate(['/home/group']);
    });
  }

  /**
   * 得到用户当前点击的组的信息
   * @param groupInfo
   */
  getGroupInfo(groupInfo:GroupInfo){
    console.log(groupInfo);
    this.group=groupInfo;
  }

  /**
   * 更新的用户当前的点击小组
   */
  updateGroup(){
    console.log("optlock1:"+this.group.optlock);
    if(this.optlock==this.group.optlock+1){
      this.group.optlock+=1;
    }
    this.groupService.updateGroupById(this.group.id,this.group).subscribe((result) => {
      this.group=result;
      this.optlock=this.group.optlock;
      console.log("optlock2:"+this.group.optlock);
      console.log(result);
      this.getGroupList();
      this.router.navigate(['/home/group']);
    });
  }

  /**
   * 获取点击删除的小组的id
   * @param groupInfo
   */
  getGroupId(groupInfo:GroupInfo){
    console.log(groupInfo);
    this.delGroupId=groupInfo.id;
    console.log(this.delGroupId);
  }

  /**
   * 通过之前获取的用户点击的小组的id来删除小组的信息
   */
  deleteGroup() {
    console.log(this.delGroupId);
    this.groupService.deleteGroupByGroupId(this.delGroupId).subscribe((result) => {
      console.log(result);
      this.getGroupList();
      this.router.navigate(['/home/group']);
    });
  }

  /**
   * 为添加设备中的设备分组进行设置用户选择的组别
   */
  setGroup(){
    this.devicegroup=this.group;
    console.log(this.devicegroup);
  }


  /**
   * 获得用户点击的小组的信息
   * @param groupInfo
   */
  clickGroup(groupInfo:GroupInfo){
    this.cliGroup=groupInfo;
    console.log(this.cliGroup);
  }


  //--------------------Account Action---------------------------//
  /**
   * 获取当前scopeId下的所有的账号信息
   */
  getAccountList(){
    this.accountService.getAccountList().subscribe((result) => {
      console.log(result);
      this.accountInfos=result.items.item;
    });
  }

  /**
   * 添加一个账号信息
   */
  addAccount(){
    console.log(this.aAccount);
    this.accountService.addAccount(this.aAccount).subscribe((result) => {
      console.log(result);
      this.getAccountList();
      this.router.navigate(['/home/account']);
    });
  }

  /**
   * 得到当前用户点击的账户的信息
   * @param accountInfo
   */
  getAccountInfo(accountInfo:AccountInfo){
    console.log(accountInfo);
    this.account=accountInfo;
  }

  /**
   * 更新当前用户选择的账号信息
   */
  updateAccount(){
    console.log("optlock1:"+this.account.optlock);
    if(this.optlock==this.account.optlock+1){
      this.account.optlock+=1;
    }
    this.accountService.updateAccountById(this.account.id,this.account).subscribe((result) => {
      this.account=result;
      this.optlock=this.account.optlock;
      console.log("optlock2:"+this.account.optlock);
      console.log(result);
      this.getAccountList();
      this.router.navigate(['/home/account']);
    });
  }

  /**
   * 得到被删除的账号信息的id
   * @param accountInfo
   */
  getAccountId(accountInfo:AccountInfo){
    this.delAccountId=accountInfo.id;
  }

  /**
   * 根据之前得到的删除账号的id来进行删除操作
   */
  deleteAccount(){
    console.log(this.delAccountId);
    this.accountService.deleteAccountByAccountId(this.delAccountId).subscribe((result) => {
      console.log(result);
      this.getAccountList();
      this.router.navigate(['/home/account']);
    });
  }

  /**
   * 获取当前用户点击的账号信息
   * @param accountInfo
   */
  clickAccount(accountInfo:AccountInfo){
    this.cliAccount=accountInfo;
  }
}
