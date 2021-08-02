package com.jyd.schedule.util;

import com.alibaba.fastjson.JSONObject;
import com.jyd.schedule.jobs.MyJob;
import com.jyd.schedule.jobs.MyJobLog;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Job是quartz框架的具体任务需要实现的接口，只有一个方法execute
 * 继承Job并实现此方法写具体执行逻辑
 *
 * 此方法是包装一下，记录执行的状态、结果、异常等等
 *
 * @author Zhenlin Jin
 * @date 2021/7/30 16:33
 */
@Slf4j
public abstract class AbstractQuartzJob implements Job {
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        MyJob myJob = new MyJob();
        MyJob src = (MyJob) context.getMergedJobDataMap().get(ScheduleUtil.THIS_IS_A_JOB);
        myJob.setJobId(src.getJobId());
        myJob.setJobName(src.getJobName());
        myJob.setJobGroup(src.getJobGroup());
        myJob.setMethodName(src.getMethodName());
        myJob.setMethodParams(src.getMethodParams());
        myJob.setCron(src.getCron());
        myJob.setCronPolicy(src.getCronPolicy());
        myJob.setConcurrent(src.getConcurrent());
        myJob.setStatus(src.getStatus());
        try {
            before(context, myJob);
            if (myJob != null) {
                doExecute(context, myJob);
            }
            after(context, myJob, null);
        } catch (Exception e) {
            log.error("定时任务执行异常:{}", e);
            after(context, myJob, e);
        }
    }

    /**
     * 执行方法，由子类重写
     */
    protected abstract void doExecute(JobExecutionContext context, MyJob job) throws Exception;

    private void before(JobExecutionContext context, MyJob job) {
        threadLocal.set(new Date());
    }

    private void after(JobExecutionContext context, MyJob job, Exception e) {
        Date startTime = threadLocal.get();
        threadLocal.remove();

        MyJobLog jobLog = MyJobLog.builder()
                .jobName(job.getJobName())
                .jobGroup(job.getJobGroup())
                .methodName(job.getMethodName())
                .methodParams(job.getMethodParams())
                .startTime(startTime)
                .endTime(new Date())
                .build();
        long runMs = jobLog.getEndTime().getTime() - startTime.getTime();
        jobLog.setJobMessages(jobLog.getJobName() + "总共耗时:" + runMs + "毫秒");
        if (e != null) {
            jobLog.setStatus("1");
            jobLog.setExceptionInfo(e.getMessage());
        } else {
            jobLog.setStatus("0");
        }

        // 将日志持久化到数据库
        // 此项目无数据库，打印到日志文件即可
        log.info(JSONObject.toJSONString(jobLog));
    }
}
