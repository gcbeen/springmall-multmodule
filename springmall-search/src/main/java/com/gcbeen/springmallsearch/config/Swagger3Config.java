package com.gcbeen.springmallsearch.config;

import org.springframework.context.annotation.Configuration;

import com.gcbeen.springmallcommon.config.BaseSwagger3Config;
import com.gcbeen.springmallcommon.domain.SwaggerProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

// 文档地址： http://localhost:8081/swagger-ui/index.html#/
@Configuration
// @EnableOpenApi
@EnableKnife4j  // 访问路径ip:port/doc.html
public class Swagger3Config extends BaseSwagger3Config  {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
        .apiBasePackage("com.gcbeen.springmallsearch.controller")
        .title("springmall 搜索系统")
        .description("springmall 搜索相关接口 文档")
        .contactName("gcbeen")
        .contactEmail("johngcb@163.com")
        .version("1.0")
        .enableSecurity(false)
        .build();
    }
    
}
