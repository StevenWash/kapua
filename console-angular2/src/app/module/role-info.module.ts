/**
 * Created by StevenWash on 2017/6/16.
 */
import {Permission} from "./permissions.module";

export class RoleInfo{
   type:string;
   id:string;
   scopeId:string;
   createOn:string;
   createBy:string;
   modifiedOn:string;
   modifiedBy:string;
   optlock:number;
   name:string;
   permissions:Permission=new Permission();
}
