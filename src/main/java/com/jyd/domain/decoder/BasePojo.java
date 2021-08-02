package com.jyd.domain.decoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zhenlin Jin
 * @date 2021/7/27 16:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePojo {
    /**
     * 通道ID
     */
    private String channelId;
    /**
     * 功能码
     */
    private String code;
    /**
     * 完整报文
     */
    private String data;

}
