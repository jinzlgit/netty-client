package com.jyd.handler.valid;

import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 使能 失能RGB
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:17
 */
@Slf4j
@Component("code03")
public class CodeValid03 implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        if (StringUtils.equalsAny(param, "0", "1"))
            return "000" + param;
        else
            throw new CommonException("参数异常,0:关闭LED,1:开启LED");
    }

}
