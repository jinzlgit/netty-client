package com.jyd.handler.valid;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jyd.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.List;

/**
 * 设置雷达开角范围
 *
 * @author Zhenlin Jin
 * @date 2021/7/29 14:23
 */
@Slf4j
@Component("code0A")
public class CodeValid0A implements ParamValidPolicy {

    @Override
    public String valid(String param) throws CommonException {
        List<Integer> array = JSONArray.parseArray(param, Integer.class);
        if (array.size() < 2)
            throw new CommonException("参数异常，参数个数必须为2");
        Integer v1 = array.get(0);
        Integer v2 = array.get(1);
        if (!(v1 >= 0 && v1 <= 108))
            throw new CommonException("参数异常，起始角度必须符合[0-108]");
        if (!(v2 >= 0 && v2 <= 108))
            throw new CommonException("参数异常，停止角度必须符合[0-108]");
        return String.format(INT_TO_HEX_1, v1) + String.format(INT_TO_HEX_1, v2);
    }

}
