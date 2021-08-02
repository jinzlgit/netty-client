package com.jyd.netty.core;

/**
 * 重连策略
 *
 * @author Zhenlin Jin
 * @date 2021/7/26 19:37
 */
public interface RetryPolicy {

    boolean allowRetry(int retryCount);

    long getSleepTimeMs(int retryCount);
}
