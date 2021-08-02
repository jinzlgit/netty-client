package com.jyd.netty.core;

import com.jyd.netty.decoder.ByteToHexStringDecoder;
import com.jyd.netty.decoder.HexToPojoDecoder;
import com.jyd.netty.decoder.PojoHandler;
import com.jyd.netty.encoder.JsonToByteEncoder;
import com.jyd.netty.encoder.PojoToJsonEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * 自定义通道初始化类，装配通道处理流水线
 *
 * @author Zhenlin Jin
 * @date 2021/7/22 14:44
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        sc.pipeline()
                // .addLast("内置自定义长度解码器", new LengthFieldBasedFrameDecoder(1024, 3,
                //         2, 0, 5))
                // .addLast("内置字符串解码器", new StringDecoder())
                .addLast("自定义解码器", new ByteToHexStringDecoder())
                .addLast("字符串编码器", new HexToPojoDecoder())
                .addLast("数据解析处理器", new PojoHandler())
                .addLast(new JsonToByteEncoder())
                .addLast("对象编码器", new PojoToJsonEncoder());
    }
}
