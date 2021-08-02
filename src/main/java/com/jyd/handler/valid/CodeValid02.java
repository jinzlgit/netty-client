package com.jyd.handler.valid;

import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

/**
 * 总线ID地址设置
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:14
 */
@Slf4j
@Component("code02")
public class CodeValid02 implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        Integer value = Integer.valueOf(param);
        if (value >= 1 && value <= 255)
            return String.format(INT_TO_HEX_2, value);
        else
            throw new CommonException("参数异常,[1-255]");
    }

}
