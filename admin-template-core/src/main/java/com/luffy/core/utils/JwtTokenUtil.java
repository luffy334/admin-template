package com.luffy.core.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    /**
     * token过期时间
     */
    public static long expiration = 24 * 60 * 60 * 1000;

    /**
     * token密钥
     */
    public static String tokenKey;

    public static String createToken(String userName, String role) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("role", role);
        String token = Jwts
                .builder()
                .setSubject(userName)
                .setClaims(map)
                .claim("userName", userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, tokenKey).compact();
        return token;
    }

    public static Claims checkToken(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从Token中获取username
     *
     * @param token
     * @return java.lang.String
     * @author luffy
     * @date 2020/8/26 17:36
     **/
    public static String getUserName(String token) throws ExpiredJwtException {
        Claims claims = Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(token).getBody();
        return claims.get("userName").toString();
    }

    /**
     * 从Token中获取用户角色
     *
     * @param token
     * @return java.lang.String
     * @author luffy
     * @date 2020/8/26 17:36
     **/
    public static String getUserRole(String token) {
        Claims claims = Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(token).getBody();
        return claims.get("role").toString();
    }

    /**
     * 校验Token是否过期
     *
     * @param token
     * @return boolean
     * @author luffy
     * @date 2020/8/26 17:37
     **/
    public static boolean isExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }

    @Value("${jwt.secret}")
    public void setTokenKey(String tokenKey) {
        JwtTokenUtil.tokenKey = tokenKey;
    }

    @Value("${jwt.expiration}")
    public void setExpiration(long expiration) {
        JwtTokenUtil.expiration = expiration;
    }
}
