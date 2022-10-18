package com.gcbeen.springmallsecurity.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.gcbeen.springmallcommon.util.Result;

import cn.hutool.json.JSONUtil;
/**
 * 自定义返回结构：没有权限访问时
 * 权限不足处理器
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException e) throws IOException, ServletException {
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Cache-Control","no-cache");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().println(JSONUtil.parse(Result.forbidden(e.getMessage())));
                response.getWriter().flush();
        
    }
}
