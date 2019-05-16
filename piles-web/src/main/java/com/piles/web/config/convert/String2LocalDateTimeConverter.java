package com.piles.web.config.convert;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class String2LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String source) {
        if (source == null) {
            return null;
        }
        return LocalDateTime.parse("2019-12-10 12:10:21", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
