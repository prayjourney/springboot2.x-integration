package com.wm.zgy.bootmybatismbplusshiroesquartz.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author renjiaxin
 * @Date 2020/5/18
 * @Description
 */
public class FileUtil {
    public static File createFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {                //如果文件不存在则新建文件
            file.createNewFile();
        }
        return file;
    }

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

//    public static void main(String[] args) {
//        File file = new File("D:/", "word.txt");  //创建文件对象
//        try {
//            if (!file.exists()) {                //如果文件不存在则新建文件
//                file.createNewFile();
//            }
//            FileOutputStream output = new FileOutputStream(file);
//            byte[] bytes = "Java数据交流管道——IO流".getBytes();
//            output.write(bytes);                //将数组的信息写入文件中
//            output.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
