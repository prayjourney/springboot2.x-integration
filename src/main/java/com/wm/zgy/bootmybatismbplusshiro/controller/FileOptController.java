package com.wm.zgy.bootmybatismbplusshiro.controller;

import com.wm.zgy.bootmybatismbplusshiro.utils.JSONUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author renjiaxin
 * @Date 2020/5/18
 * @Description
 */
@RestController
public class FileOptController {
    public String pictureName = "s1.xxx";
    public String picturePath = "d://";

    //文件下载相关代码
    @ResponseBody
    @RequestMapping("/download")
    public void fileDownload(HttpServletResponse response) {
        System.out.println(123);
        File file = new File(picturePath + pictureName);
        if (pictureName != null) {
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                Date currentTime = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                String dataTime = dateFormat.format(currentTime);
                //文件重新命名
                String pictureNewName = dataTime + pictureName.substring(pictureName.indexOf("."));
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + pictureNewName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println(pictureNewName + "下载成功！！！");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(pictureNewName + "下载失败！！！" + e);
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    //文件下载， 这个下载下来是一个空的文件
    @ResponseBody
    @RequestMapping("/download2")
    public void fileDownload2(HttpServletResponse response) throws IOException {
        // 数据
        HashMap<String, Object>  hm =new HashMap<>();
        hm.put("name","张三");
        hm.put("age",22);
        hm.put("gender","男");

        // 设置强制下载不打开
        response.setContentType("application/force-download");
        // 文件名
        String fileName = System.currentTimeMillis() + "xxx.txt";
        // 设置文件名
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

        // 创建文件
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        //byte[] buffer = new byte[1024];
        byte[] buffer = JSONUtil.getJsonFromObject(hm).getBytes("UTF-8");

        FileInputStream fis = new FileInputStream(file);   //这样文件的内容是空的，下载下来也是空的，因为里面是没有什么内容的
        BufferedInputStream bis = new BufferedInputStream(fis);

        OutputStream os = response.getOutputStream();
        //os.write(buffer);

        int i = bis.read(buffer);
        while (i != -1) {   // 这个有问题
            os.write(buffer, 0, i);
            i = bis.read(buffer);
        }
        System.out.println(fileName + "下载成功！！！");
    }


    //文件下载
    @ResponseBody
    @RequestMapping("/download3")
    public void fileDownload3(HttpServletResponse response) throws IOException {
        // 数据
        HashMap<String, Object>  hm =new HashMap<>();
        hm.put("name","张三");
        hm.put("age",22);
        hm.put("gender","男");

        // 设置强制下载不打开
        response.setContentType("application/force-download");
        // 文件名
        String fileName = System.currentTimeMillis() + "-xxx.txt";
        // 设置文件名
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

        //byte[] buffer = new byte[1024];
        byte[] buffer = JSONUtil.getJsonFromObject(hm).getBytes("UTF-8");
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        OutputStream os = response.getOutputStream();
        os.write(buffer);

        System.out.println(fileName + "下载成功！！！");
    }

}
