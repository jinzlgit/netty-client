package com.jyd.domain.web;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Zhenlin Jin
 * @date 2021/7/28 23:11
 */
@Data
public class RequestDTO {

    @NotBlank(message = "客户端名称不能为空")
    private String name;

    @NotBlank(message = "通道ID不能为空")
    private String channelId;

    @NotBlank(message = "功能码不能为空")
    private String code;

    @NotBlank(message = "设置参数不能为空")
    private String param;
}
