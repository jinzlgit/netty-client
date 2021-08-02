package com.jyd.schedule.util;

import com.jyd.exception.CommonException;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

/**
 * cron表达式校验工具类
 *
 * @author Zhenlin Jin
 * @date 2021/7/31 13:28
 */
public class CronUtil {

    /**
     * 用 quartz 的类校验 cron 表达式
     */
    public static boolean isValid(String cron) {
        return CronExpression.isValidExpression(cron);
    }

    /**
     * 根据给定的 cron 表达式，计算下次执行的时间
     */
    public static Date getNextRunTime(String cron) throws CommonException {
        try {
            CronExpression cronExpression = new CronExpression(cron);
            return cronExpression.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
        } catch (ParseException e) {
            throw new CommonException("CRON表达式非法");
        }
    }
}
