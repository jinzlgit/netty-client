package com.jyd.constant;

import lombok.Data;

/**
 * @author Zhenlin Jin
 * @date 2021/7/30 11:28
 */
public enum Code {

    CODE01("01", "雷达自学习"),
    CODE02("02", "总线ID地址设置"),
    CODE03("03", "使能/失能RGB");

    private String code;
    private String name;

    Code(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Type value() {
        return new Type(this.code, this.name);
    }

    @Data
    public class Type {
        private String typeCode;
        private String typeName;

        public Type(String typeCode, String typeName) {
            this.typeCode = typeCode;
            this.typeName = typeName;
        }
    }
}
