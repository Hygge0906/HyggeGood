package com.hyggegood.hg_common.utils;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * SecretKeyGenerator 类
 *
 * @Author: YangDengYu
 * @DateTime: 2024/11/21 21:47
 **/


public class SecretKeyGenerator {

    public static void main(String[] args) {
        // 生成 256 位（32 字节）的随机密钥
        byte[] secretKeyBytes = generateSecretKey(32);

        // 将字节数组转换为 Base64 编码的字符串
        String base64SecretKey = Base64.getEncoder().encodeToString(secretKeyBytes);

        // 输出生成的密钥
        System.out.println("Generated Secret Key (Base64): " + base64SecretKey);
    }

    /**
     * 生成指定字节长度的随机密钥
     *
     * @param length 密钥长度（字节数）
     * @return 随机生成的密钥字节数组
     */
    public static byte[] generateSecretKey(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] secretKey = new byte[length];
        secureRandom.nextBytes(secretKey);
        return secretKey;
    }
}
