package com.lji.portal.model.response.sucess;

import com.lji.portal.model.dto.UserBasic;
import com.lji.portal.model.entity.User;
import com.lji.portal.model.response.ApiResult;
import com.lji.portal.model.response.ApiStringResponse;
import com.lji.portal.model.response.ApiUserEntityResponse;

/**
 * SucessResponse
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
public class SucessUserEntityResponse<T extends UserBasic> extends ApiUserEntityResponse {
    public SucessUserEntityResponse(T data) {
        super(ApiResult.SUCESS,data);
    }
}
