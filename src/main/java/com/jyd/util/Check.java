package com.jyd.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 报文 异或和 校验
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 17:00
 */
@Slf4j
public class Check {

    public static String check(String content) {
        List<String> strings = stringToList(content.replaceAll(" ", ""));
        int a = 0;
        for (int i = 0; i < strings.size(); i++) {
            a = a ^ Integer.parseInt(strings.get(i), 16);
        }
        if(a<10){
            StringBuffer sb = new StringBuffer();
            sb.append("0");
            sb.append(a);
            return sb.toString();
        }
        String s = Integer.toHexString(a).toUpperCase();
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s;
    }

    /**
     * 字符转换成列表
     */
    public static List<String> stringToList(String data) {
        List<String> strings = new ArrayList<>();
        if (data.isEmpty()) {
            return strings;
        }
        if (1 == isOdd(data.length())) {
            return strings;
        }
        for (int i = 0; i < data.length() / 2; i++) {
            strings.add(data.substring(i * 2, i * 2 + 2));
        }
        return strings;
    }

    /**
     * 判断奇数或偶数，位运算，最后一位是1则为奇数，为0是偶数
     */
    public static int isOdd(int num) {
        return num & 1;
    }

    private static String change(String content) {
        String str = "";
        for (int i = 0; i < content.length(); i++) {
            if (i % 2 == 0) {
                str += " " + content.substring(i, i + 1);
            } else {
                str += content.substring(i, i + 1);
            }
        }
        return str.trim();
    }
}
