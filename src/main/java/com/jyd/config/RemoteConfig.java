package com.jyd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 远程连接属性配置类
 *
 * @author Zhenlin Jin
 * @date 2021/7/22 11:01
 */
@Data
@Component
@ConfigurationProperties(prefix = "remoteserver")
public class RemoteConfig {
    /**
     * 远程IP
     */
    private String ip;
    /**
     * 远程端口
     */
    private int port;
}
