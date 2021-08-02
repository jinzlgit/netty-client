package com.jyd.handler.valid;

import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 修改防区参数
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:41
 */
@Slf4j
@Component("codeA4")
public class CodeValidA4 implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        // TODO 修改防区参数设置
        return param;
    }
}
