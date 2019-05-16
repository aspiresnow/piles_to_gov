package com.piles.core.util;

import com.piles.core.exception.PileException;
import org.apache.commons.lang3.StringUtils;

public class AssertUtils {
    //鉴定str是否为blank，是的时候抛出warn级别的日志，错误编码为1，返回消息和日志内容为msg
    public static void blankForWarn(String str, String msg) {
        isTrue(StringUtils.isBlank(str), "1", msg, msg, PileException.Level.WARN);
    }
    //鉴定str是否为blank，是的时候抛出warn级别的日志，错误编码为1，返回消息为msg,日志内容为mesasage,args用于参数替换
    public static void blankForWarn(String str, String msg, String mesasage, Object... args) {
        isTrue(StringUtils.isBlank(str), "1", msg, mesasage, PileException.Level.WARN, args);
    }

    public static void nonBlankForWarn(String str, String msg) {
        isTrue(StringUtils.isNotBlank(str), "1", msg, msg, PileException.Level.WARN);
    }

    public static void nonBlankForWarn(String str, String msg, String mesasage, Object... args) {
        isTrue(StringUtils.isNotBlank(str), "1", msg, mesasage, PileException.Level.WARN, args);
    }

    public static void nullForWarn(Object obj, String msg) {
        isTrue(obj == null, "1", msg, msg, PileException.Level.WARN);
    }

    public static void nullForWarn(Object obj, String msg, String message, Object... args) {
        isTrue(obj == null, "1", msg, message, PileException.Level.WARN, args);
    }

    public static void isTrueForWarn(boolean expression, String msg) {
        isTrue(expression, "1", msg, msg, PileException.Level.WARN);
    }

    public static void isTrueForWarn(boolean expression, String msg, String message, Object... args) {
        isTrue(expression, "1", msg, message, PileException.Level.WARN, args);

    }

    //鉴定str是否为blank，是的时候抛出error级别的日志，错误编码为1，返回消息和日志内容为msg
    public static void blankForError(String str, String msg) {
        isTrue(StringUtils.isBlank(str), "1", msg, msg, PileException.Level.ERROR);
    }

    //鉴定str是否为blank，是的时候抛出error级别的日志，错误编码为1，返回消息为msg,日志内容为mesasage,args用于参数替换
    public static void blankForError(String str, String msg, String message, Object... args) {
        isTrue(StringUtils.isBlank(str), "1", msg, message, PileException.Level.ERROR, args);
    }


    public static void nonBlankForError(String str, String msg) {
        isTrue(StringUtils.isNotBlank(str), "1", msg, msg, PileException.Level.ERROR);
    }

    public static void nonBlankForError(String str, String msg, String message, Object... args) {
        isTrue(StringUtils.isNotBlank(str), "1", msg, message, PileException.Level.ERROR, args);
    }

    public static void nullForError(Object obj, String msg) {
        isTrue(obj == null, "1", msg, msg, PileException.Level.ERROR);
    }

    public static void nullForError(Object obj, String msg, String message, Object... args) {
        isTrue(obj == null, "1", msg, message, PileException.Level.ERROR, args);
    }

    public static void isTrueForError(boolean expression, String msg) {
        isTrue(expression, "1", msg, msg, PileException.Level.ERROR);

    }

    public static void isTrueForError(boolean expression, String msg, String message, Object... args) {
        isTrue(expression, "1", msg, message, PileException.Level.ERROR, args);
    }

    public static void isTrue(boolean expression, String code, String msg, String message, PileException.Level level, Object... args) {
        if (expression) {
            throw new PileException(code, msg, message, args, level);
        }
    }
    //抛出异常
    public static void throwsException(String code, String msg, String message, Object... args) {
        throw new PileException(code, msg, message, args, PileException.Level.ERROR);
    }
}
