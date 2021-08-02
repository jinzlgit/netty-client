package com.jyd.handler;

import com.jyd.domain.BaseEntity;
import com.jyd.domain.SetterResponse;
import com.jyd.domain.decoder.BasePojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Zhenlin Jin
 * @date 2021/7/28 8:59
 */
@Slf4j
@Component
public class SetterResponseParse extends AbstractPojoParse<SetterResponse> {

    @Override
    public BaseEntity parseString2Object(BasePojo basePojo) {
        // 先进行校验
        // if (!checkData(basePojo.getData())) {
        //     log.warn("校验未通过:通道[{}],功能码[{}]", basePojo.getChannelId(), basePojo.getCode());
        //     return null;
        // }
        // 解析为对象
        // 设置参数指令的响应报文暂不解析
        return null;
    }

    @Override
    protected SetterResponse parse(BasePojo basePojo) {
        return null;
    }
}
