package com.zgy.learn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author renjiaxin
 * @date 2021/1/27
 */
@RestController
@RequestMapping("file")
@Slf4j
public class FileController {
    @Value("${file.upload.dir}")
    private String uploadFilePath;

    @ResponseBody
    @PostMapping("/uploadFile")
    public String fileUpload(@RequestParam("file") MultipartFile file) throws JSONException {
        JSONObject result = new JSONObject();
        if (file.isEmpty()) {
            result.put("error", "空文件!");
            return result.toString();
        }
        // 文件名
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传文件名称为:{}, 后缀名为:{}!", fileName, suffixName);

        File fileTempObj = new File(uploadFilePath + "/" + fileName);
        // 检测目录是否存在
        if (!fileTempObj.getParentFile().exists()) {
            fileTempObj.getParentFile().mkdirs();
        }
        // 使用文件名称检测文件是否已经存在
        if (fileTempObj.exists()) {
            result.put("error", "文件已经存在!");
            return result.toString();
        }

        try {
            // 写入文件
            file.transferTo(fileTempObj);
        } catch (Exception e) {
            log.error("发生错误: {}", e);
            result.put("error", e.getMessage());
            // error -> 返回
            return result.toString();
        }

        result.put("success", "文件上传成功!");
        return result.toString();
    }


    // 下载到了默认的位置
    @RequestMapping("/downloadFile")
    public String fileDownload(HttpServletResponse response, @RequestParam("fileName") String fileName) throws JSONException {
        JSONObject result = new JSONObject();

        File file = new File(uploadFilePath + '/' + fileName);
        if (!file.exists()) {
            result.put("error", "下载文件不存在!");
            return result.toString();
        }

        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            log.error("发生错误: {}", e);
            result.put("error", e.getMessage());
            return result.toString();
        }
        result.put("success", "下载成功!");
        return result.toString();
    }

}
