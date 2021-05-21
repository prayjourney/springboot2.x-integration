package com.zgy.learn.txyuncos.controller;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-05-16
 * @modified:
 */
@Controller
public class UploadCosController {
    @Value("${tenxun.cos.secretId}")
    private String secretId;
    @Value("${tenxun.cos.secretKey}")
    private String secretKey;
    @Value("${tenxun.cos.region}")
    private String region;
    @Value("${tenxun.cos.bucket}")
    private String bucket;
    @Value("${tenxun.cos.path}")
    private String path;

    @ResponseBody
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) {
        if (file == null) {
            return "文件为空!";
        }
        String fileName = file.getOriginalFilename();
        // 1.初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2.设置region区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 3.生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // 4.获取桶的名字, bucket的命名规则为{name}-{appid}, 此处填写的存储桶名称必须为此格式
        String bucketName = bucket;

        // 简单文件上传, 最大支持5GB, 适用于小文件上传, 建议20M以下的文件使用该接口, 大文件上传请参照API文档高级API上传
        File localFile = null;
        try {
            // 存储到临时文件夹
            localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            // 指定要上传到COS上对象键
            // 对象键Key是对象在存储桶中的唯一标识。例如, 在对象的访问域名bucket1-1250000000.cos.ap-chengdu.myqcloud.com/mydemo.jpg中，对象键为
            // mydemo.jpg, 详情参考 [对象键](https://cloud.tencent.com/document/product/436/13324)
            String key = fileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            return putObjectResult.getRequestId() + "," + putObjectResult.getETag();
        } catch (IOException e) {
            return "error!";
        } finally {
            // 关闭客户端(关闭后台线程)
            cosclient.shutdown();
        }
    }

}
