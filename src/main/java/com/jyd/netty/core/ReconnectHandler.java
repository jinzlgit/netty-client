// package com.jyd.netty.core;
//
// import io.netty.channel.ChannelHandler;
// import io.netty.channel.ChannelHandlerContext;
// import io.netty.channel.ChannelInboundHandlerAdapter;
// import io.netty.channel.EventLoop;
// import lombok.extern.slf4j.Slf4j;
//
// import java.util.concurrent.TimeUnit;
//
// /**
//  * 重连处理器
//  *
//  * @author Zhenlin Jin
//  * @date 2021/7/26 20:10
//  */
// @Slf4j
// public class ReconnectHandler extends ChannelInboundHandlerAdapter {
//     private int retries = 0;
//     private RetryPolicy retryPolicy;
//
//     private NettyClient nettyClient;
//
//     public ReconnectHandler(NettyClient nettyClient) {
//         this.nettyClient = nettyClient;
//         this.retryPolicy = nettyClient.getRetryPolicy();
//     }
//
//     @Override
//     public void channelActive(ChannelHandlerContext ctx) throws Exception {
//         log.info("成功连接到服务器");
//         this.retries = 0;
//         ctx.fireChannelActive();
//     }
//
//     @Override
//     public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//         if (retries == 0) {
//             log.info("与服务器失去连接");
//             ctx.close();
//         }
//
//         boolean allowRetry = getRetryPolicy().allowRetry(retries);
//         if (allowRetry) {
//             long sleepTimeMs = getRetryPolicy().getSleepTimeMs(retries);
//             log.info("尝试在[{}]ms后重连,当前重连次数为[{}]", sleepTimeMs, retries);
//             final EventLoop eventLoop = ctx.channel().eventLoop();
//             eventLoop.schedule(new Runnable() {
//                 @Override
//                 public void run() {
//                     log.info("尝试重连中...");
//                     try {
//                         nettyClient.connect();
//                     } catch (InterruptedException e) {
//                         log.error("重连出错:" + e.getMessage());
//                     }
//                 }
//             }, sleepTimeMs, TimeUnit.MILLISECONDS);
//         }
//
//         ctx.fireChannelInactive();
//     }
//
//     private RetryPolicy getRetryPolicy() {
//         return this.retryPolicy;
//     }
// }
