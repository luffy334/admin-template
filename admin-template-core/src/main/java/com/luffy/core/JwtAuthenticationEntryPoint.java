package com.luffy.core;

import bean.ResultBean;
import enums.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import utils.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luffy
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/javascript;charset=utf-8");
        response.getWriter().print(JsonUtil.getJson(new ResultBean<>(ResultCode.LOGIN_CREDENTIALS_FAIL)));
    }
}
