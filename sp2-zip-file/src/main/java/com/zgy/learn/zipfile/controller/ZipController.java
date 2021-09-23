package com.zgy.learn.zipfile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zgy
 * @date 2021/9/23
 */
@Controller
@RequestMapping("zip")
public class ZipController {
    @ResponseBody
    @RequestMapping("download")
    public String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 文件名称
        String[] files = {"D:/URL/nginx配置与部署项目静态+动态.rar",
                "D:/迅雷下载/OneMind.zip"};
        // zip
        File zipFile = new File("d:/hello.zip");
        byte[] buf = new byte[2048];
        int len;
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFile));
        for (int i = 0; i < files.length; i++) {
            FileInputStream in = new FileInputStream(files[i]);
            zout.putNextEntry(new ZipEntry(files[i]));
            while ((len = in.read(buf)) > 0) {
                zout.write(buf, 0, len);
            }
            zout.closeEntry();
            in.close();
        }
        zout.close();
        // 下载文件
        FileInputStream zipInput = new FileInputStream(zipFile);
        OutputStream out = response.getOutputStream();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=images.zip");
        while ((len = zipInput.read(buf)) != -1) {
            out.write(buf, 0, len);
        }
        zipInput.close();
        out.flush();
        out.close();
        // 删除压缩包
        zipFile.delete();
        return "okay";
    }
}
