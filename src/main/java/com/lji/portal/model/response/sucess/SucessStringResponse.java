package com.lji.portal.model.response.sucess;

import com.lji.portal.model.response.ApiStringResponse;
import com.lji.portal.model.response.ApiResult;

/**
 * SucessResponse
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
public class SucessStringResponse extends ApiStringResponse {
    public SucessStringResponse(String data) {
        super(ApiResult.SUCESS,data);
    }
}
