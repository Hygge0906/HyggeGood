package com.hyggegood.hg_common.exception;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 自定义异常类，用于业务逻辑中需要抛出的业务异常。
 * <p>此类继承自 {@link RuntimeException}，可用于捕捉各种自定义业务错误。</p>
 * <p>包括错误信息、错误代码、发生时间等信息。</p>
 *
 * @Author YangDengYu
 * @DateTime 2024/11/21 22:59
 */
@Getter
@Setter
@ToString
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /** 错误消息 */
    private String message;

    /** 错误代码 */
    private ErrorCode errorCode;

    /** 异常发生的时间 */
    private LocalDateTime timestamp;

    /** 附加详细信息（可选） */
    private String details;

    /**
     * 错误代码枚举类，提供标准化的错误码。
     * <p>每个错误码对应一个 HTTP 状态码和消息。</p>
     */
    public enum ErrorCode {
        SUCCESS(200, "成功"),
        BAD_REQUEST(400, "请求错误"),
        UNAUTHORIZED(401, "未授权"),
        FORBIDDEN(403, "禁止访问"),
        NOT_FOUND(404, "未找到"),
        INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
        VALID_EXCEPTION(422, "参数校验失败"); // 添加参数校验失败的错误码

        private final int code;
        private final String message;

        ErrorCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 构造方法，传入错误消息。
     *
     * @param message 错误消息
     */
    public CustomException(String message) {
        super(message);
        this.message = message;
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR; // 默认 500 错误
        this.timestamp = LocalDateTime.now();
    }

    /**
     * 构造方法，传入错误码。
     *
     * @param errorCode 错误码枚举
     */
    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.message = errorCode.getMessage();
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * 构造方法，传入错误码和详细信息。
     *
     * @param errorCode 错误码枚举
     * @param details 错误的详细信息
     */
    public CustomException(ErrorCode errorCode, String details) {
        super(errorCode.getMessage());
        this.message = errorCode.getMessage();
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }

    /**
     * 构造方法，传入错误消息和异常原因。
     *
     * @param message 错误消息
     * @param cause 异常原因
     */
    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR; // 默认 500 错误
        this.timestamp = LocalDateTime.now();
    }

    /**
     * 构造方法，传入错误码、消息和异常原因。
     *
     * @param errorCode 错误码枚举
     * @param message 错误消息
     * @param cause 异常原因
     */
    public CustomException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * 获取错误码
     *
     * @return 错误码
     */
    public int getCode() {
        return errorCode.getCode();
    }
}
