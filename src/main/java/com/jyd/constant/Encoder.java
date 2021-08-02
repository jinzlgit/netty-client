package com.jyd.constant;

/**
 * @author Zhenlin Jin
 * @date 2021/7/29 10:42
 */
public enum Encoder {
    /**
     * 报文头
     */
    HEAD("1A1A1A"),
    /**
     * 长度
     */
    LENGTH("0009"),
    /**
     * 功能码
     */
    CODE("code");

    private String value;

    Encoder(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
