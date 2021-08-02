package com.jyd.schedule.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author Zhenlin Jin
 * @date 2021/7/30 16:01
 */
@Slf4j
@Component("myTask")
public class MyTask {
    public void params(String params) {
        log.info("执行有参方法:[{}]", params);
    }

    public void noParam() {
        log.info("执行无参方法");
    }
}
