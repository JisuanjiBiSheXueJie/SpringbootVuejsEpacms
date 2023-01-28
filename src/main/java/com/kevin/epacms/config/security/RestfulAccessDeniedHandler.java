package com.kevin.epacms.config.security;

import cn.hutool.json.JSONUtil;
import com.kevin.epacms.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 当访问接口没有权限时，自定义的返回结果
 *
 * @author kevin
 * @since 2022/11/17 22:03
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(Result.fail(HttpStatus.FORBIDDEN.value(), "权限不足")));
        response.getWriter().flush();
    }
}
