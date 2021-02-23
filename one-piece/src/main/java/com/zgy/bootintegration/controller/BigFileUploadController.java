package com.zgy.bootintegration.controller;

import com.zgy.bootintegration.mapper.FileMd5Mapper;
import com.zgy.bootintegration.mapper.FileUploadStatusMapper;
import com.zgy.bootintegration.pojo.FileUploadStatus;
import com.zgy.bootintegration.pojo.MultipartFileParam;
import com.zgy.bootintegration.service.BigFileUploadService;
import com.zgy.bootintegration.utils.FileUploadConstants;
import com.zgy.bootintegration.vo.ResultStatus;
import com.zgy.bootintegration.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: z.g.y
 * @description: 大文件上传接口
 * @date: Created in 2020/10/8 23:18
 * @modified:
 */
@ApiOperation("大文件分片上传")
@Controller
@Slf4j
@RequestMapping(value = "bigfile")
public class BigFileUploadController {
    @Autowired
    private FileUploadStatusMapper fileUploadStatusMapper;

    @Autowired
    private FileMd5Mapper fileMd5Mapper;

    @Autowired
    private BigFileUploadService bigFileUploadService;

    /**
     * 秒传判断, 断点判断
     *
     * @return
     */
    @RequestMapping(value = "checkFileMd5", method = RequestMethod.POST)
    @ResponseBody
    public Object checkFileMd5(String md5) throws IOException {
        // 从redis之中获取MD5信息, 用来检测
        FileUploadStatus processingObj = fileUploadStatusMapper.selectFileMd5Obj(md5);
        if (processingObj == null) {
            return new ResultVo(ResultStatus.NO_HAVE);
        }
        boolean processing = Boolean.parseBoolean(processingObj.getUploadStatus());
        String value = fileMd5Mapper.selectFilePath(FileUploadConstants.FILE_MD5_KEY.val() + md5);
        if (processing) {
            return new ResultVo(ResultStatus.IS_HAVE, value);
        } else {
            File confFile = new File(value);
            byte[] completeList = FileUtils.readFileToByteArray(confFile);
            List<String> missChunkList = new LinkedList<>();
            for (int i = 0; i < completeList.length; i++) {
                if (completeList[i] != Byte.MAX_VALUE) {
                    missChunkList.add(i + "");
                }
            }
            return new ResultVo<>(ResultStatus.ING_HAVE, missChunkList);
        }
    }

    /**
     * 上传文件
     *
     * @param param
     * @param request
     * @return ResponseEntity
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity fileUpload(MultipartFileParam param, HttpServletRequest request) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            log.info("上传文件start, 时间是：{}", LocalDateTime.now());
            try {
                // 方法1
                // storageService.uploadFileRandomAccessFile(param);
                // 方法2 这个更快点
                bigFileUploadService.uploadFileByMappedByteBuffer(param);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件上传失败, 参数是：{}, 时间是：{}", param.toString(), LocalDateTime.now());
            }
            log.info("上传文件end, 时间是：{}", LocalDateTime.now());
        }
        return ResponseEntity.ok().body("上传成功!");
    }
}
