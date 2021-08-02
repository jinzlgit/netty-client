package com.jyd.schedule.util;

import com.jyd.schedule.jobs.MyJob;
import com.jyd.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 定时任务具体执行目标对象方法
 *
 * @author Zhenlin Jin
 * @date 2021/7/30 16:11
 */
@Slf4j
public class JobInvokeUtil {
    /**
     * 执行方法
     */
    public static void invokeMethod(MyJob job) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object bean = SpringUtil.getBeanByName(job.getJobName());
        String methodName = job.getMethodName();
        String methodParams = job.getMethodParams();

        invokeSpringBean(bean, methodName, methodParams);
    }

    private static void invokeSpringBean(Object bean, String methodName, String params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (StringUtils.isNotEmpty(params)) {
            // 第二个参数是 目标方法的参数类型
            Method method = bean.getClass().getDeclaredMethod(methodName, String.class);
            method.invoke(bean, params);
        } else {
            Method method = bean.getClass().getDeclaredMethod(methodName);
            method.invoke(bean);
        }
    }
}
