/**
 * Created by StevenWash on 2017/6/15.
 */

export class UserInfo{
  private id:number;
  private name:string;
  private createdOn:string;
  private createdBy:number;
  private modifiedOn:number;
  private status:string;
  private displayName:string;
  private email:string;
  private phoneNumber:string;
  private userType:string;

  constructor(
   name:string,
   createdOn:string,
   createdBy:number,
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

}

export class UserResponse{
  private type: string;
  private limitExceeded: boolean;
  private size: number;
  private items: {
    item: UserInfo[];
  };

}
