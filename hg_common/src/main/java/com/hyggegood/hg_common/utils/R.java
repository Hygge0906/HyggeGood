package com.hyggegood.hg_common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 页面响应entity
 * @Author: YangDengYu
 * @DateTime: 2024/11/21 20:55
 **/


public class R extends ConcurrentHashMap<String, Object> {

    // 常量：状态码
    public static final int SUCCESS_CODE = 1000;
    public static final int ERROR_CODE = 5000;

    // 默认构造方法
    public R() {
        put("code", SUCCESS_CODE);
        put("msg", "操作成功");
    }

    // 构造方法，支持自定义状态码和消息
    public R(int code, String msg) {
        put("code", code);
        put("msg", msg);
    }

    // 构造方法，支持自定义返回数据
    public R(int code, String msg, Object data) {
        put("code", code);
        put("msg", msg);
        put("data", data);
    }

    // 方便链式调用的put方法，检查null值
    @Override
    public R put(String key, Object value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        super.put(key, value);
        return this;
    }

    /**
     * 返回成功响应，默认无数据
     */
    public static R ok() {
        return new R(SUCCESS_CODE, "操作成功");
    }

    /**
     * 返回成功响应，自定义消息
     */
    public static R ok(String msg) {
        return new R(SUCCESS_CODE, msg);
    }

    /**
     * 返回成功响应，自定义数据
     */
    public static R ok(Object data) {
        return new R(SUCCESS_CODE, "操作成功", data);
    }

    /**
     * 返回失败响应，默认消息
     */
    public static R error() {
        return new R(ERROR_CODE, "未知异常，请联系管理员");
    }

    /**
     * 返回失败响应，自定义消息
     */
    public static R error(String msg) {
        return new R(ERROR_CODE, msg);
    }

    /**
     * 返回失败响应，自定义状态码和消息
     */
    public static R error(int code, String msg) {
        return new R(code, msg);
    }

    /**
     * 返回失败响应，自定义数据
     */
    public static R error(Object data) {
        return new R(ERROR_CODE, "操作失败", data);
    }

    /**
     * 获取data字段，支持类型转换
     *
     * @param typeReference 类型引用，用于支持复杂类型的转换
     * @param <T> 返回类型
     * @return 转换后的数据
     */
    public <T> T getData(TypeReference<T> typeReference) {
        Object data = get("data");
        if (data instanceof String) {
            // 如果data是String类型，进行反序列化
            return JSON.parseObject((String) data, typeReference);
        } else {
            return (T) data;
        }
    }

    /**
     * 获取data字段，返回Map类型
     *
     * @return 返回Map类型的data
     */
    public Map<String, Object> getData() {
        return (Map<String, Object>) get("data");
    }

    /**
     * 获取data字段，返回List类型
     *
     * @return 返回List类型的data
     */
    public <T> java.util.List<T> getDataList() {
        return (java.util.List<T>) get("data");
    }

    /**
     * 获取状态码
     *
     * @return 状态码
     */
    public int getCode() {
        return (int) get("code");
    }

    /**
     * 获取消息
     *
     * @return 消息
     */
    public String getMsg() {
        return (String) get("msg");
    }

    /**
     * 判断是否成功，状态码为成功时返回true
     *
     * @return 是否成功
     */
    public boolean isSuccess() {
        return getCode() == SUCCESS_CODE;
    }

    /**
     * 判断是否失败，状态码为失败时返回true
     *
     * @return 是否失败
     */
    public boolean isError() {
        return getCode() == ERROR_CODE;
    }

    // 示例：可以自定义一些额外方法
    /**
     * 设置data字段为Map类型
     *
     * @param data Map类型数据
     * @return 当前对象，支持链式调用
     */
    public R setData(Map<String, Object> data) {
        put("data", data);
        return this;
    }

    /**
     * 设置data字段为List类型
     *
     * @param data List类型数据
     * @return 当前对象，支持链式调用
     */
    public R setDataList(java.util.List<Object> data) {
        put("data", data);
        return this;
    }
}


