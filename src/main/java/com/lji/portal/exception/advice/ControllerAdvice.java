package com.lji.portal.exception.advice;

import com.lji.portal.exception.ApiRuntimeException;
import com.lji.portal.model.response.result.ApiStringResponse;
import com.lji.portal.model.response.ApiResult;
import com.lji.portal.model.response.fail.ApiErrorResposne;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ControllerAdvice
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-10-29
 */
@Slf4j
@org.springframework.web.bind.annotation.ControllerAdvice("com.lji.portal")
public class ControllerAdvice {

    @ExceptionHandler(ApiRuntimeException.class)
    public ResponseEntity<ApiStringResponse> runtimeExceptionHandler(ApiRuntimeException e) {
        log.error(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HHmmss")) +
                "Api Runtime Exception => {}", e.toString());

        HttpHeaders httpHeaders = new HttpHeaders();
        Charset utf8 = StandardCharsets.UTF_8;
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON,utf8));

        ApiStringResponse apiStringResponse = new ApiErrorResposne(e.getApiResult());
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(apiStringResponse);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> excetionHandler(Exception e, HttpServletResponse response) {
        log.error(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HHmmss")) +
                "Api Internal Server Error. You should check console-error.log => {}", e.getMessage());
        e.printStackTrace();
        return logicalErrorResponse(ApiResult.SERVER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR, response);
    }

    private ResponseEntity<?> logicalErrorResponse(ApiResult apiResult, Exception e, HttpStatus status, HttpServletResponse response) {
        String message = getMessage(e);
        response.setHeader("errorMessage", apiResult.getMessage());
        response.setHeader("errorCode", String.valueOf(apiResult.getCode()));

        ApiErrorResposne<String> errorResposne = new ApiErrorResposne<>(apiResult,message);
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).header("errorMessage", message).body(errorResposne);
    }

    private String getMessage(Exception e) {
        String message;
        if (ObjectUtils.isEmpty(e)) {
            message = "unknown error occurred";
        } else {
            message = e.toString();
        }

        return message;
    }
}
