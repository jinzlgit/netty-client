package com.jyd.netty.decoder;

import com.jyd.config.NettyClientConfig;
import com.jyd.domain.decoder.BasePojo;
import com.jyd.handler.ParsePolicy;
import com.jyd.service.ClientService;
import com.jyd.service.JobService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Zhenlin Jin
 * @date 2021/7/27 15:23
 */
@Slf4j
// @ChannelHandler.Sharable
public class PojoHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("通道[{}]建立连接", ctx.channel().id().asShortText());
        // ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String channelId = ctx.channel().id().asShortText();
        log.info("通道[{}]断开连接,并从客户端集合中删除此连接", channelId);
        NettyClientConfig.CLIENT_MAP.remove(channelId);
        // ctx.fireChannelInactive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BasePojo basePojo = (BasePojo) msg;
        String channelId = ctx.channel().id().asShortText();
        basePojo.setChannelId(channelId);
        log.info("收到功能码[{}]的数据开始解析,通道:[{}]", basePojo.getCode(), channelId);
        ParsePolicy.parse(basePojo);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String channelId = ctx.channel().id().asShortText();
        log.info("通道[{}]连接出错,错误信息:{}", channelId, cause.getMessage());
        NettyClientConfig.CLIENT_MAP.remove(channelId);
    }
}
