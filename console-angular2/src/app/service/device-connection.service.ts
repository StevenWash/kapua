/**
 * Created by StevenWash on 2017/6/16.
 */
import {Http,Headers} from "@angular/http";
import {Injectable} from "@angular/core";

@Injectable()
export class DeviceConnectionService {
  private deviceConnectionUrl:string;
  constructor(private  http:Http){}

  /**
   * 得到设备的所有连接属性
   * @returns {Observable<R>}
   */
  getDeviceConnection(){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    this.deviceConnectionUrl="https://dev.izhiju.cn/api/v1/_/deviceconnections?offset=0&limit=50";

    return this.http.get(this.deviceConnectionUrl,{ headers: headers }).map(res => res.json());

  }

  /**
   * 得到所有的设备信息
   * @returns {Observable<R>}
   */
  getDeviceList(){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    this.deviceConnectionUrl="https://dev.izhiju.cn/api/v1/_/devices?offset=0&limit=50";

    return this.http.get(this.deviceConnectionUrl,{ headers: headers }).map(res => res.json());

  }
}
