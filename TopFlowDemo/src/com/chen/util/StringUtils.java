package com.chen.util;

import android.text.TextUtils;

public class StringUtils {
    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     * 
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input) || input.equals("null"))
            return true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 去掉utf bom头
     * 
     * @param data
     * @return
     */
    public static final String removeBOM(String data) {
        if (TextUtils.isEmpty(data)) {
            return data;
        }
        if (data.startsWith("\ufeff")) {
            return data.substring(1);
        } else {
            return data;
        }
    }
    
    /**
     * 处理转义字符
     * 
     * @param string
     * @return
     */
    public static String delEscapeCode(String string) {
        return string.replace("&amp;", "&").replace("&lt;", "<")
                .replace("&gt;", ">").replace("&apos;", "'")
                .replace("&#92;", "\\\\").replace("&#34;", "\\\"");
    }
}
