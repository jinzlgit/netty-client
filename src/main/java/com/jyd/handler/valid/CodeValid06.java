package com.jyd.handler.valid;

import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 设定雷达最远探测距离
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:19
 */
@Slf4j
@Component("code06")
public class CodeValid06 implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        Integer value = Integer.valueOf(param);
        if (value >= 1000 && value <= 7000)
            return String.format(INT_TO_HEX_2, value);
        else
            throw new CommonException("参数异常,[1000-7000]");
    }
}
