package com.jyd.service;

import com.jyd.constant.Code;

import java.util.List;

/**
 * @author Zhenlin Jin
 * @date 2021/7/30 11:26
 */
public interface ConfigService {

    /**
     * 获取所有的可配置参数
     */
    List<Code.Type> getAllCode();

    /**
     * 获取某个配置参数
     */
    Code.Type getByCode(String code);

}
