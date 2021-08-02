package com.jyd.netty.encoder;

import com.jyd.domain.encoder.EncoderDTO;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Zhenlin Jin
 * @date 2021/7/26 9:12
 */
@Slf4j
// @ChannelHandler.Sharable
public class PojoToJsonEncoder extends MessageToMessageEncoder<EncoderDTO> {

    @Override
    protected void encode(ChannelHandlerContext ctx, EncoderDTO encoderDTO, List<Object> out) throws Exception {
        log.info("消息进入出站处理器,通道[{}]", ctx.channel().id().asShortText());
        String json = encoderDTO.toString();
        out.add(json);
    }
}
