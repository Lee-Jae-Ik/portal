package com.lji.portal.model.response.result;

import com.lji.portal.model.dto.UserBasic;
import com.lji.portal.model.response.ApiResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ApiUserEntityResponse
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/10/30
 */
@Getter
@AllArgsConstructor
public class ApiUserEntityResponse<T extends UserBasic> {
    private ApiResult apiResult;
    private T data;

    public ApiUserEntityResponse(ApiResult apiResult, T data, String message) {
        this.apiResult = apiResult;
        this.data = data;
        apiResult.setMessage(message);
    }

    public int getCode() {
        return apiResult.getCode();
    }
    public String getMessage() { return apiResult.getMessage(); }
}
