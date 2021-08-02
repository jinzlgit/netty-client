package com.jyd.service.impl;

import com.jyd.config.NettyClientConfig;
import com.jyd.netty.core.NettyClient;
import com.jyd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhenlin Jin
 * @date 2021/7/30 9:53
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<NettyClient> getAllOnLine() {
        List<NettyClient> list = new ArrayList<>();
        NettyClientConfig.CLIENT_MAP.values().forEach(list::add);
        log.info("当前在线连接数:[{}]", list.size());
        return list;
    }
}
