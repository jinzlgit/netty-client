package com.jyd.handler;

import com.jyd.domain.decoder.BasePojo;
import com.jyd.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Zhenlin Jin
 * @date 2021/7/28 9:44
 */
@Slf4j
public class ParsePolicy {

    public static void parse(BasePojo basePojo) {
        PojoParse bean = (PojoParse) SpringUtil.getBeanByCode(basePojo.getCode());
        bean.parseString2Object(basePojo);
    }
}
