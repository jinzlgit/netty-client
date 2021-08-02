package com.jyd.schedule.jobs;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 任务调度执行日志
 *
 * @author Zhenlin Jin
 * @date 2021/7/30 16:46
 */
@Data
@Builder
public class MyJobLog {
    /**
     * 日志ID
     */
    private long jobLogId;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务组名
     */
    private String jobGroup;
    /**
     * 目标执行方法名称
     */
    private String methodName;
    /**
     * 方法参数
     */
    private String methodParams;
    /**
     * 日志信息
     */
    private String jobMessages;
    /**
     * 执行状态 0 成功 1 失败
     */
    private String status;
    /**
     * 执行的异常信息
     */
    private String exceptionInfo;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
}
