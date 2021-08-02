package com.jyd.handler.valid;

import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 设置CAN发送延迟
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:34
 */
@Slf4j
@Component("code19")
public class CodeValid19 implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        Integer value = Integer.valueOf(param);
        if (value >= 0 && value <= 30)
            return String.format(INT_TO_HEX_2, value);
        else
            throw new CommonException("参数异常,[0-30]");
    }
}
