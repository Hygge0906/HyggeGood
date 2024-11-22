package com.hyggegood.hg_common.exception;


import com.hyggegood.hg_common.utils.R;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler 类
 *
 * @Author: YangDengYu
 * @DateTime: 2024/11/21 23:03
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有未捕获的异常
     */
    @ExceptionHandler(Exception.class)
    public R handleGlobalException(Exception e, HttpServletRequest request) {
        log.error("未处理异常: {}，请求路径: {}，请求参数: {}",
                e.getMessage(), request.getRequestURI(), request.getQueryString());
        return R.error(5000, "系统异常，请稍后再试");
    }

    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(CustomException.class)
    public R handleRRException(CustomException ex, HttpServletRequest request) {
        log.error("业务异常: {}，请求路径: {}，请求参数: {}",
                ex.getMessage(), request.getRequestURI(), request.getQueryString());
        return R.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理数据库主键重复异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e, HttpServletRequest request) {
        log.error("主键重复异常: {}，请求路径: {}，请求参数: {}",
                e.getMessage(), request.getRequestURI(), request.getQueryString());
        return R.error(5001, "数据重复，请检查数据唯一性");
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("参数校验失败: {}，请求路径: {}，请求参数: {}", e.getMessage(), request.getRequestURI(), request.getQueryString());

        BindingResult result = e.getBindingResult();
        Map<String, String> validationErrors = new HashMap<>();

        // 遍历所有校验错误，将字段和错误消息放入 validationErrors 中
        result.getFieldErrors().forEach(error -> {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        });

        // 返回参数校验失败的错误码和消息
        return R.error(CustomException.ErrorCode.VALID_EXCEPTION.getCode(),
                CustomException.ErrorCode.VALID_EXCEPTION.getMessage());

    }
        /**
         * 处理 JWT Token 过期异常
         */
    @ExceptionHandler(ExpiredJwtException.class)
    public R handleExpiredJwtException(ExpiredJwtException e, HttpServletRequest request) {
        log.error("JWT Token 过期: {}，请求路径: {}，请求参数: {}", e.getMessage(), request.getRequestURI(), request.getQueryString());
        return R.error(401, "Token已过期，请重新登录");
    }

    /**
     * 处理资源未找到异常
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public R handleNoResourceFoundException(NoResourceFoundException e, HttpServletRequest request) {
        log.error("资源未找到: {}，请求路径: {}，请求参数: {}", e.getMessage(), request.getRequestURI(), request.getQueryString());
        return R.error(5006, "请求的资源不存在");
    }

    /**
     * 处理所有其他常见异常
     */
    @ExceptionHandler(RuntimeException.class)
    public R handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        log.error("运行时异常: {}，请求路径: {}，请求参数: {}", e.getMessage(), request.getRequestURI(), request.getQueryString());
        return R.error(5000, "运行时错误，请稍后重试");
    }

    /**
     * 处理空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public R handleNullPointerException(NullPointerException e, HttpServletRequest request) {
        log.error("空指针异常: {}，请求路径: {}，请求参数: {}", e.getMessage(), request.getRequestURI(), request.getQueryString());
        return R.error(5000, "空指针异常，请检查系统日志");
    }
}
