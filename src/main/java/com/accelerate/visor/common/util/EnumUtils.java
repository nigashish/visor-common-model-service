package com.accelerate.visor.common.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EnumUtils {

    public static <T> Enum lookupEnum(T s, Enum[] values, String methodName) {
        try {
            Method method = values[0].getClass().getMethod(methodName);
            for (Enum val : values) {
                if (method.invoke(val).equals(s)) {
                    return val;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new IllegalArgumentException(String.format("Invalid enum value: %s", s));
    }

    public static String getDisplayName(Enum enumValue) {
        try {
            Method method = enumValue.getClass().getMethod("getDisplayName");

            return String.valueOf(method.invoke(enumValue));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map getEnumMap(Enum[] values) {
        try {
            Map<String, Enum> lookup = new HashMap<>();
            Method method = values[0].getClass().getMethod("getDisplayName");
            for (Enum e : values
            ) {
                lookup.put((String) method.invoke(e), e);
            }
            return lookup;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
