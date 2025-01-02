package com.wjx.utils;

import org.springframework.util.StringUtils;

public class LongUtil {
    public static Long convertStr2Long(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return Long.parseLong(str);
    }
}