package com.jyd.handler.valid;

import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 关闭机芯
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:24
 */
@Slf4j
@Component("code0C")
public class CodeValid0C implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        if (StringUtils.equals(param, "1"))
            return "000" + param;
        throw new CommonException("参数异常，必须为1");
    }
}
