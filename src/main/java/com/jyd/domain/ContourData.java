package com.jyd.domain;

import lombok.Data;

import java.util.List;

/**
 * 雷达自学习轮廓数据 或 雷达实时扫描轮廓数据
 *
 * @author Zhenlin Jin
 * @date 2021/7/26 11:25
 */
@Data
public class ContourData extends BaseEntity implements ReadResponse {
    /**
     * 编号
     */
    private String number;
    /**
     * 我也不知道
     */
    private int CAN_ID;
    /**
     * 极坐标点400个
     */
    private List array;
    /**
     * 校验码
     */
    private String check;
}
