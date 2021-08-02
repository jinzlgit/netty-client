package com.jyd.netty.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.HexUtils;

/**
 * @author Zhenlin Jin
 * @date 2021/7/29 11:00
 */
@Slf4j
public class JsonToByteEncoder extends MessageToByteEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String s, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(HexUtils.fromHexString(s));
        log.info("通道[{}]发送消息:{}", ctx.channel().id().asShortText(), s);
    }
}
