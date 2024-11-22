package com.hyggegood.hg_common.utils;

/**
 * SystemConstant 类
 *
 * @Author: YangDengYu
 * @DateTime: 2024/11/21 21:08
 **/
public class SystemConstant {
    /**
     * token
     */
    public static final int JWT_ERRCODE_NULL = 4000;			//Token不存在
    public static final int JWT_ERRCODE_EXPIRE = 4001;			//Token过期
    public static final int JWT_ERRCODE_FAIL = 4002;			//验证不通过

    /**
     * JWT
     */
    public static final String JWT_SECERT = "Ynw+bYgS0hT774eNd20bycseFnbN2Sjs7FTrSaAhrdc=";			//密匙
    public static final long JWT_TTL = 24*60 * 60 * 1000;
}
