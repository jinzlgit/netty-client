package com.jyd.controller;

import com.jyd.config.NettyClientConfig;
import com.jyd.domain.web.ConnectDTO;
import com.jyd.exception.ConnectException;
import com.jyd.exception.DisconnectException;
import com.jyd.netty.core.NettyClient;
import com.jyd.service.ClientService;
import com.jyd.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zhenlin Jin
 * @date 2021/7/26 13:41
 */
@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController extends BaseController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public Object connect(@RequestBody @Validated ConnectDTO connectDTO) throws ConnectException, SchedulerException {
        NettyClient client = clientService.connect(connectDTO.getClientName(), connectDTO.getIp(), connectDTO.getPort());
        if (client.getChannel() != null && client.getChannel().isOpen()) {
            return ok().msg("连接成功").data(client.channelId());
        }
        return fail().msg("连接失败");
    }

    @GetMapping("/{channelId}")
    public Object select(@PathVariable String channelId) {
        if (clientService.isconnected(channelId))
            return ok().msg("连接正常").data(channelId);
        else
            return fail().msg("连接异常").data(channelId);
    }

    @DeleteMapping("/{channelId}")
    public Object disconnect(@PathVariable String channelId) throws DisconnectException, SchedulerException {
        if (clientService.disconnect(channelId)) {
            return ok().msg("断开连接成功").data(channelId);
        }
        return fail().data("连接已经断开");
    }

}
