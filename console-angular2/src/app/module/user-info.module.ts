/**
 * Created by StevenWash on 2017/6/15.
 */

export class UserInfo{
  private type:string;
  private id:string;
  private scopeId:string;
  private createdOn:string;
  private createdBy:string;
  private modifiedOn:string;
  private modifiedBy:string;
  private optlock:number;
  private name:string;
  private status:string;
  private displayName:string;
  private email:string;
  private phoneNumber:string;
  private userType:string;

  constructor(
   name:string,
   createdOn:string,
   createdBy:string,
   status:string,
   displayName:string,
   email:string,
   phoneNumber:string,
  ){
    this.name=name;
    this.createdBy=createdBy;
    this.createdOn=createdOn;
    this.status=status;
    this.displayName=displayName;
    this.email=email;
    this.phoneNumber=phoneNumber;
  }

  getUserName(){
     return this.displayName;
  }

}

export class UserResponse{
  private type: string;
  private limitExceeded: boolean;
  private size: number;
  private items: {
    item: UserInfo[];
  };

}
