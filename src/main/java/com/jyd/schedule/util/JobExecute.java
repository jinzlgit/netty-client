package com.jyd.schedule.util;

import com.jyd.schedule.jobs.MyJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;

/**
 * 任务调度执行 默认允许并发执行
 *
 * @author Zhenlin Jin
 * @date 2021/7/30 16:28
 */
@Slf4j
public class JobExecute extends AbstractQuartzJob {

    @Override
    protected void doExecute(JobExecutionContext context, MyJob job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}
