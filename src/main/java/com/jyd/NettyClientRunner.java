package com.jyd;

import com.jyd.config.RemoteConfig;
import com.jyd.netty.core.NettyClient;
import com.jyd.nio.NioClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Netty客户端服务启动类
 *
 * @author Zhenlin Jin
 * @date 2021/7/22 10:59
 */
@Slf4j
@Component
public class NettyClientRunner implements CommandLineRunner {

    @Autowired
    private RemoteConfig remoteConfig;

    @Override
    public void run(String... args) throws Exception {
        // NettyClient one = new NettyClient("one", "127.0.0.1", 8899);
        // one.connect();
        // NettyClient two = new NettyClient("two", "127.0.0.1", 8899);
        // two.connect();
        //
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("请输入发送内容：");
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Channel channel = one.getChannel();
        // System.out.println("客户端1的通道ID:" + channel.id());
        // while (scanner.hasNext()) {
        //     // 获取输入内容
        //     String next = scanner.nextLine();
        //     if ("exit".equalsIgnoreCase(next)) {
        //         break;
        //     }
        //     byte[] bytes = (formatter.format(LocalDateTime.now()) + " >>" + next).getBytes("UTF-8");
        //     // 发送 ByteBuf
        //     ByteBuf byteBuf = channel.alloc().buffer();
        //     byteBuf.writeBytes(bytes);
        //     channel.writeAndFlush(byteBuf);
        //     System.out.println("请输入发送内容：");
        // }
        // one.disconnect();

    }

    @PreDestroy
    public void destory() {

    }

    public static void main(String[] args) {
        Integer i = 2;
        String s = "2";

    }
}
