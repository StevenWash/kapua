"use strict";
/**
 * Created by StevenWash on 2017/6/16.
 */
Object.defineProperty(exports, "__esModule", { value: true });
var DeviceInfo = (function () {
    function DeviceInfo() {
        this.connection = new ConnectionModel();
        this.lastEvent = new LastEventModel();
    }
    return DeviceInfo;
}());
exports.DeviceInfo = DeviceInfo;
var LastEventModel = (function () {
    function LastEventModel() {
        this.position = new PositionModel();
    }
    return LastEventModel;
}());
exports.LastEventModel = LastEventModel;
var PositionModel = (function () {
    function PositionModel() {
    }
    return PositionModel;
}());
exports.PositionModel = PositionModel;
var ConnectionModel = (function () {
    function ConnectionModel() {
    }
    return ConnectionModel;
}());
exports.ConnectionModel = ConnectionModel;
//# sourceMappingURL=device.module.js.map