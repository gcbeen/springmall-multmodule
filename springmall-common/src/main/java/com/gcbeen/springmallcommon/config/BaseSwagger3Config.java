package com.gcbeen.springmallcommon.config;

import java.util.ArrayList;
import java.util.List;

import com.gcbeen.springmallcommon.domain.SwaggerProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;


public abstract class BaseSwagger3Config {
    @Bean
    public Docket createRestApi() {
        SwaggerProperties swaggerProperties = swaggerProperties();
        Docket docket = new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo(swaggerProperties))
                .select()
                // 为当前包下controller生成API文档
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getApiBasePackage()))
                // .apis(RequestHandlerSelectors.basePackage("com.gcbeen.springmall.controller"))
                // 为 有 @Api 注解的 Controller 生成 API 文档
                // .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                // 为 有 @ApiOperation 注解的方法生成 API 文档
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();

                // 添加登录认证
                if (swaggerProperties.isEnableSecurity()) {
                    docket.securitySchemes(securitySchemes()).securityContexts(securityContexts());
                }

                return docket;
                
    }

    private ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                // .version("0.1.0")
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        result.add(apiKey);
        return result;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath());

        return result;
    }

    private SecurityContext getContextByPath() {
        return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .operationSelector(oper -> selector(oper))
        // .forPaths(PathSelectors.regex(pathRegex))
        .build();
    }

    boolean selector(OperationContext operationContext) {
        String url = operationContext.requestMappingPattern();
        //这里可以写URL过滤规则
        boolean match = url.matches("/product/.*");
        match = url.matches("/esProduct/.*") || match;
        match = url.matches("/member/readHistory/.*") || match;
        match = url.matches("/order/generateOrder") || match;
        return match;
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }

    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     registry.addResourceHandler("/**").addResourceLocations(
    //             "classpath:/static/");
    //     registry.addResourceHandler("doc.html").addResourceLocations(
    //             "classpath:/META-INF/resources/");
    //     registry.addResourceHandler("/webjars/**").addResourceLocations(
    //             "classpath:/META-INF/resources/webjars/");
    // }

    public abstract SwaggerProperties swaggerProperties();

}
