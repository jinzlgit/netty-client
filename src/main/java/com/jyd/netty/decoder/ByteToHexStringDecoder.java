package com.jyd.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Zhenlin Jin
 * @date 2021/7/26 9:13
 */
@Slf4j
public class ByteToHexStringDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        String s = ByteBufUtil.hexDump(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes()).toUpperCase();
        log.info("收到响应数据:{}", s);
        // 必须从缓冲区读出数据，否则此数据会再次进入入站处理器
        ByteBuf buffer = ctx.alloc().buffer(byteBuf.readableBytes());
        byteBuf.readBytes(buffer, byteBuf.readerIndex(), byteBuf.readableBytes());
        list.add(s);
    }
}
