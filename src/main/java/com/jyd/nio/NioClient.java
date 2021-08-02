package com.jyd.nio;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author Zhenlin Jin
 * @date 2021/7/27 9:53
 */
@Slf4j
@Data
public class NioClient {

    private String host;

    private int port;

    private SocketChannel socketChannel;

    private Selector selector;

    private ByteBuffer byteBuffer;

    private InetSocketAddress inetSocketAddress;

    public NioClient(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        init();
    }

    private void init() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true);
        selector = Selector.open();
        inetSocketAddress = new InetSocketAddress(host, port);
    }

    public void connect() throws IOException {
        boolean connect = this.socketChannel.connect(this.inetSocketAddress);
        if (connect) {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            selector.select();
            log.info("可连接事件产生,IP[{}],port[{}]", this.getHost(), this.getPort());
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                log.info("客户端开始连接");
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                if (key.isValid() && key.isConnectable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    if (sc.isConnectionPending()) {
                        sc.finishConnect();
                        log.info("客户端连接成功,IP[{}],port[{}]", this.getHost(), this.getPort());
                    } else {
                        this.socketChannel.close();
                        this.selector.close();
                        // return false;
                    }
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }
            }
        }
        // return false;
    }
}
