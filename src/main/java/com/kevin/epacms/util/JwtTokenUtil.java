package com.kevin.epacms.util;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * @author kevin
 * @since 2022/10/5 16:08
 */
@Component
@Slf4j
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据负载生成令牌
     *
     * @param claims 负载
     * @return {@link String}
     */
    private String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpiration())
                .signWith(SignatureAlgorithm.HS384, secret)
                .compact();
    }

    /**
     * 生成token过期时间
     *
     * @return {@link Date}
     */
    private Date generateExpiration() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从令牌中获取负载
     *
     * @param token 令牌
     * @return {@link Claims}
     */
    private Claims getClaimsFromToken(String token){
        Claims claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            log.info("jwt格式校验失败：{}", token);
        }
        return claims;
    }

    /**
     * 从token中获得登录用户名
     *
     * @param token 令牌
     * @return {@link String}
     */
    public String getUserNameFromToken(String token){
        String username;
        try{
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     * @return boolean
     */
    public boolean validateToken(String token, UserDetails userDetails){
        String username = getUserNameFromToken(token);
        String usernameByDb = userDetails.getUsername();
        return StrUtil.equals(username, usernameByDb) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     *
     * @param token 令牌
     * @return boolean
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return new Date().after(expiredDate);
    }

    /**
     * 从token中获取过期时间
     *
     * @param token 令牌
     * @return {@link Date}
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成token
     *
     * @param userDetails 用户详细信息
     * @return {@link String}
     */
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new Hashtable<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否可以被刷新
     *
     * @param token 令牌
     * @return boolean
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     *
     * @param token 令牌
     * @return {@link String}
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
}
