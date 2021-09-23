package com.zgy.learn.zipfile;

import com.zgy.learn.zipfile.util.ZipUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zgy
 * @date 2021/9/23
 */
public class ZipFileMain {
    public static void main(String[] args) throws FileNotFoundException {
        String src = "D:/upload/ner";
        FileOutputStream fos1 = new FileOutputStream(new File("d:/mytest01.zip"));
        FileOutputStream fos2 = new FileOutputStream(new File("d:/mytest02.zip"));
        ZipUtil.toZipWithDir(src, fos1, true);
        List<File> files = new ArrayList<>();
        File file01 = new File("D:/upload/ner/bb12faecc8706be8f2b2068f47fbcf27/s1.pcapng");
        File file02 = new File("D:/迅雷下载/阳光电影www.ygdy8.com.罗小黑战记.HD.1080p.国语中字.mp4");
        File file03 = new File("D:/迅雷下载/OneMind.zip");
        files.add(file01);
        files.add(file02);
        files.add(file03);
        // ZipUtil.toZipWithFiles(files, fos2);
    }
}
