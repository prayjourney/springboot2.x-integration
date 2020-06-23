package com.wm.zgy.bootmybatismbplusshiroesquartz.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author renjiaxin
 * @Date 2020/5/18
 * @Description
 */
@Slf4j
@Component
public class FileUtil {

    // 文件是否存在
    public static boolean fileExists(String filePath){
        if (!StringUtils.isBlank(filePath)){
            File file = new File(filePath);
            if (file.exists()) return true;
            else return false;
        }else{
            log.error("文件不存在, 文件的路径是: {}! ", filePath);
            return  false;
        }
    }

    // 路径是否是多层次
    public static boolean isMultiPath(String filePath){
        // File.separator java根据系统来判断， linux /, win \\
        String[] split = filePath.split(File.separator);
        if (split.length > 1){
            return true;
        }else return false;
    }

    // 创建新文件
    public static File createFile(String path) throws IOException {
        // 判断路径是否为多层级
        boolean bool = isMultiPath(path);
        if (bool) {
            String[] split = path.split(File.separator);
            String dir = "";
            for (int i = 0; i < split.length - 1; i++) {
                dir += split[i];
            }
            File dirs = new File(dir);
            // 创建了多层级的目录
            dirs.mkdirs();
        }

        File file = new File(path);
        if (!file.exists()) {                //如果文件不存在则新建文件
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
        output.write(bytes);    //将数组的信息写入文件中
        output.close();
    }
}
