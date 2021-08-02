package com.jyd.handler.valid;

import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 设置姿态自学习
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:34
 */
@Slf4j
@Component("code1A")
public class CodeValid1A implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        if (!StringUtils.equalsIgnoreCase(param, "1")) {
            throw new CommonException("参数异常,固定为1");
        }
        return param + "000";
    }
}
