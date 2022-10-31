package com.lji.portal.model.response.fail;

import com.lji.portal.model.response.result.ApiStringResponse;
import com.lji.portal.model.response.ApiResult;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * ApiErrorResposne
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
public class ApiErrorResposne<T> extends ApiStringResponse {

    @Setter
    private String changeMessage;

    public ApiErrorResposne(ApiResult apiResult) {
        super(apiResult, null);
    }

    public ApiErrorResposne(ApiResult apiResult, String message) {
        super(apiResult,message);
    }

    @Override
    public String getMessage() {
        if (!StringUtils.hasText(this.changeMessage)) return super.getMessage();
        else return this.changeMessage;
    }
}
