package com.jyd.handler;

import com.jyd.domain.BaseEntity;
import com.jyd.domain.decoder.BasePojo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhenlin Jin
 * @date 2021/7/28 9:18
 */
public abstract class AbstractPojoParse<T> implements PojoParse {
    /**
     * 十六进制标识符
     */
    protected static final int RADIX_HEX = 16;
    /**
     * 十六进制转二进制格式化
     */
    protected static final DecimalFormat HEX_TO_BINARY_FORMAT = new DecimalFormat("00000000");
    /**
     * 浮点数取两位小数格式化
     */
    protected static final DecimalFormat FLOAT_FORMAT = new DecimalFormat("0.00");

    protected abstract T parse(BasePojo basePojo) throws Exception;

    @Override
    public String parseObject2String(BaseEntity baseEntity) {

        return null;
    }

    protected boolean checkData(String data) {
        // 截取最后两个字节前的报文做运算，再与本身判断是否相同
        String content = data.substring(0, data.length() - 2);
        List<String> strings = stringToList(content.replaceAll(" ", ""));
        int a = 0;
        for (int i = 0; i < strings.size(); i++) {
            a = a ^ Integer.parseInt(strings.get(i), 16);
        }
        if(a<10){
            StringBuffer sb = new StringBuffer();
            sb.append("0");
            sb.append(a);
            return sb.toString().equalsIgnoreCase(content);
        }
        String s = Integer.toHexString(a).toUpperCase();
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s.equalsIgnoreCase(content);
    }

    /**
     * 字符转换成列表
     */
    private static List<String> stringToList(String data) {
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
    private static int isOdd(int num) {
        return num & 1;
    }
}
