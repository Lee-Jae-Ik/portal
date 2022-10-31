package com.lji.portal.model.response.sucess;

import com.lji.portal.model.dto.UserBasic;
import com.lji.portal.model.response.ApiResult;
import com.lji.portal.model.response.result.ApiListObjectResponse;
import com.lji.portal.model.response.result.ApiUserEntityResponse;

import java.util.List;

/**
 * SucessResponse
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
public class SucessListObjectResponse<T> extends ApiListObjectResponse {
    public SucessListObjectResponse(List<T> data) {
        super(ApiResult.SUCESS,data);
    }
}
