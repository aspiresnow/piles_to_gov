package com.piles.web.exception;

import lombok.Data;

@Data
public class PileException extends RuntimeException {

    //默认code为1，标示失败
    private String code = "1";
    private String msg;
    //默认为ERROR级别的异常
    private Level level = Level.ERROR;
    private Object[] args;

    public PileException(String msg) {
        super(msg);//错误编码默认使用1
        this.msg = msg;
    }

    public PileException(String code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
    public PileException(String code, String msg, String message) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public PileException(String code, String msg, String message, Object[] args) {
        super(message);
        this.code = code;
        this.msg = msg;
        this.args = args;
    }

    public PileException(String code, String msg, String message, Object[] args, Level level) {
        super(message);
        this.code = code;
        this.msg = msg;
        this.args = args;
        this.level = level;
    }

    public PileException(String code, String msg, String message, Object[] args, Level level, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
        this.args = args;
        this.level = level;
    }

    public PileException(Throwable cause) {
        super(cause);
    }

    public Throwable getRootCause() {
        Throwable rootCause = null;
        Throwable cause = this.getCause();
        while (cause != null && cause != rootCause) {
            rootCause = cause;
            cause = cause.getCause();
        }
        return rootCause;
    }

    public String getDetailMessage() {
        StringBuilder sb = new StringBuilder(64);
        String message = this.getMessage();
        if (message != null) {
            sb.append(message).append("; ");
        }
        sb.append("nested exception is ").append(this.getCause());
        return sb.toString();
    }

    /**
     * 自定义的异常的级别枚举
     */
    public enum Level {
        ERROR, WARN, INFO, DEBUG
    }
}
