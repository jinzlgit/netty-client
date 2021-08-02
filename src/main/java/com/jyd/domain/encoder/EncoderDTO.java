package com.jyd.domain.encoder;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Zhenlin Jin
 * @date 2021/7/29 9:53
 */
@Data
@Builder
public class EncoderDTO implements Serializable {
    /**
     * 报文头
     */
    private String head;
    /**
     * 报文长度
     */
    private String length;
    /**
     * 功能码
     */
    private String code;
    /**
     * 参数
     */
    private String param;
    /**
     * 校验码
     */
    private String check;

    @Override
    public String toString() {
        return (head +
                length +
                code +
                param +
                check)
                .toUpperCase();
    }

    /**
     * 获取需要校验的数据
     */
    public String getCheckData() {
        return (head +
                length +
                code +
                param)
                .toUpperCase();
    }
}
