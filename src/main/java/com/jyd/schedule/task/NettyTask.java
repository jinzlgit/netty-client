package com.jyd.schedule.task;

import com.jyd.config.NettyClientConfig;
import com.jyd.constant.Encoder;
import com.jyd.domain.encoder.EncoderDTO;
import com.jyd.netty.core.NettyClient;
import com.jyd.service.ClientService;
import com.jyd.service.JobService;
import com.jyd.util.Check;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Zhenlin Jin
 * @date 2021/7/31 20:10
 */
@Slf4j
@Component("nettyTask")
public class NettyTask {

    @Autowired
    private JobService jobService;

    /**
     * 定时发送查询数据请求
     */
    public void sendRequest(String channelId) {
        NettyClient client = NettyClientConfig.CLIENT_MAP.get(channelId);
        if (client == null) {
            log.warn("定时任务：已经断开连接");
            try {
                jobService.deleteJob(ClientService.NETTY_JOB.get(channelId));
            } catch (SchedulerException e) {
                log.warn("定时任务删除出错：{}", e.getMessage());
            }
            return;
        }
        Channel channel = client.getChannel();
        if (!channel.isActive() && channel.isWritable()) {
            log.warn("定时任务：连接通道异常");
            try {
                jobService.deleteJob(ClientService.NETTY_JOB.get(channelId));
            } catch (SchedulerException e) {
                log.warn("定时任务删除出错：{}", e.getMessage());
            }
            return;
        }
        // 设置消息内容
        EncoderDTO encoder = EncoderDTO.builder()
                .head(Encoder.HEAD.value())
                // 长度固定
                .length(Encoder.LENGTH.value())
                .code("A2")
                .param("0000")
                .build();
        String check = Check.check(encoder.getCheckData());
        encoder.setCheck(check);
        channel.writeAndFlush(encoder);
    }

}
