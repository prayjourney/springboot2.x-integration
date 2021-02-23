package com.zgy.bootintegration;

import com.zgy.bootintegration.utils.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

/**
 * @author: z.g.y
 * @date: 2020/6/23
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUtilTest {
    // 测试系统的一些信息
    @Test
    public void testSystem() {
        String osName = System.getProperty("os.name");
        String userName = System.getProperty("user.name");
        System.out.println(osName);
        System.out.println(userName);
    }

    // 测试系统的分隔符
    @Test
    public void testFileSperator() {
        System.out.println(File.separator);
    }

    // 创建多层级目录
    @Test
    public void testCreateDir() {
        // 创建了一个3层的目录, d -> c -> 4.txt
        String path = "d://b/c/4.txt";
        File file = new File(path);
        file.mkdirs();
    }

    // 测试是否为多层级的目录
    @Test
    public void testIsMultiPath() {
        System.out.println(FileUtil.isMultiPath("d:\\1\\2\\3\\4.txt"));
    }

    @Test
    public void testCreateFile01() throws IOException {
        FileUtil.createFile("d:\\1.txt");
    }

    @Test
    public void testCreateFile02() throws IOException {
        FileUtil.createFile("d:\\1\\2\\3\\4.txt");
    }

}
