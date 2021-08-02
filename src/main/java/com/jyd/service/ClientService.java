package com.jyd.service;

import com.jyd.exception.ConnectException;
import com.jyd.exception.DisconnectException;
import com.jyd.netty.core.NettyClient;
import com.jyd.schedule.jobs.MyJob;
import org.quartz.SchedulerException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zhenlin Jin
 * @date 2021/7/27 16:31
 */
public interface ClientService {

    /**
     * 创建连接后存储定时任务
     */
    Map<String, MyJob> NETTY_JOB = new ConcurrentHashMap<>();

    NettyClient connect(String name, String host, int port) throws ConnectException, SchedulerException;

    boolean disconnect(String channelId) throws DisconnectException, SchedulerException;

    boolean isconnected(String channelId);
}
