package com.jyd.service;

import com.jyd.schedule.jobs.MyJob;
import org.quartz.SchedulerException;

/**
 * 定时任务业务
 *
 * @author Zhenlin Jin
 * @date 2021/7/30 23:10
 */
public interface JobService {

    /**
     * 新增任务
     */
    void createNewJob(MyJob job) throws SchedulerException;

    /**
     * 更新任务，比如主要更新CRON表达式
     */
    void updateJob(MyJob job) throws SchedulerException;

    void pauseJob(MyJob job) throws SchedulerException;

    void resumeJob(MyJob job) throws SchedulerException;

    /**
     * 删除任务后，所对应的 trigger 也将删除
     */
    void deleteJob(MyJob job) throws SchedulerException;

    /**
     * 暂停或恢复任务
     */
    void changeStatus(MyJob job) throws SchedulerException;

    /**
     * 立即运行任务
     */
    void runNow(MyJob job) throws SchedulerException;

    /**
     * 校验CRON表达式是否有效
     */
    boolean checkCron(String cron);
}
