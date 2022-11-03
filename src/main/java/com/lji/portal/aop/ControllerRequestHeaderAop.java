package com.lji.portal.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * ControllerRequestHeaderAop
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-11-02
 */
@Component
@Aspect
@Slf4j
public class ControllerRequestHeaderAop {

    @Before("execution(* com.lji.*.controller.*.*(..))")
    public void printRequestHeaderAtLog(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final long THREAD_ID = Thread.currentThread().getId();
        StringBuilder stringBuilder = new StringBuilder();
        /* API 호출 시 로그 출력 내용 작성 */
        stringBuilder.append("\n[").append(THREAD_ID).append("]/*======================================================================*/");
        stringBuilder.append("\n[").append(THREAD_ID).append("] 1. Target Name : ").append(joinPoint.getTarget().getClass().getName()) // api method(controller)
                .append(joinPoint.getSignature().getName());
        stringBuilder.append("\n[").append(THREAD_ID).append("] 2. HTTP Method : ").append(request.getMethod()); // http method (get or post)
        stringBuilder.append("\n[").append(THREAD_ID).append("] 3. Api Url  : ").append(request.getRequestURL()); // api url

        /* Request Header 추출 */
        Enumeration<String> headerNames = request.getHeaderNames();
        int count = 1;
        do {
            String selectHeaderName = headerNames.nextElement();
            String getHeaderValue = request.getHeader(selectHeaderName);
            stringBuilder.append("\n[").append(THREAD_ID).append("] 4" +"-" + count + ". Request Header[" + selectHeaderName + "] : ").append(getHeaderValue);
            count++;
        } while (headerNames.hasMoreElements());
        /* Request Header 추출 종료 */

        /**
         * API call 한 client IP 로깅
         */
        stringBuilder.append("\n[").append(THREAD_ID).append("] 5. Check Client Ip  : ").append(getClientIpAddr(request));

        stringBuilder.append("\n[").append(THREAD_ID).append("]/*======================================================================*/");

        log.info(stringBuilder.toString());
    }

    /**
     * Client Ip 체킹 Method
     * @param request
     * @return
     */
    private String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

}

