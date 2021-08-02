package com.jyd.handler.valid;

import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 设置雷达遮挡距离
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:25
 */
@Slf4j
@Component("code0D")
public class CodeValid0D implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        Integer value = Integer.valueOf(param);
        if (value >= 300 && value <= 1000)
            return String.format(INT_TO_HEX_2, value);
        else
            throw new CommonException("参数异常,[300-1000]");
    }
}
