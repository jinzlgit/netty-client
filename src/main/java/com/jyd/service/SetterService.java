package com.jyd.service;

import com.jyd.exception.CommonException;

/**
 * @author Zhenlin Jin
 * @date 2021/7/28 23:28
 */
public interface SetterService {

    void sendRequest(String name, String channelId, String code, String param) throws CommonException;
}
