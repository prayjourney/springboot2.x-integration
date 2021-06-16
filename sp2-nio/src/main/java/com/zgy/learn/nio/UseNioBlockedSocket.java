package com.zgy.learn.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author: zgy
 * @date: 2021/6/17
 * @desc: 这样写和直接使用Socket写没有区别
 */
public class UseNioBlockedSocket {

    // 客户端
    @Test
    public void client() throws IOException {
        System.out.println("客户端开始上线...");
        // 1. 获取通道, fileChannel是in, socketChannel是out
        FileChannel inChannel = FileChannel.open(Paths.get("D:/美图/5c74e48a61ec7.jpg"), StandardOpenOption.READ);
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9988));

        // 2. 分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 3. 将通道之中的数据循环读入缓冲区
        while (inChannel.read(buffer) != -1) {
            buffer.flip();
            // 4. 发送到服务端
            socketChannel.write(buffer);
            buffer.clear();
        }
        // 5. 关闭通道
        socketChannel.close();
        inChannel.close();
        System.out.println("客户端下线...");

    }

    // 服务端
    @Test
    public void server() throws IOException {
        System.out.println("服务器开始上线...");
        // 1. 获取通道, fileChannel是out, serverSocketChannel是in
        FileChannel outChannel = FileChannel.open(Paths.get("D:/美图/5c74e48a61ec7-003.jpg"), StandardOpenOption.WRITE,
                StandardOpenOption.CREATE_NEW);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 2. 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9988));

        // 3. 获取客户端连接通道
        SocketChannel socketChannel = serverSocketChannel.accept();

        // 4. 分配缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 5. 接收客户端数据, 保存到本地
        while (socketChannel.read(buf) != -1) {
            // 切换读模式
            buf.flip();
            outChannel.write(buf);
            // 清空
            buf.clear();
        }

        // 6. 关闭通道
        socketChannel.close();
        serverSocketChannel.close();
        outChannel.close();
        System.out.println("服务器下线...");
    }

}
