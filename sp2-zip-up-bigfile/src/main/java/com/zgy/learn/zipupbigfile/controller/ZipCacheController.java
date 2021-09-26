package com.zgy.learn.zipfile.controller;

import com.zgy.learn.zipfile.util.ZipUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author zgy
 * @date 2021/9/23
 */
@Controller
@RequestMapping("zip/cache")
public class ZipCacheController {
    private final String tmp_path = "d:";

    @ResponseBody
    @RequestMapping("download")
    public String download(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException {
        /*1.创建临时文件夹*/
        File temDir = new File(tmp_path + "/" + id);
        if (!temDir.exists()) {
            temDir.mkdirs();
        }
        /*2.生成需要下载的文件，存放在临时文件夹内*/
        String src = "D:/upload/ner";
        FileOutputStream fos1 = new FileOutputStream(new File(temDir + File.separator + "mytest03.zip"));
        ZipUtil.toZipWithDir(src, fos1, true);

        /*3.设置response的header*/
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=excel.zip");

        /*4.调用工具类,下载zip压缩包*/
        ZipUtil.toZipWithDir(temDir.getPath(), response.getOutputStream(), false);

        /*5.删除临时文件和文件夹*/
        File[] listFiles = temDir.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            listFiles[i].delete();
        }
        temDir.delete();
        return "okay";
    }
}
