package com.lji.portal.model.response.result;

import com.lji.portal.model.response.ApiResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.simple.JSONObject;

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
public class ApiJsonResponse {

    private ApiResult apiResult;
    private JSONObject data;

    public ApiJsonResponse(ApiResult apiResult, JSONObject data, String message) {
        this.apiResult = apiResult;
        this.data = data;
        apiResult.setMessage(message);
    }

    public int getCode() {
        return apiResult.getCode();
    }
    public String getMessage() { return apiResult.getMessage(); }
}
