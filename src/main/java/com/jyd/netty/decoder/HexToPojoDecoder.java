package com.jyd.netty.decoder;

import com.jyd.domain.decoder.BasePojo;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author Zhenlin Jin
 * @date 2021/7/27 16:09
 */
// @ChannelHandler.Sharable
public class HexToPojoDecoder extends MessageToMessageDecoder<String> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, String s, List<Object> list) throws Exception {
        String code = s.substring(10, 12);
        BasePojo basePojo = new BasePojo(null, code, s);
        list.add(basePojo);
    }
}
