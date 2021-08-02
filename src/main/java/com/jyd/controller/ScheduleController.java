package com.jyd.controller;

import com.jyd.exception.CommonException;
import com.jyd.schedule.jobs.MyJob;
import com.jyd.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zhenlin Jin
 * @date 2021/7/30 15:28
 */
@Slf4j
@RequestMapping("/job")
@RestController
public class ScheduleController extends BaseController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public Object createNewJob(@RequestBody @Validated MyJob job) throws SchedulerException, CommonException {
        if (!jobService.checkCron(job.getCron())) {
            throw new CommonException("CRON表达式非法");
        }
        jobService.createNewJob(job);
        return ok().msg("创建成功");
    }

    @PostMapping("/once")
    public Object run(@RequestBody @Validated MyJob job) throws SchedulerException {
        jobService.runNow(job);
        return ok();
    }

    @PostMapping("/pause")
    public Object pause(@RequestBody @Validated MyJob job) throws SchedulerException {
        jobService.pauseJob(job);
        return ok();
    }

    @PostMapping("/resume")
    public Object resume(@RequestBody @Validated MyJob job) throws SchedulerException {
        jobService.resumeJob(job);
        return ok();
    }

    @DeleteMapping("/{jobId}")
    public Object deleteJobById(@PathVariable long jobId) {
        // 先从数据库中查询出 job ，再从调度器 schedule 中删除
        // jobService.deleteJob();
        return ok();
    }
}
