package com.jyd.domain;

import lombok.Data;

/**
 * 设置参数后的响应数据
 *
 * @author Zhenlin Jin
 * @date 2021/7/26 13:33
 */
@Data
public class SetterResponse extends BaseEntity implements WriteResponse{
    private String number;

    private int CAN_ID;

    private String response;

    private String check;
}
