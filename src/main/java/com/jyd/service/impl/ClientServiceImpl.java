package com.jyd.service.impl;

import com.jyd.config.NettyClientConfig;
import com.jyd.exception.CommonException;
import com.jyd.exception.ConnectException;
import com.jyd.exception.DisconnectException;
import com.jyd.netty.core.NettyClient;
import com.jyd.schedule.jobs.MyJob;
import com.jyd.service.ClientService;
import com.jyd.service.JobService;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Zhenlin Jin
 * @date 2021/7/27 16:35
 */
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private static final AtomicLong NETTY_JOB_ID = new AtomicLong(1);

    @Autowired
    private JobService jobService;

    @Override
    public NettyClient connect(String name, String host, int port) throws ConnectException, SchedulerException {
        if (NettyClientConfig.CLIENT_MAP.values().stream().anyMatch(o -> o.getName().equals(name))) {
            throw new CommonException("客户端名称已存在:" + name);
        }
        NettyClient nettyClient = new NettyClient(name, host, port);
        try {
            nettyClient.connect();
        } catch (InterruptedException e) {
            throw new ConnectException("建立连接失败", e);
        }
        // 定时获取实时数据
        MyJob job = new MyJob();
        job.setJobId(NETTY_JOB_ID.incrementAndGet());
        job.setJobName("nettyTask");
        job.setJobGroup("nettyGroup");
        job.setMethodName("sendRequest");
        job.setMethodParams(nettyClient.channelId());
        job.setCron("3/2 * * * * ? ");
        job.setCronPolicy("0");
        job.setConcurrent("1");
        job.setStatus("0");
        jobService.createNewJob(job);
        NETTY_JOB.put(nettyClient.channelId(), job);
        return nettyClient;
    }

    @Override
    public boolean disconnect(String channelId) throws DisconnectException, SchedulerException {
        jobService.deleteJob(NETTY_JOB.get(channelId));
        NETTY_JOB.remove(channelId);
        if (NettyClientConfig.CLIENT_MAP.containsKey(channelId)) {
            try {
                NettyClientConfig.CLIENT_MAP.get(channelId).disconnect();
                return true;
            } catch (InterruptedException e) {
                throw new DisconnectException("断开连接失败", e);
            }
        }
        return false;
    }

    @Override
    public boolean isconnected(String channelId) {
        if (NettyClientConfig.CLIENT_MAP.containsKey(channelId)) {
            Channel channel = NettyClientConfig.CLIENT_MAP.get(channelId).getChannel();
            if (channel.isActive())
                return true;
            else
                NettyClientConfig.CLIENT_MAP.remove(channelId);
        }
        return false;
    }
}
