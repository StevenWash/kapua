/**
 * Created by StevenWash on 2017/6/16.
 */
import {Http,Headers} from "@angular/http";
import {Injectable} from "@angular/core";
import {DeviceInfo} from "../module/device.module";

@Injectable()
export class DeviceConnectionService {
  private deviceConnectionUrl:string;
  private deviceUrl:string;
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


  /**
   * 添加一个设备信息
   * @param deviceInfo
   * @returns {Observable<R>}
   */
  addDevice(deviceInfo:DeviceInfo){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');

    this.deviceUrl='https://dev.izhiju.cn/api/v1/'+scopeId+'/devices';

    return this.http.post(this.deviceUrl,JSON.stringify(deviceInfo),{ headers: headers }).map(res => res.json());
  }

  /**
   * 通过设备的id来删除该设备
   * @param deviceId
   * @returns {Observable<Response>}
   */
  deleteDeviceByDeviceId(deviceId:string){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');
    console.log(headers);
    console.log(deviceId);

    this.deviceUrl='https://dev.izhiju.cn/api/v1/'+scopeId+'/devices/'+deviceId;
    return this.http.delete(this.deviceUrl,{ headers: headers });
  }

  /**
   * 通过==设备的id来更新设备的信息
   * @param deviceId
   * @param device
   * @returns {Observable<R>}
   */
  updateDeviceById(deviceId:string,device:DeviceInfo){
    console.log(device.clientId);
    console.log("deviceId:"+deviceId);
    console.log(device.optlock);

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let authToken = localStorage.getItem('tokenId');
    headers.append('Authorization', `Bearer ${authToken}`);

    let scopeId = localStorage.getItem('scopeId');

    this.deviceUrl='https://dev.izhiju.cn/api/v1/'+scopeId+'/devices/'+deviceId;

    return this.http.put(this.deviceUrl,JSON.stringify(device),{ headers: headers }).map(res => res.json());
  }
}