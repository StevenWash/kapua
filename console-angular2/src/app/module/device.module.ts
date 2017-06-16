/**
 * Created by StevenWash on 2017/6/16.
 */

export class DeviceInfo{
  private type:string;
  private id:string;
  private scopeId:string;
  private createdOn:string;
  private createdBy:string;
  private modifiedOn:string;
  private modifiedBy:string;
  private optlock:number;
  private groupId:string;
  private clientId:string;
  private status:string;
  private displayName:string;
  private deviceCredentialsMode:string;
  private acceptEncoding: string;
  private applicationIdentifiers: string;
  private biosVersion: string;
  private connection: ConnectionModel;
  private connectionId: string;
  private firmwareVersion: string;
  private jvmVersion: string;
  private lastEvent: LastEventModel;
  private lastEventId: string;
  private modelId: string;
  private osVersion: string;
  private osgiFrameworkVersion: string;
  private serialNumber: string;
}

export class LastEventModel {
  private action: string;
  private createdBy: string;
  private deviceId: any;
  private eventMessage: string;
  private id: string;
  private position: PositionModel;
  private receivedOn: string;
  private resource: string;
  private responseCode: string;
  private scopeId: string;
}

export class PositionModel {
  private altitude: number;
  private latitude: number;
  private longitude: number;
}

export class ConnectionModel {
  private clientId: string;
  private clientIp: string;
  private createdBy: string;
  private createdOn: string;
  private id: string;
  private modifiedBy: string;
  private modifiedOn: string;
  private optlock: number;
  private protocol: string;
  private scopeId: string;
  private status: string;
  private userId: any;
}
