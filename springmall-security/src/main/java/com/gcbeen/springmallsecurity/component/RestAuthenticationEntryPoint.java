package com.gcbeen.springmallsecurity.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.gcbeen.springmallcommon.util.Result;

import cn.hutool.json.JSONUtil;
// 认证失败处理器
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control","no-cache");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(JSONUtil.parse(Result.unauthorized(authException.getMessage())));
            response.getWriter().flush();
        
    }
    


}
