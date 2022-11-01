package com.lji.portal.security.filter;

import com.lji.portal.exception.ApiRuntimeException;
import com.lji.portal.model.response.ApiResult;
import com.lji.portal.security.SecurityUrlConfig;
import com.lji.portal.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.auth.message.AuthException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CustomerAuthorizationFilter
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/10/31
 */
@Component
@RequiredArgsConstructor
public class CustomerAuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private final SecurityUrlConfig securityUrlConfig;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getMethod().equals("GET")) {
            for (String whiteList : securityUrlConfig.getGetUrlList()) {
                if (request.getRequestURI().startsWith(whiteList.replace("/**", ""))) {
                    filterChain.doFilter(request,response);
                    return;
                }
            }
        }

        if (request.getMethod().equals("POST")) {
            for (String whiteList : securityUrlConfig.getPostUrlList()) {
                if (request.getRequestURI().startsWith(whiteList.replace("/**", ""))) {
                    filterChain.doFilter(request,response);
                    return;
                }
            }
        }
        String accessToken = jwtProvider.resolveToken(request);

        if (!jwtProvider.validateAccessToken(accessToken)) {
            throw new ApiRuntimeException(ApiResult.SERVER_ERROR);
        }
    }
}
