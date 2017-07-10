"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * Created by StevenWash on 2017/6/14.
 */
var core_1 = require("@angular/core");
var user_service_1 = require("../service/user.service");
var user_info_module_1 = require("../module/user-info.module");
var role_service_1 = require("../service/role.service");
var role_info_module_1 = require("../module/role-info.module");
var device_connection_service_1 = require("../service/device-connection.service");
var device_module_1 = require("../module/device.module");
var group_service_1 = require("../service/group.service");
var group_info_module_1 = require("../module/group-info.module");
var creditial_module_1 = require("../module/creditial.module");
var router_1 = require("@angular/router");
var login_service_1 = require("../service/login.service");
var account_service_1 = require("../service/account.service");
var account_info_module_1 = require("../module/account-info.module");
var access_role_module_1 = require("../module/access-role.module");
var MainViewComponent = (function () {
    /**
     * 构造函数，在构造器里面进行依赖注入
     * @param userListService
     * @param roleService
     * @param deviceConnectionService
     */
    function MainViewComponent(userListService, roleService, deviceConnectionService, groupService, router, loginService, accountService) {
        this.userListService = userListService;
        this.roleService = roleService;
        this.deviceConnectionService = deviceConnectionService;
        this.groupService = groupService;
        this.router = router;
        this.loginService = loginService;
        this.accountService = accountService;
        this.aUser = new user_info_module_1.UserInfo();
        this.aCredential = new creditial_module_1.Credential();
        this.userCredential = new creditial_module_1.Credential();
        this.showPassInput = false; //再添加用户密码的时候判断是否显示密码输入框
        this.aRole = new role_info_module_1.RoleInfo();
        this.aGroup = new group_info_module_1.GroupInfo();
        this.aDevice = new device_module_1.DeviceInfo();
        this.cliDevice = new device_module_1.DeviceInfo();
        this.aAccount = new account_info_module_1.AccountInfo();
        this.cliAccount = new account_info_module_1.AccountInfo();
        this.aAccountUser = new user_info_module_1.UserInfo();
        this.aAccountCredential = new creditial_module_1.Credential();
        console.log("home..");
        if (localStorage.getItem('userId') == null || localStorage.getItem('userId').length == 0) {
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
    MainViewComponent.prototype.logout = function () {
        console.log("logout....");
        this.loginService.logout();
        this.router.navigate(['/login']);
    };
    /**
     * 得到所有用户的所有信息
     */
    MainViewComponent.prototype.getUserList = function () {
        var _this = this;
        this.userListService.getUserList(this.inputUsername).subscribe(function (result) {
            console.log(result);
            _this.userInfos = result.items.item;
        });
    };
    /**
     * 通过用户名获取用户信息
     * @param name
     */
    MainViewComponent.prototype.getLoginUser = function () {
        var _this = this;
        this.userListService.getLoginUser().subscribe(function (result) {
            _this.displayName = result.displayName;
            _this.email = result.email;
        });
    };
    /**
     * 在用户管理界面点击update之后将当前用户的信息显示在update的提示框中
     * @param userInfo
     */
    MainViewComponent.prototype.getUserInfo = function (userInfo) {
        console.log(userInfo);
        this.user = userInfo;
    };
    /**
     * 更新用户的信息
     */
    MainViewComponent.prototype.updateUser = function () {
        var _this = this;
        console.log("optlock1:" + this.user.optlock);
        if (this.optlock == this.user.optlock + 1) {
            this.user.optlock += 1;
        }
        this.userListService.updateUserById(this.user.id, this.user).subscribe(function (result) {
            _this.user = result;
            _this.optlock = _this.user.optlock;
            console.log("optlock2:" + _this.user.optlock);
            console.log(result);
            _this.getUserList();
            _this.router.navigate(['/home/user']);
        });
    };
    /**
     * 添加用户信息
     */
    MainViewComponent.prototype.addUser = function () {
        var _this = this;
        console.log(this.aUser);
        this.userListService.addUser(this.aUser).subscribe(function (result) {
            console.log(result);
            _this.aCredential.userId = result.id;
            _this.aCredential.credentialType = "PASSWORD";
            _this.aCredential.credentialKey = _this.aCredential.password;
            console.log(_this.aCredential);
            _this.userListService.addCredential(_this.aCredential).subscribe(function (result) {
                console.log(result);
            });
            _this.getUserList();
            _this.router.navigate(['/home/user']);
        });
    };
    /**
     * 点击删除之后将当前点击的用户的id传入
     * @param userInfo
     */
    MainViewComponent.prototype.getUserId = function (userInfo) {
        console.log(userInfo);
        this.delUserId = userInfo.id;
        console.log(this.delUserId);
    };
    /**
     * 删除用户信息
     */
    MainViewComponent.prototype.deleteUser = function () {
        var _this = this;
        console.log(this.delUserId);
        this.userListService.deleteUser(this.delUserId).subscribe(function (result) {
            console.log(result);
            _this.getUserList();
            _this.router.navigate(['/home/user']);
        });
    };
    /**
     * 根据用户id获取该用户的所有的密码信息
     * @param userInfo
     */
    MainViewComponent.prototype.getUserCredentials = function (userId) {
        var _this = this;
        this.userListService.getCredentialsByUserId(userId).subscribe(function (result) {
            console.log(result);
            _this.cliUserCredentials = result.items.item;
        });
    };
    /**
     * 通过userId获取所有的角色信息
     * @param userId
     */
    MainViewComponent.prototype.getUserRolesAndPermissionByUserId = function (userId) {
        var _this = this;
        var roleList = [];
        var permissionList = [];
        this.roleService.getAccessInfosByUserId(userId).then(function (accessInfos) {
            _this.accessInfos = accessInfos;
            console.log(_this.accessInfos);
            _this.roleService.getAccessRolesByAccessInfoId(_this.accessInfos[0].id).then(function (accessRoles) {
                _this.accessRoles = accessRoles;
                console.log(_this.accessRoles);
                var len = _this.accessRoles.length;
                console.log("len:" + len);
                for (var i = 0; i < len; i++) {
                    _this.roleService.getRoleByRoleId(_this.accessRoles[i].roleId).then(function (result) {
                        console.log(result);
                        roleList.push(result);
                        while (roleList.length >= len) {
                            _this.userRoles = roleList;
                            console.log(_this.userRoles);
                            break;
                        }
                    });
                    _this.roleService.getPermissionByRoleId(_this.accessRoles[i].roleId).then(function (result) {
                        console.log(result);
                        for (var j = 0; j < result.length; j++) {
                            permissionList.push(result[j]);
                            console.log(permissionList);
                            _this.userPermissions = permissionList;
                            console.log(_this.userPermissions);
                        }
                    });
                }
            });
        });
    };
    /**
     * 用户信息被点击之后触发的函数
     * @param userInfo
     */
    MainViewComponent.prototype.clickUser = function (userInfo) {
        this.userRoles = null;
        this.userPermissions = null;
        this.cliUser = userInfo;
        console.log(this.cliUser);
        //根据该点击用户的id获取点击用户的所有密码信息
        this.getUserCredentials(this.cliUser.id);
        //根据用户的userId获取当前用户的角色信息
        this.getUserRolesAndPermissionByUserId(this.cliUser.id);
    };
    /**
     * 将用户界面的搜索框中的数据进行重置
     */
    MainViewComponent.prototype.resetUserInput = function () {
        this.inputUsername = null;
        this.getUserList();
    };
    /**
     * 添加用户的密码信息
     */
    MainViewComponent.prototype.submitUserCredential = function () {
        var _this = this;
        console.log(this.userCredential);
        if (this.userCredential.password == this.userCredential.repassword) {
            console.log("提交密码");
            console.log(this.cliUser);
            this.userCredential.userId = this.cliUser.id;
            this.userCredential.credentialKey = this.userCredential.password;
            console.log(this.userCredential);
            this.userListService.addCredential(this.userCredential).subscribe(function (result) {
                console.log(result);
                //根据该点击用户的id获取点击用户的所有密码信息
                _this.getUserCredentials(_this.cliUser.id);
            });
        }
    };
    /**
     * 设置密码的类型
     * @param value
     */
    MainViewComponent.prototype.setUserCredentialType = function () {
        if (this.userCredential.credentialType == "PASSWORD") {
            console.log("设置密码");
            this.showPassInput = true;
        }
        else {
            console.log("设置API_KEY");
            this.showPassInput = false;
        }
        console.log(this.userCredential.credentialType);
    };
    /**
     * 这是点击的用户的密码信息
     * @param userCredentialInfo
     */
    MainViewComponent.prototype.getUserCredentialInfo = function (userCredentialInfo) {
        this.cliUserCredential = userCredentialInfo;
    };
    /**
     * 提交用户的修改密码信息
     */
    MainViewComponent.prototype.submitUpdateUserCredential = function () {
        var _this = this;
        console.log(this.cliUserCredential);
        if (this.cliUserCredential.password == this.cliUserCredential.repassword) {
            this.cliUserCredential.credentialKey = this.cliUserCredential.password;
            console.log(this.cliUserCredential);
            this.userListService.updateCredential(this.cliUserCredential).subscribe(function (result) {
                console.log(result);
                //根据该点击用户的id获取点击用户的所有密码信息
                _this.getUserCredentials(_this.cliUser.id);
            });
        }
    };
    /**
     * 获取点击之后的用户密码的id
     * @param userCredential
     */
    MainViewComponent.prototype.getUserCredentialId = function (userCredential) {
        this.delUserCredentialId = userCredential.id;
    };
    /**
     * 删除该条用户的密码信息
     */
    MainViewComponent.prototype.deleteUserCredential = function () {
        var _this = this;
        console.log(this.delUserCredentialId);
        this.userListService.deleteUserCredential(this.delUserCredentialId).subscribe(function (result) {
            console.log(result);
            _this.getUserCredentials(_this.cliUser.id);
        });
    };
    /**
     * 在用户界面点击添加角色信息之后提交角色信息
     */
    MainViewComponent.prototype.submitUserRole = function () {
        var _this = this;
        console.log(this.userRoleId);
        var accessInfo = new access_role_module_1.AccessInfo();
        accessInfo.userId = this.cliUser.id;
        this.userListService.addAccessInfo(accessInfo).subscribe(function (result) {
            console.log(result);
            accessInfo = result;
            var accessRole = new access_role_module_1.AccessRole();
            accessRole.accessInfoId = accessInfo.id;
            accessRole.roleId = _this.userRoleId;
            console.log(accessRole);
            _this.roleService.addAccessRole(accessRole).subscribe(function (result) {
                console.log(result);
            });
        });
    };
    //-------------------Role Action ------------------//
    /**
     * 得到所有的角色信息
     */
    MainViewComponent.prototype.getRoleList = function () {
        var _this = this;
        this.roleService.getRoles(this.inputRolename).subscribe(function (result) {
            console.log(result);
            _this.roleInfos = result.items.item;
        });
    };
    /**
     * 得到当前的role信息
     * @param roleInfo
     */
    MainViewComponent.prototype.getRoleInfo = function (roleInfo) {
        console.log(roleInfo);
        this.role = roleInfo;
    };
    /**
     * 更新角色信息
     */
    MainViewComponent.prototype.updateRole = function () {
        var _this = this;
        console.log("optlock1:" + this.role.optlock);
        if (this.optlock == this.role.optlock + 1) {
            this.role.optlock += 1;
        }
        this.roleService.updateROleById(this.role.id, this.role).subscribe(function (result) {
            _this.role = result;
            _this.optlock = _this.role.optlock;
            console.log(result);
            _this.getRoleList();
            _this.router.navigate(['/home/role']);
        });
    };
    /**
     * 添加角色信息
     */
    MainViewComponent.prototype.addRole = function () {
        var _this = this;
        console.log(this.aRole);
        this.roleService.addRole(this.aRole).subscribe(function (result) {
            console.log(result);
            _this.getRoleList();
            _this.router.navigate(['/home/role']);
        });
    };
    /**
     * 通过当前点击的角色的信息来获取该角色的id
     * @param roleInfo
     */
    MainViewComponent.prototype.getRoleId = function (roleInfo) {
        console.log(roleInfo);
        this.delRoleId = roleInfo.id;
        console.log(this.delRoleId);
    };
    /**
     * 通过点击角色的id信息来删除角色信息
     */
    MainViewComponent.prototype.deleteRole = function () {
        var _this = this;
        console.log(this.delRoleId);
        this.roleService.deleteRoleByRoleId(this.delRoleId).subscribe(function (result) {
            console.log(result);
            _this.getRoleList();
            _this.router.navigate(['/home/role']);
        });
    };
    /**
     * 获取用户点击的角色的信息
     * @param roleInfo
     */
    MainViewComponent.prototype.clickRole = function (roleInfo) {
        var _this = this;
        this.cliRole = roleInfo;
        console.log(this.cliRole);
        //获取当前角色的所有权限信息
        this.roleService.getRolePermissionByRole(this.cliRole.id).subscribe(function (result) {
            console.log(result);
            _this.rolePermissions = result.items.item;
            console.log(_this.rolePermissions);
        });
        //获取当前角色的子用户
        /*let len=this.userInfos.length;
        for(var i=0;i<len;i++){
          this.userInfos[i]
        }*/
        //获取当前所有的domain信息
    };
    /**
     * 将角色搜索界面的输入框重置
     */
    MainViewComponent.prototype.resetRoleInput = function () {
        this.inputRolename = null;
        this.getRoleList();
    };
    //--------------------DeviceConnection Action---------------------------//
    /**
     * 得到所有的设备连接信息
     */
    MainViewComponent.prototype.getDeviceConnection = function () {
        var _this = this;
        console.log(this.inputDeviceConnClientId + "  " + this.inputDeviceConnClientStatus);
        this.deviceConnectionService.getDeviceConnection(this.inputDeviceConnClientId, this.inputDeviceConnClientStatus).subscribe(function (result) {
            console.log(result);
            _this.deviceConnections = result.items.item;
        });
    };
    /**
     * 获取用户点击的设备连接信息
     * @param deviceConnection
     */
    MainViewComponent.prototype.clickConnection = function (deviceConnection) {
        this.cliConnection = deviceConnection;
        console.log(this.cliConnection);
    };
    /**
     * 点击重置按钮之后的操作事件
     */
    MainViewComponent.prototype.resetInputDeviceConn = function () {
        this.inputDeviceConnClientId = null;
        this.inputDeviceConnClientStatus = null;
        this.getDeviceConnection();
    };
    //--------------------Device Action---------------------------//
    /**
     * 得到所有的设备信息
     */
    MainViewComponent.prototype.getDevices = function () {
        var _this = this;
        console.log(this.inputDeviceClientId + "  " + this.inputDeviceConStatus);
        this.deviceConnectionService.getDeviceList(this.inputDeviceClientId, this.inputDeviceConStatus).subscribe(function (result) {
            _this.deviceInfos = result.items.item;
        });
    };
    /**
     * 添加设备信息
     */
    MainViewComponent.prototype.addDevice = function () {
        var _this = this;
        this.aDevice.deviceCredentialsMode = "LOOSE"; //设置默认值
        console.log(this.aDevice);
        this.deviceConnectionService.addDevice(this.aDevice).subscribe(function (result) {
            console.log(result);
            _this.deviceInfos = null;
            _this.getDevices();
            _this.router.navigate(['/home/device']);
        });
    };
    /**
     * 得到当前用户点击的设备信息的id
     * @param deviceInfo
     */
    MainViewComponent.prototype.getDeviceId = function (deviceInfo) {
        console.log(deviceInfo);
        this.delDeviceId = deviceInfo.id;
        console.log(this.delDeviceId);
    };
    /**
     * 通过用户选择的设备的id删除该设备信息
     */
    MainViewComponent.prototype.deleteDevice = function () {
        var _this = this;
        console.log(this.delDeviceId);
        this.deviceConnectionService.deleteDeviceByDeviceId(this.delDeviceId).subscribe(function (result) {
            console.log(result);
            _this.getDevices();
            _this.router.navigate(['/home/device']);
        });
    };
    /**
     * 得到当前用户点击的设备信息
     * @param deviceInfo
     */
    MainViewComponent.prototype.getDeviceInfo = function (deviceInfo) {
        console.log(deviceInfo);
        this.device = deviceInfo;
    };
    /**
     * 根据的设备的id来更新设备的相关信息
     */
    MainViewComponent.prototype.updateDevice = function () {
        var _this = this;
        console.log("optlock1:" + this.device.optlock);
        if (this.deviceOptlock == this.device.optlock + 1) {
            this.device.optlock += 1;
        }
        console.log(this.device);
        this.deviceConnectionService.updateDeviceById(this.device.id, this.device).subscribe(function (result) {
            _this.device = result;
            _this.deviceOptlock = _this.device.optlock;
            console.log("optlock2:" + _this.device.optlock);
            console.log(result);
            _this.getDevices();
            _this.router.navigate(['/home/device']);
        });
    };
    /**
     * 获得点击的设备信息
     * @param deviceInfo
     */
    MainViewComponent.prototype.clickDevice = function (deviceInfo) {
        this.cliDevice = deviceInfo;
        console.log(this.cliDevice);
    };
    /**
     * 点击充值按钮之后的处理过程
     */
    MainViewComponent.prototype.resetInputDevice = function () {
        this.inputDeviceConStatus = null;
        this.inputDeviceClientId = null;
        this.getDevices();
    };
    //--------------------Group Action---------------------------//
    /**
     * 得到所有的组信息
     */
    MainViewComponent.prototype.getGroupList = function () {
        var _this = this;
        this.groupService.getGroupList(this.inputGroupname).subscribe(function (result) {
            console.log(result);
            _this.groupInfos = result.items.item;
        });
    };
    /**
     * 添加组信息
     */
    MainViewComponent.prototype.addGroup = function () {
        var _this = this;
        console.log(this.aGroup);
        this.groupService.addGroup(this.aGroup).subscribe(function (result) {
            console.log(result);
            _this.getGroupList();
            _this.router.navigate(['/home/group']);
        });
    };
    /**
     * 得到用户当前点击的组的信息
     * @param groupInfo
     */
    MainViewComponent.prototype.getGroupInfo = function (groupInfo) {
        console.log(groupInfo);
        this.group = groupInfo;
    };
    /**
     * 更新的用户当前的点击小组
     */
    MainViewComponent.prototype.updateGroup = function () {
        var _this = this;
        console.log("optlock1:" + this.group.optlock);
        if (this.optlock == this.group.optlock + 1) {
            this.group.optlock += 1;
        }
        this.groupService.updateGroupById(this.group.id, this.group).subscribe(function (result) {
            _this.group = result;
            _this.optlock = _this.group.optlock;
            console.log("optlock2:" + _this.group.optlock);
            console.log(result);
            _this.getGroupList();
            _this.router.navigate(['/home/group']);
        });
    };
    /**
     * 获取点击删除的小组的id
     * @param groupInfo
     */
    MainViewComponent.prototype.getGroupId = function (groupInfo) {
        console.log(groupInfo);
        this.delGroupId = groupInfo.id;
        console.log(this.delGroupId);
    };
    /**
     * 通过之前获取的用户点击的小组的id来删除小组的信息
     */
    MainViewComponent.prototype.deleteGroup = function () {
        var _this = this;
        console.log(this.delGroupId);
        this.groupService.deleteGroupByGroupId(this.delGroupId).subscribe(function (result) {
            console.log(result);
            _this.getGroupList();
            _this.router.navigate(['/home/group']);
        });
    };
    /**
     * 为添加设备中的设备分组进行设置用户选择的组别
     */
    MainViewComponent.prototype.setGroup = function () {
        this.devicegroup = this.group;
        console.log(this.devicegroup);
    };
    /**
     * 获得用户点击的小组的信息
     * @param groupInfo
     */
    MainViewComponent.prototype.clickGroup = function (groupInfo) {
        this.cliGroup = groupInfo;
        console.log(this.cliGroup);
    };
    /**
     * 将小组的搜索框的输入信息重置
     */
    MainViewComponent.prototype.resetGroupInput = function () {
        this.inputGroupname = null;
        this.getGroupList();
    };
    //--------------------Account Action---------------------------//
    /**
     * 获取当前scopeId下的所有的账号信息
     */
    MainViewComponent.prototype.getAccountList = function () {
        var _this = this;
        this.accountService.getAccountList(this.inputAccountName).subscribe(function (result) {
            console.log(result);
            _this.accountInfos = result.items.item;
        });
    };
    /**
     * 添加一个账号信息
     */
    MainViewComponent.prototype.addAccount = function () {
        var _this = this;
        console.log(this.aAccount);
        this.accountService.addAccount(this.aAccount).subscribe(function (result) {
            console.log(result);
            _this.getAccountList();
            _this.router.navigate(['/home/account']);
        });
    };
    /**
     * 得到当前用户点击的账户的信息
     * @param accountInfo
     */
    MainViewComponent.prototype.getAccountInfo = function (accountInfo) {
        console.log(accountInfo);
        this.account = accountInfo;
    };
    /**
     * 更新当前用户选择的账号信息
     */
    MainViewComponent.prototype.updateAccount = function () {
        var _this = this;
        console.log("optlock1:" + this.account.optlock);
        if (this.optlock == this.account.optlock + 1) {
            this.account.optlock += 1;
        }
        this.accountService.updateAccountById(this.account.id, this.account).subscribe(function (result) {
            _this.account = result;
            _this.optlock = _this.account.optlock;
            console.log("optlock2:" + _this.account.optlock);
            console.log(result);
            _this.getAccountList();
            _this.router.navigate(['/home/account']);
        });
    };
    /**
     * 得到被删除的账号信息的id
     * @param accountInfo
     */
    MainViewComponent.prototype.getAccountId = function (accountInfo) {
        this.delAccountId = accountInfo.id;
    };
    /**
     * 根据之前得到的删除账号的id来进行删除操作
     */
    MainViewComponent.prototype.deleteAccount = function () {
        var _this = this;
        console.log(this.delAccountId);
        this.accountService.deleteAccountByAccountId(this.delAccountId).subscribe(function (result) {
            console.log(result);
            _this.getAccountList();
            _this.router.navigate(['/home/account']);
        });
    };
    /**
     * 通过accounId来获取当前账号下的所有的用户信息
     * @param accountId
     */
    MainViewComponent.prototype.getAccountUsers = function (accountId) {
        var _this = this;
        this.accountService.getAccountUsers(accountId).subscribe(function (result) {
            console.log(result);
            _this.accountUsers = result.items.item;
        });
    };
    /**
     * 获取当前用户点击的账号信息
     * @param accountInfo
     */
    MainViewComponent.prototype.clickAccount = function (accountInfo) {
        this.cliAccount = accountInfo;
        console.log(this.cliAccount);
        //获得当前点击账号的用户信息
        this.getAccountUsers(this.cliAccount.id);
    };
    /**
     * 获取在Account下用户点击用户信息
     * @param accountUser
     */
    MainViewComponent.prototype.getAccountUserInfo = function (accountUser) {
        this.accountUser = accountUser;
    };
    /**
     * 更新账号下的用户信息
     */
    MainViewComponent.prototype.updateAccountUser = function () {
        var _this = this;
        console.log("optlock1:" + this.accountUser.optlock);
        if (this.accoptlock == this.accountUser.optlock + 1) {
            this.accountUser.optlock += 1;
        }
        this.userListService.updateUserById(this.accountUser.id, this.accountUser).subscribe(function (result) {
            _this.accountUser = result;
            _this.accoptlock = _this.accountUser.optlock;
            console.log("optlock2:" + _this.accountUser.optlock);
            console.log(result);
            _this.getAccountUsers(_this.cliAccount.id);
            _this.router.navigate(['/home/account']);
        });
    };
    /**
     * 添加用户
     */
    MainViewComponent.prototype.addAccountUser = function () {
        var _this = this;
        console.log(this.aAccountUser);
        console.log(this.aAccountCredential);
        this.accountService.addAccountUser(this.cliAccount.id, this.aAccountUser).subscribe(function (result) {
            console.log(result);
            _this.aAccountCredential.userId = result.id;
            _this.aAccountCredential.credentialType = "PASSWORD";
            _this.aAccountCredential.credentialKey = _this.aAccountCredential.password;
            console.log(_this.aAccountCredential);
            _this.accountService.addAccountCredential(_this.cliAccount.id, _this.aAccountCredential).subscribe(function (result) {
                console.log(result);
            });
            _this.getAccountUsers(_this.cliAccount.id);
            _this.router.navigate(['/home/account']);
        });
    };
    /**
     * 获取将要删除的用户的id
     * @param userInfo
     */
    MainViewComponent.prototype.getAccountUserId = function (userInfo) {
        this.delAccUserId = userInfo.id;
    };
    /**
     * 删除账号下的用户信息
     */
    MainViewComponent.prototype.deleteAccountUser = function () {
        var _this = this;
        console.log(this.delAccUserId);
        this.accountService.deleteAccountUser(this.cliAccount.id, this.delAccUserId).subscribe(function (result) {
            console.log(result);
            _this.getAccountUsers(_this.cliAccount.id);
            _this.router.navigate(['/home/account']);
        });
    };
    MainViewComponent = __decorate([
        core_1.Component({
            selector: 'main-view',
            templateUrl: 'app/static/main-view.component.html',
            styleUrls: ['app/css/static/main.view.css']
        }),
        __metadata("design:paramtypes", [user_service_1.UserListService,
            role_service_1.RoleService,
            device_connection_service_1.DeviceConnectionService,
            group_service_1.GroupService,
            router_1.Router,
            login_service_1.LoginService,
            account_service_1.AccountService])
    ], MainViewComponent);
    return MainViewComponent;
}());
exports.MainViewComponent = MainViewComponent;
//# sourceMappingURL=main-view.component.js.map