package com.jyd.service.impl;

import com.jyd.config.NettyClientConfig;
import com.jyd.constant.Encoder;
import com.jyd.domain.encoder.EncoderDTO;
import com.jyd.exception.CommonException;
import com.jyd.netty.core.NettyClient;
import com.jyd.service.SetterService;
import com.jyd.util.Check;
import com.jyd.util.SpringUtil;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Zhenlin Jin
 * @date 2021/7/28 23:30
 */
@Slf4j
@Service
public class SetterServiceImpl implements SetterService {

    @Override
    public void sendRequest(String name, String channelId, String code, String param) throws CommonException {
        NettyClient client = NettyClientConfig.CLIENT_MAP.get(channelId);
        if (client == null)
            throw new CommonException("已经断开连接");
        Channel channel = client.getChannel();
        if (!channel.isActive() && channel.isWritable())
            throw new CommonException("连接通道异常");

        String validParam = SpringUtil
                .getValidBeanByName(Encoder.CODE.value() + code.toUpperCase())
                .valid(param);
        // 设置消息内容
        EncoderDTO encoder = EncoderDTO.builder()
                .head(Encoder.HEAD.value())
                // 长度固定
                .length(Encoder.LENGTH.value())
                .code(code)
                .param(validParam)
                .build();
        String check = Check.check(encoder.getCheckData());
        encoder.setCheck(check);
        channel.writeAndFlush(encoder);
    }

}
