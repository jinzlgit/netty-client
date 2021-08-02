package com.jyd.util;

import com.jyd.handler.ContourDataParse;
import com.jyd.handler.DeviceInfoParse;
import com.jyd.handler.SetterResponseParse;
import com.jyd.handler.valid.ParamValidPolicy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Zhenlin Jin
 * @date 2021/7/27 16:18
 */
@Slf4j
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static Object getBeanByName(String name) {
        return context.getBean(name);
    }

    /**
     * 根据功能码获取对应的参数校验实例
     */
    public static ParamValidPolicy getValidBeanByName(String name) {
        return StringUtils.isNoneBlank(name) ? (ParamValidPolicy) context.getBean(name) : null;
    }

    /**
     * 根据功能码获取对应的解析策略实例
     */
    public static Object getBeanByCode(String code) {
        if (StringUtils.isBlank(code))
            return null;
        if (StringUtils.equalsIgnoreCase("A1", code) || StringUtils.equalsIgnoreCase("A2", code))
            return context.getBean(ContourDataParse.class);
        else if (StringUtils.equalsIgnoreCase("A3", code))
            return context.getBean(DeviceInfoParse.class);
        else
            return context.getBean(SetterResponseParse.class);
    }
}
