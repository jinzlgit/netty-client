package com.jyd.handler.valid;

import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 雷达缩进防区
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:19
 */
@Slf4j
@Component("code05")
public class CodeValid05 implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        Integer value = Integer.valueOf(param);
        if (value >= 50 && value <= 1000)
            return String.format(INT_TO_HEX_2, value);
        else
            throw new CommonException("参数异常,[50-1000]");
    }
}
