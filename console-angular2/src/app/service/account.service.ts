import {Injectable} from "@angular/core";
import {Http,Headers} from "@angular/http";
import {AccountInfo} from "../module/account-info.module";
import {HostInfo} from "../module/host.info.modeule";
/**
 * Created by StevenWash on 2017/6/28.
 */

@Injectable()
export class AccountService{
  private accountUrl:string;

  constructor (
    private http:Http
  ){}

  /**
   * 获取当前scopeId下的所有的账号信息
   * @returns {Observable<R>}
   */
  getAccountList(accountName:string){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');

    if(accountName!=null){
      this.accountUrl=HostInfo.ip+'/api/v1/'+scopeId+'/accounts?name='+accountName+'&offset=0&limit=50';
    }else
      this.accountUrl=HostInfo.ip+'/api/v1/'+scopeId+"/accounts?offset=0&limit=50";

    return this.http.get(this.accountUrl,{ headers: headers }).map(res => res.json());
  }

  /**
   * 添加一个账号信息
   * @param account
   * @returns {Observable<R>}
   */
  addAccount(account:AccountInfo){
    console.log(account);

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');

    this.accountUrl=HostInfo.ip+'/api/v1/'+scopeId+'/accounts';

    return this.http.post(this.accountUrl,JSON.stringify(account),{ headers: headers }).map(res => res.json());
  }

  /**
   * 通过accountId更新账号信息
   * @param accountId
   * @param account
   * @returns {Observable<R>}
   */
  updateAccountById(accountId:string,account:AccountInfo){
    console.log(account.name);
    console.log("accountId:"+accountId);
    console.log(account.optlock);

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');

    this.accountUrl=HostInfo.ip+'/api/v1/'+scopeId+'/accounts/'+accountId;

    return this.http.put(this.accountUrl,JSON.stringify(account),{ headers: headers }).map(res => res.json());
  }

  /**
   * 根据accountId来删除账号信息
   * @param accountId
   * @returns {Observable<Response>}
   */
  deleteAccountByAccountId(accountId:string){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');

    this.accountUrl=HostInfo.ip+'/api/v1/'+scopeId+'/accounts/'+accountId;
    return this.http.delete(this.accountUrl,{ headers: headers });
  }
}
