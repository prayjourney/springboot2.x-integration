package com.zgy.bootintegration.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.zgy.bootintegration.handler.NoModelDataListener;
import com.zgy.bootintegration.mapper.StudentMapper;
import com.zgy.bootintegration.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author: z.g.y
 * @date: 2020/5/18
 * @description:
 */
@Slf4j
@RestController
public class FileOptController {
    private static List<String> excelFile =
            Arrays.asList(".xlsx", ".xlsm", ".xlsb", ".xls", ".xltx", ".xltm", ".xlt", ".xlam", "xla", ".ods");
    public String pictureName = "s1.xxx";
    // https://alibaba-easyexcel.github.io/quickstart/read.html#%E7%9B%91%E5%90%AC%E5%99%A8
    public String picturePath = "d://";
    @Autowired
    JdbcTemplate template;
    @Autowired
    StudentMapper mapper;

    // 文件下载相关代码
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


    // 文件下载， 这个下载下来是一个空的文件
    @ResponseBody
    @RequestMapping("/download2")
    public void fileDownload2(HttpServletResponse response) throws IOException {
        // 数据
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("name", "张三");
        hm.put("age", 22);
        hm.put("gender", "男");

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
        byte[] buffer = JacksonUtil.getJsonFromObject(hm).getBytes("UTF-8");

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


    // 文件下载
    @ResponseBody
    @RequestMapping("/download3")
    public void fileDownload3(HttpServletResponse response) throws IOException {
        // 数据
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("name", "张三");
        hm.put("age", 22);
        hm.put("gender", "男");

        // 设置强制下载不打开
        response.setContentType("application/force-download");
        // 文件名
        String fileName = System.currentTimeMillis() + "-xxx.txt";
        // 设置文件名
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

        //byte[] buffer = new byte[1024];
        byte[] buffer = JacksonUtil.getJsonFromObject(hm).getBytes("UTF-8");
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        OutputStream os = response.getOutputStream();
        os.write(buffer);

        System.out.println(fileName + "下载成功！！！");
    }

    // 原文链接：https://blog.csdn.net/chaos_le/java/article/details/81871472
    @ResponseBody
    @RequestMapping("/download4")
    public void downloadTXT(HttpServletResponse response) {

        String fileName = "fileName" + ".txt";
        String content = "写入txt的内容";
        response.setContentType("text/plain");

        try {
            response.setHeader("Content-Disposition", "attachment; filename="
                    + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ServletOutputStream outputStream = null;
        BufferedOutputStream buffer = null;

        try {
            outputStream = response.getOutputStream();
            buffer = new BufferedOutputStream(outputStream);
            buffer.write(content.getBytes("UTF-8"));
            buffer.flush();
            buffer.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // 文件上传，上传到了设置好的 路径
    @ResponseBody
    @RequestMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
        if (file.isEmpty()) {
            return "file is empty!";
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传文件名称为:{}, 后缀名为:{}!", fileName, suffixName);
        if (StringUtils.isBlank(suffixName) || !excelFile.contains(suffixName)) {
            return "文件不符合要求! 请重新上传符合格式的excel文件!";
        }
        // classes目录的绝对路径
        String fileTempPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String fileTempAllPath = fileTempPath + File.separator + "upload" + File.separator + fileName;
        File fileTempObj = new File(fileTempAllPath);
        // 检测目录是否存在
        if (!fileTempObj.getParentFile().exists()) {
            fileTempObj.getParentFile().mkdirs();
        }
        // 写入文件, 写入文件之后, 然后就去新的线程之中处理
        file.transferTo(fileTempObj);
        new Thread(() -> {
            String name = fileTempObj.getAbsolutePath();
            File excelFile = new File(name);
            try {
                // 使用jdbctemplate
                // ExcelReaderBuilder readerBuilder = EasyExcel.read(excelFile, new NoModelDataListener(template));
                // 使用mybatis
                ExcelReaderBuilder readerBuilder = EasyExcel.read(excelFile, new NoModelDataListener(mapper));
                readerBuilder.sheet(0).doRead();
            } catch (Exception e) {
                log.error("解析excel文件发生了错误, 原因是:{} !", e.getMessage());
            }
            excelFile.delete();
            log.info("上传到服务器的excel文件处理完毕, 已经删除!");
        }, "task: operate file").start();
        return "upload okay!";
    }
}
