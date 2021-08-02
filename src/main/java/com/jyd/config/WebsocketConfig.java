package com.jyd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 不配置这个的话，websocket用不了哟
 *
 * @author Zhenlin Jin
 * @date 2021/7/28 16:24
 */
@Configuration
public class WebsocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
