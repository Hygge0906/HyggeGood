package com.hyggegood.hg_common.utils;

import io.jsonwebtoken.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * Jwtutils 类
 * @Author: YangDengYu
 * @DateTime: 2024/11/21 21:11
 **/
public class Jwtutils {

    // 默认有效期为7天
    public static final Long JWT_TTL = 7 * 24 * 60 * 60 * 1000L; // 默认有效期为7天

    private static final String JWT_KEY = "Ynw+bYgS0hT774eNd20bycseFnbN2Sjs7FTrSaAhrdc=";

    private static final Logger logger = LoggerFactory.getLogger(Jwtutils.class); // 日志记录器

    /**
     * 获取唯一的UUID
     * @return 唯一的UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成JWT（不设置过期时间）
     * @param subject JWT中要存放的数据（JSON格式）
     * @return JWT token
     */
    public static String createJWT(String subject) {
        return createJWT(subject, JWT_TTL); // 默认过期时间
    }

    /**
     * 生成JWT
     * @param subject JWT中要存放的数据（JSON格式）
     * @param ttlMillis token超时时间
     * @return JWT token
     */
    public static String createJWT(String subject, Long ttlMillis) {
        System.out.println("JWT_SECRET_KEY: " + JWT_KEY);
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID()); // 创建JWT
        return builder.compact();
    }

    /**
     * 生成带有自定义ID的JWT
     * @param id 唯一标识
     * @param subject JWT中要存放的数据（JSON格式）
     * @param ttlMillis token超时时间
     * @return JWT token
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id); // 创建JWT
        return builder.compact();
    }

    /**
     * 获取JwtBuilder
     * @param subject JWT中要存放的数据（JSON格式）
     * @param ttlMillis token过期时间
     * @param uuid 唯一标识
     * @return JwtBuilder
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; // 使用HS256签名算法
        SecretKey secretKey = generalKey(); // 获取密钥
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JWT_TTL; // 默认有效期为7天
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis); // 设置过期时间

        return Jwts.builder()
                .setId(uuid) // 唯一标识
                .setSubject(subject) // 设置主题
                .setIssuer("hg_ydy") // 设置签发者
                .setIssuedAt(now) // 设置签发时间
                .signWith(signatureAlgorithm, secretKey) // 使用签名算法和密钥
                .setExpiration(expDate); // 设置过期时间
    }

    /**
     * 生成加密后的秘钥
     * @return SecretKey 密钥
     */
    private static SecretKey generalKey() {
        if (JWT_KEY == null || JWT_KEY.isEmpty()) {
            throw new IllegalArgumentException("JWT 秘钥必须提供。");
        }
        byte[] encodedKey = Base64.getDecoder().decode(JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析JWT
     * @param jwt JWT token
     * @return Claims
     * @throws JwtParseException
     */
    public static Claims parseJWT(String jwt) throws JwtParseException {
        try {
            SecretKey secretKey = generalKey(); // 获取密钥
            return Jwts.parser()
                    .setSigningKey(secretKey) // 设置签名密钥
                    .parseClaimsJws(jwt) // 解析JWT
                    .getBody();
        } catch (ExpiredJwtException e) {
            logger.error("JWT token 已过期: {}", jwt); // 修改为中文
            throw new JwtParseException("JWT token 已过期。", e);
        } catch (JwtException e) {
            logger.error("无效的 JWT token: {}", jwt);
            throw new JwtParseException("无效的 JWT token。", e);
        } catch (Exception e) {
            logger.error("JWT解析失败: {}", e.getMessage());
            throw new JwtParseException("JWT解析失败。", e);
        }
    }

    // 自定义的异常类，用来包装Jwt解析时的异常
    public static class JwtParseException extends Exception {
        public JwtParseException(String message) {
            super(message);
        }

        public JwtParseException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static void main(String[] args) throws Exception {
        String token = createJWT("subjectData"); // 创建JWT
        logger.info("生成的JWT: {}", token);

        Claims claims = parseJWT(token); // 解析JWT
        logger.info("解析后的Claims: {}", claims);
    }
}





