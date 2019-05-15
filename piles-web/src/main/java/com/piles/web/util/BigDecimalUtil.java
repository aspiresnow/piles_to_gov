package com.piles.web.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * @Auther: zhanglizhi
 * @Date: 2019/2/25 21:56
 * @Description: 处理BigDecimal
 */
public interface BigDecimalUtil {
    /**
     * 累加BigDecimal
     * @param values 值集合
     * @return 累加和
     */
    static BigDecimal add(BigDecimal... values) {
        return add(4, BigDecimal.ROUND_HALF_UP, values);
    }
    /**
     * 累加BigDecimal
     * @param scale 精度
     * @param values 值集合
     * @return 累加和
     */
    static BigDecimal add(int scale, BigDecimal... values) {
        return add(scale, BigDecimal.ROUND_HALF_UP, values);
    }
    /**
     * 累加BigDecimal
     * @param scale 精度
     * @param roundingMode 取舍模式
     * @param values 值集合
     * @return 累加和
     */
    static BigDecimal add(int scale, int roundingMode, BigDecimal... values) {
        if (values == null || values.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = Stream.of(values).filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return result.setScale(scale, roundingMode);
    }
    /**
     * 累加BigDecimal
     * @param values 值集合
     * @return 累加和
     */
    static BigDecimal multiply(BigDecimal... values) {
        return multiply(4, BigDecimal.ROUND_HALF_UP, values);
    }
    /**
     * 累加BigDecimal
     * @param scale 精度
     * @param values 值集合
     * @return 累加和
     */
    static BigDecimal multiply(int scale, BigDecimal... values) {
        return multiply(scale, BigDecimal.ROUND_HALF_UP, values);
    }
    /**
     * 累加BigDecimal
     * @param scale 精度
     * @param roundingMode 取舍模式
     * @param values 值集合
     * @return 累积和
     */
    static BigDecimal multiply(int scale, int roundingMode, BigDecimal... values) {
        if (values == null || values.length == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = Stream.of(values).filter(Objects::nonNull)
                .reduce(BigDecimal::multiply).orElse(BigDecimal.ZERO);
        return result.setScale(scale, roundingMode);
    }

    /**
     * 最小为0
     * @param value
     * @return
     */
    static BigDecimal mindownZero(BigDecimal value) {
        value = convertNull2Zero(value);
        return value.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : value;
    }

    /**
     * 格式化bigDecimal 默认四舍五入
     *
     * @param value 值
     * @param scale 精度
     * @return
     */
    static String formatBigDecimal(BigDecimal value, int scale) {
        return formatBigDecimal(value, scale, null);
    }

    /**
     * 格式化bigDecimal
     *
     * @param value        值
     * @param scale        精度
     * @param roundingMode 取舍方式
     * @return
     */
    static String formatBigDecimal(BigDecimal value, int scale, RoundingMode roundingMode) {
        String format = IntStream.range(0, scale).boxed().map(s -> "0").collect(Collectors.joining("", "0.", ""));

        DecimalFormat df = new DecimalFormat(format);
        roundingMode = roundingMode == null ? RoundingMode.HALF_UP : roundingMode;
        df.setRoundingMode(roundingMode);
        value = convertNull2Zero(value);
        if (BigDecimal.ZERO.compareTo(value) == 0) {
            return "0";
        }
        return df.format(convertNull2Zero(value));
    }

    /**
     * 将空转换为0
     *
     * @param value
     * @return
     */
    static BigDecimal convertNull2Zero(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }
}
