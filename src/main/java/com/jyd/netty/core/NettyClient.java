package com.jyd.netty.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jyd.config.NettyClientConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * Netty客户端
 *
 * @author Zhenlin Jin
 * @date 2021/7/22 9:41
 */
@Slf4j
@Data
public class NettyClient {

    private String name;

    private String host;

    private int port;

    @JsonIgnore
    private Bootstrap bootstrap;
    /**
     * 将 Channel 保存起来，可用于在其它非 handler 的地方发送给数据
     */
    @JsonIgnore
    private Channel channel;

    public NettyClient(String host, int port) {
        this(null, host, port);
    }

    public NettyClient(String name, String host, int port) {
        this.name = name;
        this.host = host;
        this.port = port;
        init();
    }

    private void init() {
        bootstrap = NettyBootStrap.getBootStrap();
    }

    public void connect() throws InterruptedException {
        synchronized (bootstrap) {
            ChannelFuture connect = bootstrap.connect(this.host, this.port);
            log.info("客户端{}开始连接:IP[{}],port[{}]", Optional.ofNullable(name).orElse(""), this.getHost(), this.getPort());
            connect.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        log.info("客户端{}连接成功", Optional.ofNullable(name).orElse(""));
                    } else {
                        log.info("客户端{}连接失败", Optional.ofNullable(name).orElse(""));
                    }
                }
            });
            connect.sync();
            this.channel = connect.channel();
        }
        NettyClientConfig.CLIENT_MAP.put(this.channelId(), this);
    }

    public void disconnect() throws InterruptedException {
        ChannelFuture disconnect = this.channel.disconnect();
        disconnect.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    log.info("客户端{}断开成功", Optional.ofNullable(name).orElse(""));
                } else {
                    log.info("客户端{}断开失败", Optional.ofNullable(name).orElse(""));
                }
            }
        });
        disconnect.sync();
        NettyClientConfig.CLIENT_MAP.remove(this.channelId());
    }

    public String channelId() {
        return this.channel.id().asShortText();
    }

}
