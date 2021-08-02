package com.jyd.service;

import com.jyd.netty.core.NettyClient;

import java.util.List;

/**
 * @author Zhenlin Jin
 * @date 2021/7/30 9:45
 */
public interface UserService {
    /**
     * 获取当前在线连接客户端
     */
    List<NettyClient> getAllOnLine();
}
