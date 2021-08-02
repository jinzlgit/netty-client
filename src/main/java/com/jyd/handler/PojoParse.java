package com.jyd.handler;

import com.jyd.domain.BaseEntity;
import com.jyd.domain.decoder.BasePojo;

/**
 * @author Zhenlin Jin
 * @date 2021/7/27 17:25
 */
public interface PojoParse {

    BaseEntity parseString2Object(BasePojo basePojo);

    String parseObject2String(BaseEntity baseEntity);
}
