package com.jyd.schedule.util;

import com.jyd.exception.CommonException;
import com.jyd.schedule.jobs.MyJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * 这个真的是任务调度类了
 *
 * @author Zhenlin Jin
 * @date 2021/7/30 20:55
 */
@Slf4j
public class ScheduleUtil {
    /**
     * JobKey构建的前缀
     */
    private static final String JOB_KEY_PREFIX = "JOB_KEY_PREFIX";
    /**
     * 暂存JOB到DATA MAP的key值
     */
    public static final String THIS_IS_A_JOB  = "THIS_IS_A_JOB";

    /**
     * 创建任务
     */
    public static void createScheduleJob(Scheduler scheduler, MyJob job) throws SchedulerException {
        Class<? extends Job> quartzJobClass = getQuartzJobClass(job);
        // 构建Job信息
        JobDetail jobDetail = JobBuilder.newJob(quartzJobClass)
                // .withIdentity("name", "group")
                .withIdentity(getJobKey(job.getJobId()))
                .build();

        // 表达式调度构建器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
        cronScheduleBuilder = setterCronPolicy(job, cronScheduleBuilder);

        // 按新的CRON表达式构建一个新的触发器trigger
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(getTriggerKey(job.getJobId()))
                .withSchedule(cronScheduleBuilder)
                .build();

        // 放入参数，运行时的方法可以获取。此处将MyJob对象放入，后面执行Job任务时从这取参
        jobDetail.getJobDataMap().put(THIS_IS_A_JOB, job);

        // 判断是否存在
        if (scheduler.checkExists(getJobKey(job.getJobId()))) {
            // 防止创建时存在数据问题，先移除，然后再执行创建操作
            scheduler.deleteJob(getJobKey(job.getJobId()));
        }

        scheduler.scheduleJob(jobDetail, cronTrigger);

        // 暂停任务
        if (job.getStatus().equals("1")) {
            pauseJob(scheduler, job.getJobId());
        }
    }

    /**
     * 立即执行任务
     */
    public static void runNow(Scheduler scheduler, MyJob job) throws SchedulerException {
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(THIS_IS_A_JOB, job);
        scheduler.triggerJob(getJobKey(job.getJobId()), dataMap);
    }

    /**
     * 更新任务
     */
    public static void updateScheduleJob(Scheduler scheduler, MyJob job) throws SchedulerException {
        createScheduleJob(scheduler, job);
    }

    /**
     * 恢复任务
     */
    public static void resumeJob(Scheduler scheduler, long jobId) throws SchedulerException {
        scheduler.resumeJob(getJobKey(jobId));
    }

    /**
     * 暂停任务
     */
    public static void pauseJob(Scheduler scheduler, long jobId) throws SchedulerException {
        scheduler.pauseJob(getJobKey(jobId));
    }

    /**
     * 删除定时任务
     */
    public static void deleteJob(Scheduler scheduler, long jobId) throws SchedulerException {
        scheduler.deleteJob(getJobKey(jobId));
    }

    /**
     * 得到quartz框架的Job任务类用于创建JobDetail
     */
    private static Class<? extends Job> getQuartzJobClass(MyJob job) {
        boolean isConcurrent = "0".equals(job.getConcurrent());
        return isConcurrent ? JobExecute.class : JobExecuteNotConcurrent.class;
    }

    /**
     * 构建jobkey
     */
    private static JobKey getJobKey(long jobId) {
        return JobKey.jobKey(JOB_KEY_PREFIX + jobId);
    }

    /**
     * 构建triggerkey
     */
    private static TriggerKey getTriggerKey(long jobId) {
        return TriggerKey.triggerKey(JOB_KEY_PREFIX + jobId);
    }

    /**
     * 设置CRON触发策略
     */
    private static CronScheduleBuilder setterCronPolicy(MyJob job, CronScheduleBuilder cb) throws CommonException {
        switch (job.getCronPolicy()) {
            case "0":
                return cb;
            case "1":
                return cb.withMisfireHandlingInstructionIgnoreMisfires();
            case "2":
                return cb.withMisfireHandlingInstructionFireAndProceed();
            case "3":
                return cb.withMisfireHandlingInstructionDoNothing();
            default:
                throw  new CommonException("CRON触发执行策略" + job.getCronPolicy() + "不合法");
        }
    }
}
