package com.lji.portal.controller.base;

import com.lji.portal.model.dto.UserBasic;
import com.lji.portal.model.response.result.ApiResponse;
import com.lji.portal.model.response.sucess.SucessResponse;
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
public abstract class BaseAbstractController<T> {


    protected ResponseEntity<ApiResponse> callRestApi(T body) {
        return response(new SucessResponse(body));
    }

    private <T> ResponseEntity<T> response(T body) {
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHttpHeaders()).body(body);
    }

    protected abstract HttpHeaders getCommonHttpHeaders();
}
