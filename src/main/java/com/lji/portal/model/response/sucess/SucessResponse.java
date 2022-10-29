package com.lji.portal.model.response.sucess;

import com.lji.portal.model.response.ApiResponse;
import com.lji.portal.model.response.ApiResult;

/**
 * SucessResponse
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
public class SucessResponse<T> extends ApiResponse {
    public SucessResponse(T data) {
        super(ApiResult.SUCESS,data);
    }
}
