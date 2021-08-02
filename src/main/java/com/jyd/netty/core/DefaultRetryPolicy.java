package com.jyd.netty.core;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author Zhenlin Jin
 * @date 2021/7/26 19:40
 */
@Slf4j
public class DefaultRetryPolicy implements RetryPolicy {

    private static final int MAX_RETRIES_LIMIT = 29;
    private static final int DEFAULT_MAX_SLEEP_MS = Integer.MAX_VALUE;

    private final Random random = new Random();
    private final long baseSleepTimeMs;
    private final int maxRetries;
    private final int maxSleepMs;

    public DefaultRetryPolicy(int baseSleepTimeMs, int maxRetries) {
        this(baseSleepTimeMs, maxRetries, DEFAULT_MAX_SLEEP_MS);
    }

    public DefaultRetryPolicy(int baseSleepTimeMs, int maxRetries, int maxSleepMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
        this.maxRetries = maxRetries;
        this.maxSleepMs = maxSleepMs;
    }

    @Override
    public boolean allowRetry(int retryCount) {
        if (retryCount < maxRetries) {
            return true;
        }
        return false;
    }

    @Override
    public long getSleepTimeMs(int retryCount) {
        if (retryCount < 0)
            throw new IllegalArgumentException("重试次数必须大于零");
        if (retryCount > MAX_RETRIES_LIMIT) {
            log.info("重试次数[{}]已超过当前策略设置的限制,自动修改为默认最大值[{}]", maxRetries, MAX_RETRIES_LIMIT);
            retryCount = MAX_RETRIES_LIMIT;
        }
        long sleepMs = baseSleepTimeMs * Math.max(1, random.nextInt(1 << retryCount));
        if (sleepMs > maxSleepMs) {
            log.info("重试间隔[{}]超过当前策略阈值,自动修改为此阈值[{}]", sleepMs, maxSleepMs);
            sleepMs = maxSleepMs;
        }
        return sleepMs;
    }
}
