package com.jyd.service.impl;

import com.jyd.constant.Code;
import com.jyd.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zhenlin Jin
 * @date 2021/7/30 11:26
 */
@Slf4j
@Service
public class ConfigServiceImpl implements ConfigService {
    private static final String CODE = "CODE";

    @Override
    public List<Code.Type> getAllCode() {
        return Arrays.stream(Code.values()).map(Code::value).collect(Collectors.toList());
    }

    @Override
    public Code.Type getByCode(String code) {
        return Code.valueOf(CODE + code).value();
    }

}
