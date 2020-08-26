package com.luffy.core.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {

    /**
     * token过期时间
     */
    public static long expiration;

    /**
     * token密钥
     */
    public static String tokenKey;

    public static String createToken(String userName,String role) {
        Map<String,Object> map = new HashMap<>(1);
        map.put("role", role);
        String token = Jwts
                .builder()
                .setSubject(userName)
                .setClaims(map)
                .claim("userName",userName)
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
     * @author luffy
     * @date 2020/8/26 17:36
     * @param token
     * @return java.lang.String
     **/
    public static String getUserName(String token){
        Claims claims = Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }

    /**
     * 从Token中获取用户角色
     *
     * @author luffy
     * @date 2020/8/26 17:36
     * @param token
     * @return java.lang.String
     **/
    public static String getUserRole(String token){
        Claims claims = Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(token).getBody();
        return claims.get("role").toString();
    }

    /**
     * 校验Token是否过期
     *
     * @author luffy
     * @date 2020/8/26 17:37
     * @param token
     * @return boolean
     **/
    public static boolean isExpiration(String token){
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
