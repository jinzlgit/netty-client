package com.jyd.schedule.util;

import com.jyd.schedule.jobs.MyJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 任务调度执行 不允许并发执行
 *
 * @author Zhenlin Jin
 * @date 2021/7/30 17:51
 */
@DisallowConcurrentExecution
public class JobExecuteNotConcurrent extends AbstractQuartzJob {

    @Override
    protected void doExecute(JobExecutionContext context, MyJob job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}
