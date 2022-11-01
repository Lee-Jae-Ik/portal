package com.lji.portal.security;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * SecurityUrl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/10/31
 */
@Getter
@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityUrlConfig {
    private List<String> getUrlList = new ArrayList<>();
    private List<String> postUrlList = new ArrayList<>();
}
