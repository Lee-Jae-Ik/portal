package com.lji.portal.exception;

import com.lji.portal.model.response.ApiResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * ApiRuntimeException
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
@Slf4j
@Getter
public class ApiRuntimeException extends RuntimeException {
    private ApiResult apiResult;

    public ApiRuntimeException() {
        super();
        apiResult = ApiResult.SERVER_ERROR;
    }

    public ApiRuntimeException(ApiResult apiResult) {
        super(apiResult.getMessage());
        this.apiResult = apiResult;
    }

    public ApiRuntimeException(ApiResult apiResult, String message) {
        super(message);
        apiResult.setMessage(message);
        this.apiResult = apiResult;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
