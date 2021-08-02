package com.jyd.schedule.jobs;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

/**
 * 任务调度Job
 *
 * @author Zhenlin Jin
 * @date 2021/7/30 16:05
 */
@Data
public class MyJob {
    /**
     * 任务ID
     */
    private long jobId;
    /**
     * 任务名称(对应 Task 任务类的名称)
     */
    @NotBlank(message = "任务名称[目标类名]不能为空")
    private String jobName;
    /**
     * 任务组名
     */
    private String jobGroup;
    /**
     * 任务方法名称
     */
    @NotBlank(message = "任务方法名称不能为空")
    private String methodName;
    /**
     * 任务方法参数
     */
    private String methodParams;
    /**
     * 任务执行 CRON 表达式
     */
    @NotBlank(message = "CRON表达式不能为空")
    private String cron;
    /**
     * CRON计划策略 <br/>0 默认 <br/>1 立即触发执行 <br/>2 触发一次执行 <br/>3 不触发立即执行
     */
    private String cronPolicy = "0";
    /**
     * 是否并发执行 <br/>0 允许 <br/>1 禁止
     */
    private String concurrent;
    /**
     * 任务状态 <br/>0 正常 <br/>1 暂停
     */
    private String status;
}
