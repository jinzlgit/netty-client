package com.jyd.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 设备信息
 *
 * @author Zhenlin Jin
 * @date 2021/7/26 11:51
 */
@Data
public class DeviceInfo extends BaseEntity implements ReadResponse {

    private String number;

    private int CAN_ID;

    private int RGB;
    /**
     * 雷达敏感度
     */
    private int sensitivity;
    /**
     * 雷达缩进防区
     */
    private int indentation;
    /**
     * 雷达最远探测距离
     */
    private int farthest;
    /**
     * 雷达开角范围：起始角
     */
    private int angleStart;
    /**
     * 雷达开角范围：停止角
     */
    private int angleEnd;
    /**
     * 姿态故障偏移阈值
     */
    private int faultOffsetThreshold;
    /**
     * 雷达遮挡故障距离阈值
     */
    private int faultDistanceThreshold;
    /**
     * 雷达遮挡故障数量
     */
    private int occludeFaultCount;
    /**
     * 雷达蒙尘故障数量
     */
    private int dustedFaultCount;
    /**
     * pitch学习值
     */
    private double picthStudy;
    /**
     * roll学习值
     */
    private double rollStudy;
    /**
     * 有效报警次数
     */
    private int validAlarmCount;
    /**
     * 心跳周期
     */
    private int heart;
    /**
     * 机芯状态: 0 打开 1 关闭
     */
    private int movementStatus;
    /**
     * 最大等待时间
     */
    private int bootloader;
    /**
     * 使/失能继电器: 0 使能 1 失能
     */
    private int relay;
    /**
     * 设置CAN发送延迟
     */
    private int sendCANDelay;
    /**
     * 蜂鸣器
     */
    private int buzzer;
    /**
     * 雷达运行状态
     */
    private String runStatus;
    /**
     * 雷达故障状态
     */
    private String faultStatus;

    private double VDD;

    private double pitch;

    private double roll;
    /**
     * 温度值
     */
    private int temperature;

    private String check;
}
