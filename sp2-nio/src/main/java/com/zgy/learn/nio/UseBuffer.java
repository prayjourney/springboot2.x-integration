package com.zgy.learn.nio;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * @author zgy
 * @date 2021/6/16
 */
public class UseBuffer {
    public static void main(String[] args) throws UnsupportedEncodingException {
        bufferTest();
    }

    public static void bufferTest() throws UnsupportedEncodingException {
        // 1. 分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        String str = "Hello world!!!";
        // 2. 使用put把数据放入到缓冲区
        buf.put(str.getBytes("UTF-8"));
        System.out.println("capacity: " + buf.capacity());
        System.out.println("limit: " + buf.limit());
        System.out.println("position: " + buf.position());
    }
}
