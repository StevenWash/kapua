package org.eclipse.kapua.service.weather.internal;

import org.eclipse.kapua.KapuaErrorCode;

/**
 * Account error codes
 * 
 * since 1.0
 * 
 */
public enum KapuaWeatherErrorCodes implements KapuaErrorCode {
    /**
     * Internal error
     */
    INTERNAL_ERROR,
    /**
     * Illegal argument
     */
    ILLEGAL_ARGUMENT,
    /**
     * Operation not allowed
     */
    OPERATION_NOT_ALLOWED;
}
