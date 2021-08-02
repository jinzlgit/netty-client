package com.jyd.controller;

import com.jyd.constant.Code;
import com.jyd.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Zhenlin Jin
 * @date 2021/7/30 11:25
 */
@Slf4j
@RequestMapping("/config")
@RestController
public class ConfigController extends BaseController {

    @Autowired
    private ConfigService configService;

    @GetMapping
    public Object getAll() {
        List<Code.Type> list = configService.getAllCode();
        return ok().data(list);
    }

    @GetMapping("/{code}")
    public Object getBycode(@PathVariable String code) {
        Code.Type type = configService.getByCode(code);
        return ok().data(type);
    }
}
