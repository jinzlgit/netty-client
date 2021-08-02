package com.jyd.handler.valid;

import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 雷达敏感度设置
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:18
 */
@Slf4j
@Component("code04")
public class CodeValid04 implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        Integer value = Integer.valueOf(param);
        if (value >= 1 && value <= 400)
            return String.format(INT_TO_HEX_2, value);
        else
            throw new CommonException("参数异常,[1-400]");
    }
}
