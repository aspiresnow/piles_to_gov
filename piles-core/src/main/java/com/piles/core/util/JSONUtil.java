package com.piles.core.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class JSONUtil<T> {

    public JSONObject obj2JSONObj(T t, List<String> columns) throws IllegalAccessException {
        if (columns == null || columns.isEmpty())
            return obj2JSONObj(t);
        else {
            return obj2JSONObjByColumns(t, columns);
        }
    }

    public JSONObject obj2JSONObj(T t) throws IllegalAccessException {
        JSONObject jsonObject = new JSONObject();
        Class<?> clazz = t.getClass();
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String key = field.getName();
                    Class<?> cls = field.getType();
                    Object value = field.get(t);;
                    if (Number.class.isAssignableFrom(cls)) {
                        if (value == null)
                            value = 0;
                    } else if (Date.class.isAssignableFrom(cls)) {
                        if (value == null)
                            value = "";
                        else
                            value = ((Date)value).getTime();
                    } else if (BigDecimal.class.isAssignableFrom(cls)) {
                        if (value == null)
                            value = BigDecimal.ZERO;
                    } else {
                        if (value == null)
                            value = "";
                    }
                    jsonObject.put(key, value);
                }
            } catch (Exception e) {
                //这里甚么都不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会进入
            }
        }

        return jsonObject;
    }

    public JSONObject obj2JSONObjByColumns(T t, List<String> columns) throws IllegalAccessException {
        JSONObject jsonObject = new JSONObject();
        Class<?> clazz = t.getClass();

        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String key = field.getName();
                    if (!hasColumn(columns, key))
                        continue;
                    Class<?> cls = field.getType();
                    Object value = field.get(t);;
                    if (Number.class.isAssignableFrom(cls)) {
                        if (value == null)
                            value = 0;
                    } else if (Date.class.isAssignableFrom(cls)) {
                        if (value == null)
                            value = "";
                        else
                            value = ((Date)value).getTime();
                    } else if (BigDecimal.class.isAssignableFrom(cls)) {
                        if (value == null)
                            value = BigDecimal.ZERO;
                    } else {
                        if (value == null)
                            value = "";
                    }
                    jsonObject.put(key, value);
                }
            } catch (Exception e) {
                //这里甚么都不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会进入
            }
        }
        return jsonObject;
    }

    public JSONArray objs2JSONArray(List<T> list, List<String> columns) throws IllegalAccessException {
        JSONArray jsonArray = new JSONArray();
        for (T t : list) {
            JSONObject jsonObject = obj2JSONObj(t, columns);
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    public JSONArray obj2JSONArray(T obj, List<String> columns) throws IllegalAccessException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = obj2JSONObj(obj, columns);
        jsonArray.add(jsonObject);
        return jsonArray;
    }

    private boolean hasColumn(List<String> columns, String fieldName) {
        for (String column : columns) {
            if (column.equals(fieldName))
                return true;
        }

        return false;
    }

}
