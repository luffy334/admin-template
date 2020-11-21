package com.luffy.core.filter;

import com.luffy.core.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 用户鉴权
 * 
 * @author luffy
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final String TOKEN_HEADER = "Authorization";

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 在过滤之前和之后执行的事件
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(TOKEN_HEADER);
        // 放行没有token的请求
        if (token == null) {
            chain.doFilter(request, response);
        } else {
            //解析token并设置认证信息
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
            super.doFilterInternal(request, response, chain);
        }
    }

    /**
     * 从token中获取用户信息并新建一个token
     *
     * @author luffy
     * @date 2020/8/26 16:35
     * @param token
     * @return org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     **/
    private UsernamePasswordAuthenticationToken getAuthentication(String token) throws ExpiredJwtException {
        // 去掉前缀 获取Token字符串
        // 从Token中解密获取用户名
        String username = JwtTokenUtil.getUserName(token);
        // 从Token中解密获取用户角色
        String role = JwtTokenUtil.getUserRole(token);
        // 将[ROLE_XXX,ROLE_YYY]格式的角色字符串转换为数组
        String[] roles = role.replace("[", "").replace("]", "").split(", ");
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for (String s : roles){
            if (StringUtils.hasText(s)) {
                authorities.add(new SimpleGrantedAuthority(s));
            }
        }
        if (username != null){
            return new UsernamePasswordAuthenticationToken(username, null,authorities);
        }
        return null;
    }
}
