package com.jyd.controller.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Zhenlin Jin
 * @date 2021/7/26 15:16
 */
@Slf4j
@Component
@ServerEndpoint(value = "/contour/{name}")
public class ContourWebSocket {
    // private static final Set<MyWebSocket> onLine = new CopyOnWriteArraySet<>();

    private static final Map<String, ContourWebSocket> ONLINE = new ConcurrentHashMap<>();

    private Session session;

    private String name;

    private static AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

    @OnOpen
    public void onOpen(@PathParam("name") String name, Session session) {
        this.session = session;
        this.name = name;
        ONLINE.put(name, this);
        ONLINE_COUNT.incrementAndGet();
        log.info("WebSocket:客户端[{}]建立连接成功", name);
    }

    @OnClose
    public void onClose() {
        log.info("WebSocket:客户端[{}]断开连接", name);
        ONLINE.remove(this.name);
        ONLINE_COUNT.decrementAndGet();
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error(throwable.getMessage());
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        log.info("收到客户端[{}]消息:{}", session.getId(), message);
        // 回复用户
        session.getBasicRemote().sendText("收到客户端的消息:" + message);
    }

    public void sendText(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("发送消息到[{}]客户端出错:{}", this.session.getId(), e.getMessage());
        }
    }

    /**
     * 群发消息
     *
     * @param message 消息内容
     */
    public static void sendMessage(String message) {
        ONLINE.forEach((s, myWebSocket) -> {
            myWebSocket.sendText(message);
        });
    }

    /**
     * 向连接指定通道的客户端发送消息
     *
     * @param name 客户端名称
     * @param message　消息内容
     */
    public static void sendMsg4Channel(String name, String message) {
        ONLINE.forEach((s, myWebSocket) -> {
            if (s.equalsIgnoreCase(name)) {
                myWebSocket.sendText(message);
            }
        });
    }
}
