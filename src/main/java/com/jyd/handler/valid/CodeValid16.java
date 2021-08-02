package com.jyd.handler.valid;

import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 设置最大等待时间
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:31
 */
@Slf4j
@Component("code16")
public class CodeValid16 implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        Integer value = Integer.valueOf(param);
        if (value >= 1 && value <= 100)
            return String.format(INT_TO_HEX_2, value);
        else
            throw new CommonException("参数异常,[1-100]");
    }
}
