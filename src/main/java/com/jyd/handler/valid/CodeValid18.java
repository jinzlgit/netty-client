package com.jyd.handler.valid;

import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 使能/失能 继电器
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:33
 */
@Slf4j
@Component("code18")
public class CodeValid18 implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        if (StringUtils.equalsAny(param, "0", "1"))
            return "000" + param;
        else
            throw new CommonException("参数异常,0:关闭继电器,1:开启继电器");
    }
}
