package com.jyd.service.impl;

import com.jyd.schedule.jobs.MyJob;
import com.jyd.schedule.util.CronUtil;
import com.jyd.schedule.util.ScheduleUtil;
import com.jyd.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zhenlin Jin
 * @date 2021/7/31 13:11
 */
@Slf4j
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void createNewJob(MyJob job) throws SchedulerException {
        // 新增任务让其暂停
        // job.setStatus("1");
        ScheduleUtil.createScheduleJob(scheduler, job);
    }

    @Override
    public void updateJob(MyJob job) throws SchedulerException {
        ScheduleUtil.updateScheduleJob(scheduler, job);
    }

    @Override
    public void pauseJob(MyJob job) throws SchedulerException {
        ScheduleUtil.pauseJob(scheduler, job.getJobId());
    }

    @Override
    public void resumeJob(MyJob job) throws SchedulerException {
        ScheduleUtil.resumeJob(scheduler, job.getJobId());
    }

    @Override
    public void deleteJob(MyJob job) throws SchedulerException {
        ScheduleUtil.deleteJob(scheduler, job.getJobId());
    }

    @Override
    public void changeStatus(MyJob job) throws SchedulerException {
        String status = job.getStatus();
        if ("0".equals(status)) {
            ScheduleUtil.resumeJob(scheduler, job.getJobId());
        } else if ("1".equals(status)) {
            ScheduleUtil.pauseJob(scheduler, job.getJobId());
        }
    }

    @Override
    public void runNow(MyJob job) throws SchedulerException {
        ScheduleUtil.runNow(scheduler, job);
    }

    @Override
    public boolean checkCron(String cron) {
        return CronUtil.isValid(cron);
    }
}
