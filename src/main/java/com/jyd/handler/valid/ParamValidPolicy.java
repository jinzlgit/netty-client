package com.jyd.handler.valid;

import com.jyd.exception.CommonException;

/**
 * 功能码参数校验策略
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:10
 */
public interface ParamValidPolicy {
    /**
     * 格式化用1个字节表示十六进制
     */
    String INT_TO_HEX_1 = "%02x";
    /**
     * 格式化用2个字节表示十六进制
     */
    String INT_TO_HEX_2 = "%04x";
    /**
     * 格式化用4个字节表示十六进制
     */
    String INT_TO_HEX_4 = "%08x";

    String valid(String param) throws CommonException;
}
