package com.gcbeen.springmalladmin.config;

import com.gcbeen.springmalladmin.component.AdminUserDetailService;
import com.gcbeen.springmallsecurity.config.SecurityConfig;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * SpringSecurity 的配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SpringmallSecurityConfig extends SecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringmallSecurityConfig.class);

    @Autowired
    private AdminUserDetailService adminUserDetailService;

    @Bean
    public UserDetailsService userDetailsService() {
        LOGGER.info("UserDetailsService");
        // 获取登录用户信息
        return username -> adminUserDetailService.loadUserByUsername(username);
    }
    
}
