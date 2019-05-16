package com.piles.web.config.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @Auther: zhanglizhi
 * @Date: 2019/5/16 15:47
 * @Description:
 */
public class LocalDateTimeCodec implements ObjectSerializer, ObjectDeserializer {
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        return Jdk8DateCodec.instance.deserialze(parser, clazz, fieldName, "yyyy-MM-dd HH:mm:ss", 0);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;

        if (object == null) {
            out.writeNull();
            return;
        }
        LocalDateTime data = (LocalDateTime) object;
        String strVal = data.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        out.writeString(strVal);
    }
}
