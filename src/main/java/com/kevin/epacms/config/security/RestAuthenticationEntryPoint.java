package com.kevin.epacms.config.security;

import cn.hutool.json.JSONUtil;
import com.kevin.epacms.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 自定义返回结果：未登录或登录过期
 *
 * @author kevin
 * @since 2022/11/17 22:03
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().println(JSONUtil.parse(Result.fail(HttpStatus.UNAUTHORIZED.value(), "请先登录")));
        response.getWriter().flush();
    }
}
