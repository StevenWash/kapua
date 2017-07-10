"use strict";
/**
 * Created by StevenWash on 2017/6/19.
 */
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var forms_1 = require("@angular/forms");
var core_1 = require("@angular/core");
function validateMobile(c) {
    var MOBILE_REGEXP = /^1[0-9]{10,10}$/;
    return MOBILE_REGEXP.test(c.value) ? null : {
        validateMobile: { valid: false }
    };
}
exports.validateMobile = validateMobile;
var MobileValidator = (function () {
    function MobileValidator() {
    }
    MobileValidator = __decorate([
        core_1.Directive({
            selector: '[validateMobile][ngModel]',
            providers: [
                { provide: forms_1.NG_VALIDATORS,
                    useValue: validateMobile,
                    multi: true
                }
            ]
        })
    ], MobileValidator);
    return MobileValidator;
}());
exports.MobileValidator = MobileValidator;
//# sourceMappingURL=adduser.validator.js.map