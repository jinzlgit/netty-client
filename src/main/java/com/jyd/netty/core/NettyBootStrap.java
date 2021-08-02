package com.jyd.netty.core;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;
import java.io.Serializable;

/**
 * @author Zhenlin Jin
 * @date 2021/7/22 14:22
 */
@Slf4j
public class NettyBootStrap implements Serializable {

    private volatile static Bootstrap bootstrap = null;

    private static final NioEventLoopGroup worker = new NioEventLoopGroup();;

    private NettyBootStrap() {
        bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.ALLOCATOR, ByteBufAllocator.DEFAULT)
                .handler(new ClientChannelInitializer());
        log.info("Bootstrap初始化成功");
    }

    public static Bootstrap getBootStrap() {
        if (bootstrap == null) {
            synchronized (NettyBootStrap.class) {
                if (bootstrap == null) {
                    new NettyBootStrap();
                }
            }
        }
        return bootstrap;
    }

    @PreDestroy
    public void preDestory() {
        worker.shutdownGracefully();
    }
}
