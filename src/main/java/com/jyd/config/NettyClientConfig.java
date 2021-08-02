package com.jyd.config;

import com.jyd.netty.core.NettyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zhenlin Jin
 * @date 2021/7/26 15:45
 */
@Slf4j
@Configuration
public class NettyClientConfig {
    public static final Map<String, NettyClient> CLIENT_MAP = new ConcurrentHashMap<>();

    @PreDestroy
    public void preDestroy() {
        log.info("容器关闭，清理客户端连接");
        CLIENT_MAP.forEach((o, client) -> {
            try {
                log.info("关闭客户端[{}],通道:[{}]", Optional.ofNullable(client.getName()).orElse(""), o);
                client.disconnect();
            } catch (InterruptedException e) {
                log.error("关闭连接出错:{}", e.getMessage());
            }
        });
    }
}
