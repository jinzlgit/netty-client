package com.jyd.controller;

import com.jyd.domain.web.RequestDTO;
import com.jyd.exception.CommonException;
import com.jyd.service.SetterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhenlin Jin
 * @date 2021/7/28 23:24
 */
@Slf4j
@RequestMapping("/setter")
@RestController
public class DataController extends BaseController {

    @Autowired
    private SetterService setterService;

    @PostMapping
    public Object setter(@RequestBody @Validated RequestDTO requestDTO) throws CommonException {
        setterService.sendRequest(requestDTO.getName(), requestDTO.getChannelId(),
                requestDTO.getCode(), requestDTO.getParam());
        return ok().msg("消息发送成功");
    }
}
