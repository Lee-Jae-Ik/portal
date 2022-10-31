package com.lji.portal.model.response.sucess;

import com.lji.portal.model.response.result.ApiJsonResponse;
import com.lji.portal.model.response.ApiResult;
import org.json.simple.JSONObject;

/**
 * SucessResponse
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
public class SucessJsonResponse extends ApiJsonResponse {
    public SucessJsonResponse(JSONObject data) {
        super(ApiResult.SUCESS,data);
    }
}
