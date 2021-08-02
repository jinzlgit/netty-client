package com.jyd.controller;

import com.jyd.netty.core.NettyClient;
import com.jyd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Zhenlin Jin
 * @date 2021/7/30 9:44
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Object getAllOnLine() {
        List<NettyClient> allOnLine = userService.getAllOnLine();
        return ok().msg("获取成功").data(allOnLine);
    }
}
