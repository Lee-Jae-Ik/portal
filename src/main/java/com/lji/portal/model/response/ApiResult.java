package com.lji.portal.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * ApiResult
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
@Getter
public enum ApiResult {

    //commons
    SUCESS(200,"sucess"),
    SERVER_ERROR(9999, "server error"),
    NO_DATA(9998, "no data"),

    //user error
    DUPLICATION_USER(1000, "user duplicastion")
    ;

    private int code;
    @Setter
    private String message;

    ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
