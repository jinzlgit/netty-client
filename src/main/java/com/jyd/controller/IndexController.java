package com.jyd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Zhenlin Jin
 * @date 2021/8/2 9:48
 */
@Slf4j
@RequestMapping("/echarts")
@Controller
public class IndexController {

    @GetMapping
    public String index() {
        return "echarts";
    }
}
