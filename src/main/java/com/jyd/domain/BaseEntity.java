package com.jyd.domain;

import lombok.Data;

/**
 * @author Zhenlin Jin
 * @date 2021/7/26 11:26
 */
@Data
public class BaseEntity {
    /**
     * 报文头
     */
    private String head;
    /**
     * 报文长度
     */
    private int length;
    /**
     * 功能码
     */
    private String code;
}
