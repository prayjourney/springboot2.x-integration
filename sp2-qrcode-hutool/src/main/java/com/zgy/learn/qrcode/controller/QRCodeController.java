package com.zgy.learn.qrcode.controller;

import com.zgy.learn.qrcode.service.QRCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: pray-journey.io
 * @description:
 * @date: created in 2021-01-25
 * @modified:
 */
@RestController
@Slf4j
public class QRCodeController {
    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("qrCode")
    public void getQRCode(String codeContent, HttpServletResponse response) {
        try {
            qrCodeService.createQRCode2Stream(codeContent, response);
            log.info("成功生成二维码！");
        } catch (Exception e) {
            log.error("发生错误， 错误信息是：{}！", e.getMessage());
        }
    }

}
