package com.jyd.schedule.util;

import com.jyd.schedule.jobs.MyJob;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定时任务存储
 *
 * @author Zhenlin Jin
 * @date 2021/7/31 19:33
 */
@Slf4j
public class JobCache {

    private static final Map<Long, MyJob> map = new ConcurrentHashMap<>();

}
