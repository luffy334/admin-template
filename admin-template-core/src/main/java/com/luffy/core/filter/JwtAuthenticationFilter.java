package com.luffy.core.filter;

import bean.ResultBean;
import com.luffy.core.utils.JwtTokenUtil;
import enums.ResultCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import utils.JsonUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * 登录拦截器
 *
 * @author luffy
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${jwt.tokenHead}")
    private String tokenPrefix;

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 接收、解析、验证用户凭证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getParameter("userName"), request.getParameter("password")));
    }

    /**
     * 验证成功
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        User user = (User) authResult.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        // 创建Token
        String token = JwtTokenUtil.createToken(user.getUsername(), authorities.toString());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        // 返回token
        response.setHeader("token", tokenPrefix + token);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JsonUtil.getJson(new ResultBean<>(ResultCode.SUCCESS)));
    }

    /**
     * 验证失败
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResultBean resultBean;
        // 账号过期
        if (failed instanceof DisabledException) {
            resultBean = new ResultBean(ResultCode.ACCOUNT_DISABLE);
        } else if (failed instanceof LockedException) {
            resultBean = new ResultBean(ResultCode.ACCOUNT_LOCK);
        } else if (failed instanceof InternalAuthenticationServiceException) {
            resultBean = new ResultBean(ResultCode.ACCOUNT_UNREGISTERED);
        } else if (failed instanceof BadCredentialsException) {
            resultBean = new ResultBean(ResultCode.ACCOUNT_PASSWORD_FAIL);
        } else {
            resultBean = new ResultBean(ResultCode.ACCOUNT_PASSWORD_FAIL);
        }
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JsonUtil.getJson(resultBean));
    }
}
