/**
 * Created by StevenWash on 2017/6/14.
 */
export class Creditial{
  constructor(
    private username:string,
    private password:string
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
