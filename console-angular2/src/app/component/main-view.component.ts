/**
 * Created by StevenWash on 2017/6/14.
 */
import {Component} from "@angular/core";
import {UserListService} from "../service/user-list.service";
import {UserInfo, UserResponse} from "../module/user-info.module";


@Component({
  selector: 'main-view',
  templateUrl: 'app/static/main-view.component.html'
})
export class MainViewComponent{
  private userResponse:UserResponse;
  private userInfos:UserInfo[];
  private user:UserInfo;
  private loginUser:UserInfo;

  constructor(
    private userListService:UserListService
  ){

    this.getUserList();
  }

  /**
   * 得到用户的所有信息
   */
  getUserList(){
      this.userListService.getUserList().subscribe((result) => {
        this.userInfos=result.items.item;
    });
  }

  getUserByName(name:string){
    console.log(name);

  }

}
