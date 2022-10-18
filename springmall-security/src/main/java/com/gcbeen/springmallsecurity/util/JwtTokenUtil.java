package com.gcbeen.springmallsecurity.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/**
 *  生成 JwtToken 的工具类
 */
// @Component
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 根据用户信息生成token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_USERNAME, "");
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
    /**
     * 判断token是否可以被刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 当原来的token没过期时是可以刷新的
     * @param oldToken 带tokenHead的token
     * @return
     */
    public String refreshHeadToken(String oldToken) {
        if (StrUtil.isEmpty(oldToken)) {
            return null;
        }
        String token = oldToken.substring(tokenHead.length());
        if (StrUtil.isEmpty(token)) {
            return null;
        }
        Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            // token 校验不通过
            return null;
        }
        if (isTokenExpired(token)) {
            // 如果 token 已经过期, 不支持刷新
            return null;
        }
        if (tokenRefreshJustBefore(token, 30*60)) {
            // 如果 token 在 30 分钟之内 刚刷新过，返回原来的 token
            return token;
        } else {
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateToken(claims);
        }
    }

    /**
     * 判断 token 在指定时间内是否刚刚刷新过
     * @param token 原token
     * @param time 指定时间（秒）
     * @return
     */
    private boolean tokenRefreshJustBefore(String token, int time) {
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Date refreshDate = new Date();
        
        if (refreshDate.after(created) && refreshDate.before(DateUtil.offsetSecond(created, time))) {
            // 刷新时间 在 从 创建时间  的指定时间 内
            return true;
        }
        return false;
    }
    /**
     * 刷新 token
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 根据负载生成 JWT 的 token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate()).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 从token中获取JWT中的负载
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            LOGGER.info("JWT 格式验证失败：{}", token);
        }
        return claims;
    }

     /**
      * 生成token的过期时间
      * @return Date
      */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取登录用户名
     * @param token
     * @return
     */
    public String  getUserNameFromToken(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch(Exception e) {
            LOGGER.info("error message: {}", e.getMessage());
        }
        return username;
    }

    /**
     * 验证token是否还有效
     * @param token 客户端传入的 token
     * @param userDetails 从数据库中查出的用户信息
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        // return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        return username.equals("") && !isTokenExpired(token);
    }
    /**
     * 判断token是否已经失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }
}