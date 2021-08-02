package com.jyd.handler.valid;

import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 使能蜂鸣器
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:22
 */
@Slf4j
@Component("code09")
public class CodeValid09 implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        if (StringUtils.equalsAny(param, "0", "1"))
            return "000" + param;
        else
            throw new CommonException("参数异常,0:关闭,1:开启");
    }
}
