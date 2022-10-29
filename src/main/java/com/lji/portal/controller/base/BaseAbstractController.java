package com.lji.portal.controller.base;

import com.lji.portal.model.response.ApiResponse;
import com.lji.portal.model.response.sucess.SucessResponse;
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
    protected <T>ResponseEntity<ApiResponse> callRestApi(T body){
        return response(new SucessResponse<T>(body));
    }

    private <T> ResponseEntity<T> response(T body) {
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHttpHeaders()).body(body);
    }

    protected abstract HttpHeaders getCommonHttpHeaders();
}
