package com.zgy.learn.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

/**
 * @author: zgy
 * @date: 2021/6/17
 */
public class UseFileChannelBuffer {
    public static void main(String[] args) throws IOException {
        useFileChannel01();
        useFileChannelCopy();
        directBuffer();
    }

    /**
     * 输入输出流+通道
     */
    public static void useFileChannel01() throws IOException {
        FileInputStream fis = new FileInputStream("D:/美图/5c74e48a61ec7.jpg");
        FileOutputStream fos = new FileOutputStream("D:/美图/5c74e48a61ec7-copy.jpg");

        // 1. 获取channel
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        // 2. 分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 3. 将通道中的数据存入缓冲区, 循环读入, inChannel.read(dst), 表示将inChannel里面的数据读入到dst之中, 返回值为-1则表示读入结束
        while (inChannel.read(buffer) != -1) {
            // 切换读写模式
            buffer.flip();
            // 4. 将缓冲区之中的数据写入到通道之中
            outChannel.write(buffer);
            // 5. 清空缓冲区数据, 如果没有读完, 则继续下一轮的读写
            buffer.clear();
        }

        // 6. 关闭流和通道
        inChannel.close();
        outChannel.close();
        fis.close();
        fos.close();
        System.out.println("完毕: 输入输出流+通道...");

    }


    /**
     * 完全使用fileChannel进行拷贝, StandardOpenOption.XXX是操作模式
     */
    public static void useFileChannelCopy() throws IOException {
        // 1. 获取channel
        FileChannel inChannel = FileChannel.open(Paths.get("D:/美图/5.05M.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:", "美图", "5.05M-copy.jpg"), StandardOpenOption.WRITE,
                StandardOpenOption.READ, StandardOpenOption.CREATE, StandardOpenOption.CREATE_NEW);

        // 2. 分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 3. 将通道之中的数据循环读入缓冲区
        while (inChannel.read(buffer) != -1) {
            // 4. 切换写模式
            buffer.flip();
            // 5. 将缓冲区之中的数据写入通道
            outChannel.write(buffer);
            // 6. 清空缓冲区数据, 如果没有读完, 则继续下一轮的读写
            buffer.clear();
        }
        inChannel.close();
        outChannel.close();
        System.out.println("完毕: 完全使用fileChannel进行拷贝...");

    }

    /**
     * 使用直接缓冲区
     *
     * @throws IOException
     */
    public static void directBuffer() throws IOException {
        LocalDateTime start = LocalDateTime.now();
        FileChannel inChannel =
                FileChannel.open(Paths.get("D:/迅雷下载/兹山鱼谱.The.Book.of.Fish.2021.HD1080P.韩语中字.TSKS.mkv"),
                        StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:/迅雷下载/兹山鱼谱.mkv"),
                StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        // 1. 定义直接缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        while (inChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.printf("开始时间: %s, 结束时间: %s", start.toString(), end.toString());

    }

}
