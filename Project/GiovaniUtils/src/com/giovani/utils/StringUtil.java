package com.giovani.utils;

/**
 * 字符串工具
 *
 * @author Giovani
 * @create: 2019/1/15 17:23
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {

        if (string == null || string.length() <= 0) {
            return true;
        }

        return false;

    }

}
