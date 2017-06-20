/**
 * Created by StevenWash on 2017/6/14.
 */
export class Creditial{

   username:string;
   password:string;
   repassword:string;
   credentialType:string;
   credentialPlainKey:string;
   scopeId:string;
   userId:string;

  constructor(
  ){}


  getUsername():string{
      return this.username;
  }

  setUsername(username:string){
    this.username=username;
  }

  getPassword():string{
    return this.password;
  }

  setPassword(password:string){
    this.password=password;
  }
}
