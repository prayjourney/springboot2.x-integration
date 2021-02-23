package com.zgy.bootintegration.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author: z.g.y
 * @date: 2020/5/18
 * @description:
 */
@Slf4j
@Component
public class FileUtil {
    // 文件是否存在
    public static boolean fileExists(String filePath) {
        if (!StringUtils.isBlank(filePath)) {
            File file = new File(filePath);
            if (file.exists()) {
                return true;
            } else {
                return false;
            }
        } else {
            log.error("文件不存在, 文件的路径是: {}! ", filePath);
            return false;
        }
    }

    // 路径是否是多层次
    public static boolean isMultiPath(String filePath) {
        // File.separator java根据系统来判断， linux /, win \
        // File.separator使用split解析会出错: https://blog.csdn.net/jrx1995/article/details/99591897
        String separator = "/|\\\\";
        List<String> path = Arrays.asList(filePath.split(separator));
        if (path.size() > 2) {
            return true;
        } else {
            return false;
        }
    }

    // 创建新文件, 支持多层级, 最后的一个是文件名, 如: d:\\1\\2\\3\\4.txt"
    public static File createFile(String filePath) throws IOException {
        // 判断路径是否为多层级
        boolean bool = isMultiPath(filePath);
        if (bool) {
            String separator = "/|\\\\";
            List<String> pathList = Arrays.asList(filePath.split(separator));

            String osName = System.getProperty("os.name");
            String systemSeparator = "";
            if (osName.contains("Win")) {
                systemSeparator = "\\";
            } else {
                systemSeparator = "/";
            }

            String path = "";
            for (int i = 0; i < pathList.size() - 1; i++) {
                path += pathList.get(i) + systemSeparator;
            }

            // 创建了多层级的目录
            File f = new File(path);
            f.mkdirs();
        }

        File file = new File(filePath);
        // 如果文件不存在则新建文件, 如果是个文件夹则也去创建
        if (!file.exists()) {
            file.createNewFile();
        }
        if (file.exists() && file.isDirectory()) {
            file.delete();
            log.warn("删除了原先的同名文件夹! {} !", file.getName());
            file.createNewFile();
        }
        return file;
    }

    // 写内容到某个路径
    public static void writeContent(String path, String content) throws IOException {
        File f = new File(path);
        if (!f.exists()) {
            f = createFile(path);
        }
        FileOutputStream output = new FileOutputStream(f);
        byte[] bytes = content.getBytes("UTF-8");
        // 将数组的信息写入文件中
        output.write(bytes);
        output.close();
    }
}
