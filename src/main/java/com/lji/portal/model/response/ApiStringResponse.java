package com.lji.portal.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ApiResponse
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
@Getter
@AllArgsConstructor
public class ApiStringResponse {

    private ApiResult apiResult;
    private String data;

    public ApiStringResponse(ApiResult apiResult, String data, String message) {
        this.apiResult = apiResult;
        this.data = data;
        apiResult.setMessage(message);
    }

    public int getCode() {
        return apiResult.getCode();
    }
    public String getMessage() { return apiResult.getMessage(); }
}
