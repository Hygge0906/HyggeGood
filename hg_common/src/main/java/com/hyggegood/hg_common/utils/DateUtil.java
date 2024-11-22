package com.hyggegood.hg_common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.format.DateTimeParseException;
/**
 * DateUtil日期工具 类
 *
 * @Author: YangDengYu
 * @DateTime: 2024/11/21 21:08
 **/
public class DateUtil {

    // 常用的日期时间格式化器缓存
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    private static final DateTimeFormatter DATE_PATH_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd/");

    /**
     * 日期对象转字符串
     * @param date 日期对象
     * @param format 格式化字符串
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Date date, String format) {
        if (date == null) {
            return "";
        }
        // 使用 java.time 的 DateTimeFormatter（线程安全）
        LocalDateTime localDateTime = new java.sql.Timestamp(date.getTime()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    /**
     * 字符串转日期对象
     * @param str 日期字符串
     * @param format 格式化字符串
     * @return 转换后的日期对象
     * @throws DateTimeParseException 如果解析失败
     */
    public static Date formatString(String str, String format) throws DateTimeParseException {
        if (str == null || str.isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.parse(str, formatter);
        return java.sql.Timestamp.valueOf(localDateTime); // 转换为 Date
    }

    /**
     * 获取当前时间的字符串表示（格式：yyyyMMddHHmmssSSS）
     * @return 当前时间的字符串
     */
    public static String getCurrentDateStr() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DATE_FORMATTER); // 使用缓存的格式化器
    }

    /**
     * 获取当前日期路径（格式：yyyy/MM/dd/）
     * @return 当前日期路径
     */
    public static String getCurrentDatePath() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DATE_PATH_FORMATTER); // 使用缓存的格式化器
    }

    public static void main(String[] args) {
        try {
            System.out.println(getCurrentDateStr());
            System.out.println(getCurrentDatePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}