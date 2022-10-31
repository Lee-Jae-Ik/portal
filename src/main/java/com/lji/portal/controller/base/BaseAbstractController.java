package com.lji.portal.controller.base;

import com.lji.portal.model.dto.UserBasic;
import com.lji.portal.model.response.result.ApiJsonResponse;
import com.lji.portal.model.response.result.ApiListObjectResponse;
import com.lji.portal.model.response.result.ApiStringResponse;
import com.lji.portal.model.response.result.ApiUserEntityResponse;
import com.lji.portal.model.response.sucess.SucessJsonResponse;
import com.lji.portal.model.response.sucess.SucessListObjectResponse;
import com.lji.portal.model.response.sucess.SucessStringResponse;
import com.lji.portal.model.response.sucess.SucessUserEntityResponse;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * BaseController
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
public abstract class BaseAbstractController {

    /**
     * String result response
     * @param body
     * @return
     */
    protected ResponseEntity<ApiStringResponse> callRestApiStringResult(String body){
        return response(new SucessStringResponse(body));
    }

    /**
     * UserBasic extends data response
     * @param body
     * @param <T>
     * @return
     */
    protected <T extends UserBasic> ResponseEntity<ApiUserEntityResponse> callRestApiUserDtoResult(T body) {
      return response(new SucessUserEntityResponse<T>(body));
    }

    /**
     * Json object result response
     * @param body
     * @return
     */
    protected ResponseEntity<ApiJsonResponse> callRestApiJsonResult(JSONObject body) {
        return response(new SucessJsonResponse(body));
    }

    /**
     * List object result response
     * @param body
     * @param <T>
     * @return
     */
    protected <T> ResponseEntity<ApiListObjectResponse> callRestApiListObjectResult(List<T> body) {
        return response(new SucessListObjectResponse(body));
    }

    private <T> ResponseEntity<T> response(T body) {
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHttpHeaders()).body(body);
    }

    protected abstract HttpHeaders getCommonHttpHeaders();
}
