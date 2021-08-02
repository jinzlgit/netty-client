package com.jyd.handler.valid;

import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 恢复雷达出厂设置
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:30
 */
@Slf4j
@Component("code14")
public class CodeValid14 implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        if (!StringUtils.equalsIgnoreCase(param, "0")) {
            throw new CommonException("参数异常,固定为0");
        }
        return param + "000";
    }
}
