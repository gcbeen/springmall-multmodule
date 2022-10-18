package com.gcbeen.springmalladmin.config;

import org.springframework.context.annotation.Configuration;

import com.gcbeen.springmallcommon.config.BaseSwagger3Config;
import com.gcbeen.springmallcommon.domain.SwaggerProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

// 文档地址： http://localhost:8080/swagger-ui/index.html#/
@Configuration
// @EnableOpenApi
@EnableKnife4j  // 访问路径ip:port/doc.html
public class Swagger3Config extends BaseSwagger3Config  {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
        .apiBasePackage("com.gcbeen.springmalladmin.controller")
        .title("springmall 后台系统")
        .description("springmall 后台相关接口 文档")
        .contactName("gcbeen")
        .contactEmail("johngcb@163.com")
        .version("1.0")
        .enableSecurity(true)
        .build();
    }
    
}
