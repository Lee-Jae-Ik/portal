package com.lji.portal.controller.base;

import com.lji.portal.model.dto.UserBasic;
import com.lji.portal.model.response.ApiStringResponse;
import com.lji.portal.model.response.ApiUserEntityResponse;
import com.lji.portal.model.response.sucess.SucessStringResponse;
import com.lji.portal.model.response.sucess.SucessUserEntityResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * BaseController
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
public abstract class BaseAbstractController {
    protected ResponseEntity<ApiStringResponse> callRestApiStringResult(String body){
        return response(new SucessStringResponse(body));
    }

    protected <T extends UserBasic> ResponseEntity<ApiUserEntityResponse> callRestApiUserDtoResult(T body) {
      return response(new SucessUserEntityResponse<T>(body));
    }

    private <T> ResponseEntity<T> response(T body) {
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHttpHeaders()).body(body);
    }

    protected abstract HttpHeaders getCommonHttpHeaders();
}
